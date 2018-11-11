package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.CommonDaoJdbc;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Bekh Artem
 * servlet delete session for
 * the current role
 */
public class LogOutServlet extends HttpServlet {
    private static final String name = LogOutServlet.class.getName();
    private Logger logger;
    @Override
    public void init() {
        logger = Logger.getLogger(name);
    }
    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(String.format("%s%s", "Started servlet ", name));
        HttpSession session=req.getSession(false);
        session.invalidate();
        RequestDispatcher dispatcher = req.getRequestDispatcher(String.format("%s%s", req.getContextPath(), "/all"));
        dispatcher.forward(req, resp);
        logger.info(String.format("%s%s", "Finished servlet ", name));
    }
}
