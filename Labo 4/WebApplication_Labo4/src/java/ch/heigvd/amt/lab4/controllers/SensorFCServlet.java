/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.lab4.controllers;

import ch.heigvd.amt.lab4.model.Sensor;
import ch.heigvd.amt.lab4.services.SensorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "sensors", urlPatterns = {"/sensors"})
public class SensorFCServlet extends HttpServlet {
    
    @EJB
    private SensorDAO sensorDAO;

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
        List<Sensor> listSensor;
        Sensor s;
        
        //TODO http://sensors?action=update&id=334 : ne correspond pas à ce que j'ai fait ? ...
        
        response.setContentType("text/html;charset=UTF-8");
        switch(request.getParameter("action")) {
            case "update" :
                        s = new Sensor();
                        s.setId(Integer.valueOf(request.getParameter("oldId")));
                        s.setType(request.getParameter("oldType"));
                        s.setDescription(request.getParameter("oldDescription"));
                        Sensor s2 = new Sensor();
                        s2.setId(Integer.valueOf(request.getParameter("newId")));
                        s2.setType(request.getParameter("newType"));
                        s2.setDescription(request.getParameter("newDescription"));
                        sensorDAO.update(s,s2);
                break;
            case "create" :
                        s = new Sensor();
                        s.setType(request.getParameter("type"));
                        s.setDescription(request.getParameter("description"));
                        sensorDAO.create(s);
                break;
            case "delete" :
                        sensorDAO.delete(Integer.valueOf(request.getParameter("id")));
                break;
            case "findbydescription" :
                        listSensor = sensorDAO.findByDescription(request.getParameter("description"));
                        for (Sensor sensor : listSensor) {
                            response.getWriter().println(sensor.getId() + " " + sensor.getType() + " " + sensor.getDescription());
                        }
                break;
            case "findbytype" :
                        listSensor = sensorDAO.findByType(request.getParameter("type"));
                        for (Sensor sensor : listSensor) {
                            response.getWriter().println(sensor.getId() + " " + sensor.getType() + " " + sensor.getDescription());
                        }
                break;
            case "findall" :
                        listSensor = sensorDAO.findAll();
                        for (Sensor sensor : listSensor) {
                            response.getWriter().println(sensor.getId() + " " + sensor.getType() + " " + sensor.getDescription());
                        }
                break;
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
