package io.hardingadonis.r7.controller.web;

import io.hardingadonis.r7.dao.*;
import java.io.*;
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
                System.err.println(ex.getMessage());
            }
        });
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
