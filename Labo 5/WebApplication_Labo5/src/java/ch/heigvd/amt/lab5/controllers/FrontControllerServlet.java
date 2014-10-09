package ch.heigvd.amt.lab5.controllers;

import ch.heigvd.amt.lab5.model.Measure;
import ch.heigvd.amt.lab5.model.Sensor;
import ch.heigvd.amt.lab5.services.DataManagerLocal;
import ch.heigvd.amt.lab5.services.MeasuresManagerLocal;
import ch.heigvd.amt.lab5.services.SensorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.logging.Logger;

@WebServlet(name = "FrontControllerServlet", urlPatterns = {"/FrontControllerServlet"})
public class FrontControllerServlet extends HttpServlet {

    @EJB
    private MeasuresManagerLocal measureManager;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        

        List<Measure> measureList = measureManager.getMeasures();
        
        //get request parameters (in the URL or in the body for POST requests) for pagination
        int measureNumber = measureList.size();
        try {
            measureNumber = Integer.valueOf(request.getParameter("measureNumber"));
        } catch (Exception e) {
            measureNumber = measureList.size();
        }
        
        List<Measure> paginedMeasureList = new LinkedList<Measure>();
        for (int i = 0; i < measureNumber && i < measureList.size(); i++) {
            paginedMeasureList.add(measureList.get(i));
        }
        request.setAttribute("measureList", paginedMeasureList);
                
        String	message	= "A First Java EE Web App";	
        request.setAttribute("message",	message);
        request.getRequestDispatcher("WEB-INF/views/measures.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
