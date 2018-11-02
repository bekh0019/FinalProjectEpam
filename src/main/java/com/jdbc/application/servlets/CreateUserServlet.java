package com.jdbc.application.servlets;

import com.jdbc.application.dao.*;
import com.jdbc.application.dao.Report;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class CreateUserServlet extends HttpServlet {
    private static final String name = CreateUserServlet.class.getName();
    private static final Logger logger = Logger.getLogger(name);



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info(String.format("%s%s", "Started servlet ", name));

       CommonDao commonDao =new CommonDaoJdbc();
        Report report=new Report();
        Reader reader = null;
        req.setCharacterEncoding("UTF-8");
            try {
                commonDao.insertNewReader(req.getParameter("name"), req.getParameter("surname"),
                        req.getParameter("login"), req.getParameter("password"),
                        req.getParameter("email"),false);
            }
            catch (DBSystemException | SQLException e) {
                e.printStackTrace();
            }
            try {
                reader= commonDao.selectReader(req.getParameter("login"), req.getParameter("password"));
            }
            catch (DBSystemException | SQLException e) {
                e.printStackTrace();
            }

        if (reader != null) {
                HttpSession session = req.getSession();
                session.setAttribute("role", Role.Reader);
                session.setAttribute("identity", reader);
                report.sendMail("Hello, " + reader.getName()+" thank you for your interest in our journal's catalog website, here is your link:");
               // resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/main"));
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/EmailConfirm.jsp");
                dispatcher.forward(req, resp);
            }
            else {
                System.out.println("Error in process of registration");
            }

        logger.info(String.format("%s%s", "Finished servlet ", name));
        }

    }

