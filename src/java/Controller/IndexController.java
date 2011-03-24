/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ComputerV1;
import Model.Human;
import Model.Player;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 *
 * @author saljack
 */
public class IndexController extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(getServletInfo());

        //Ziskani Game ze session
        HttpSession session = request.getSession();
        Game gm = (Game) session.getAttribute("game");
        if (gm == null) {
            gm = new Game();
            session.setAttribute("game", gm);
        }

        //Nastaveni tematu
        if (!"yes".equals(request.getParameter("deftheme"))) {
            String str = (String) session.getAttribute("theme");
            if (str != null) {
                gm.setTheme(str);
            } else {
                gm.setTheme("Default");
            }
        } else {
            gm.setTheme("Default");
        }

        //SetPLR
        Player[] plrs = new Player[4];
        plrs[0] = creatPlayer(request.getParameter("plr0"), request.getParameter("name0"));
        plrs[1] = creatPlayer(request.getParameter("plr1"), request.getParameter("name1"));
        plrs[2] = creatPlayer(request.getParameter("plr2"), request.getParameter("name2"));
        plrs[3] = creatPlayer(request.getParameter("plr3"), request.getParameter("name3"));
        gm.setPlayers(plrs);

        String roz = request.getParameter("velikost");
        if (roz != null) {
            int rozmer = Integer.parseInt(roz);
            gm.setRozmery(rozmer);
        }

        gm.newGame(response);





        System.err.println(response.encodeRedirectURL("table.jsp?akce=ahoj"));
        response.sendRedirect(response.encodeRedirectURL("table.jsp"));
    }

    private Player creatPlayer(String plr, String name) {
        Player player = null;
        if (plr.equals("hum")) {
            player = new Human();
        } else if (plr.equals("com1")) {
            player = new ComputerV1();
        } else if (plr.equals("com2")) {
            player = new ComputerV1();
        } else if (plr.equals("com3")) {
            player = new ComputerV1();
        } else {
            return null;
        }
        player.setName(name);
        return player;

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
