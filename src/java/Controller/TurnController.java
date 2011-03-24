/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author saljack
 */
@WebServlet(name = "TurnServlet", urlPatterns = {"/Turn"})
public class TurnController extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
         * Tady zjistit kterou kartu otocit to poslat game a presmerovat na table
         */

        HttpSession session = request.getSession();

        String url;

        Game gm = (Game) session.getAttribute("game");
        if (gm == null) {
            System.err.println("Session problem");
            url = response.encodeRedirectURL("index.jsp");
        } else {
            int turnCard = -1;
            String card = request.getParameter("card");
            if (card != null) {
                turnCard = Integer.parseInt(card);
            }else{
                card = request.getParameter("turnback");
                if(card != null){
                    gm.turnBackCards();
                }
            }
                    
            
            url = response.encodeRedirectURL("table.jsp");
            if (turnCard != -1) {
                
                if(gm.turnCard(turnCard)){//Kdyz jsou otoceny dve karty tak zobrazi tlacitko
                    url +="?turned=true";
                }
            }
            
        }
        
        //Presmerovani
        response.sendRedirect(url);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
