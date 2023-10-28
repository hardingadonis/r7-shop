package io.hardingadonis.r7.controller.web;

import io.hardingadonis.r7.dao.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminDAO.getInstance().get(1).ifPresent(e -> {
            try {
                PrintWriter out = response.getWriter();

                out.println("<h1>" + e + "</h1>");
            } catch (IOException ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
