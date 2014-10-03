/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.lab4.controllers;

import ch.heigvd.amt.lab4.model.Measure;
import ch.heigvd.amt.lab4.services.MeasuresManagerLocal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(name = "DataLoggerServlet", urlPatterns = {"/DataLoggerServlet"})
public class DataLoggerServlet extends HttpServlet {
    
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
        BufferedReader requestReader = request.getReader();
        String requestLine;
        String[] requestLineTokens;
        
        String measureSensorId;
        long measureTimeStamp;
        double measureValue;
        
        int validMeasureNbr = 0;
        int invalidMeasureNbr = 0;
        while((requestLine = requestReader.readLine()) != null) {
            requestLineTokens = requestLine.split(",");
            try {
                measureSensorId = requestLineTokens[0];
                measureTimeStamp = Long.valueOf(requestLineTokens[1]);
                measureValue = Double.valueOf(requestLineTokens[2]);
            } catch(Exception e) {
                invalidMeasureNbr++;
                continue;
            }
            validMeasureNbr++;
            measureManager.addMeasure(new Measure(measureSensorId,measureTimeStamp,measureValue));
        }
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DataLoggerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<p>" + validMeasureNbr + " measures added. " + invalidMeasureNbr + " invalid lines.</p>");
            out.println("</body>");
            out.println("</html>");
        }
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
