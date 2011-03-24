/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author saljack
 */
public class LoadImage extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            //Ziskani img z parametru
            InputStream fl = request.getPart("file").getInputStream();
            String state = "failed";

            //Vytvoreni noveho obrazku
            ServletContext sc = request.getSession().getServletContext();
            //test na nastaveni adresaru
            File file_img = new File(sc.getRealPath("img"));

            //existuje adresar img
            if (!file_img.isDirectory()) {
                file_img.mkdir();
            }

            int num = 0;
            File dir_theme = new File(file_img.getPath() + "/Theme" + num);
            //Vytvorit adresar pro theme
            while (dir_theme.isDirectory()) {
                dir_theme = new File(file_img.getPath() + "/Theme" + num);
                ++num;
            }

            //Vytvorit dir v /img/ThemeXX
            if (dir_theme.mkdir()) {
                File image = new File(dir_theme.getPath() + "/theme.jpg");
                image.createNewFile();

                //Kopirovani
                OutputStream fout = new FileOutputStream(image);
                byte data[] = new byte[1024];
                int pocet;

                while (fl.available() > 0) {
                    pocet = fl.read(data);
                    fout.write(data, 0, pocet);
                }

                //Kontrola je-li file jpeg a vytvoreni subimage

                if (image.isFile()) {
                    String mime = new MimetypesFileTypeMap().getContentType(image);
                    if ("image/jpeg".equals(mime)) {
//                        bal.setPath(ImageCreator.createImage(image));
                        if (ImageCreator.createImage(image) != null) {
                            HttpSession session = request.getSession();
//                        System.err.println(dir_theme.getName());
                            session.setAttribute("theme", dir_theme.getName());
                            state = "loaded";
                        } else {
                            System.err.println("Neni jpeg");
                            image.delete();
                            dir_theme.delete();
                        }


                    } else {
                        System.err.println("Neni jpeg");
                        image.delete();
                        return;
                    }
                }
            } else {
                throw new IOException(dir_theme + " was not created");
            }

            //Presmerovat na index.jsp
            response.sendRedirect(request.getContextPath() + "/index.jsp?theme="+state);

        } catch (IOException ioex) {
            System.err.println(ioex.toString());
        }
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
