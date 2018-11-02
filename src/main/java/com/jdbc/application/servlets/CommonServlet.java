package com.jdbc.application.servlets;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Bekh A
 */
public class CommonServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CommonServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8"); // кодировка ответа
        req.setCharacterEncoding("UTF-8");
      //  super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost (req, resp);
        LOGGER.info("Servlet started");
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/Main.jsp");
        dispatcher.forward(req, resp);
        LOGGER.info("Servlet finished");


    }
}
