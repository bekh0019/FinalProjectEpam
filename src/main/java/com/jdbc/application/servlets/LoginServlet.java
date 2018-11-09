
package com.jdbc.application.servlets;

import com.jdbc.application.dao.*;
import com.jdbc.application.model.Admin;
import com.jdbc.application.model.Reader;
import com.jdbc.application.model.Role;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    private static final String name = LoginServlet.class.getName();
    private  Logger logger;
    private CommonDao commonDao;
    @Override
    public void init() {
        commonDao = new CommonDaoJdbc();
        logger = Logger.getLogger(name);
    }

    public void setCommonDao(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(String.format("%s%s", "Started servlet ", name));
        Reader reader = null;
        Admin admin = null;

        try {
            reader = commonDao.selectReader(req.getParameter("login"), req.getParameter("password"));
        } catch (DBSystemException | SQLException e) {
            e.printStackTrace();
        }

        if (reader != null) {
            HttpSession session = req.getSession();
            session.setAttribute("role", Role.Reader);
            session.setAttribute("identity", reader);
            session.setAttribute("money",false);
            resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/enterToCabinet"));
        }


        try {
            admin = commonDao.selectAdmin(req.getParameter("login"),req.getParameter("password"));
        } catch (DBSystemException | SQLException e) {
            e.printStackTrace();
        }

        if (admin != null) {
            HttpSession session = req.getSession();
            session.setAttribute("role", Role.Admin);
            session.setAttribute("identity", admin);
            resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/enterToCabinet"));
        }

        if ((admin == null) && (reader == null)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/errors/ErrorLogging.jsp");
            dispatcher.forward(req, resp);

            logger.info(String.format("%s%s", "Finished servlet ", name));
        }
    }
}

