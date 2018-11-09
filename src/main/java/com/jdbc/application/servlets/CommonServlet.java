package com.jdbc.application.servlets;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *This servlet is responsible
 * for display page Main. When application
 * starts this doGet is used.
 * @author Bekh A
 */
public class CommonServlet extends HttpServlet {
    private static final String NAME = CommonServlet.class.getName();
    private Logger logger;
    @Override
    public void init() {
        logger = Logger.getLogger(NAME);
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(String.format("%s%s", "Started servlet ", NAME));
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/Main.jsp");
        dispatcher.forward(req, resp);
        logger.info("Servlet finished");
    }
}
