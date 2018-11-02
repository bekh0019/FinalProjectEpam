package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.CommonDaoJdbc;
import com.jdbc.application.dao.DBSystemException;
import com.jdbc.application.dao.Journal;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateJournalServlet extends HttpServlet {
    private static final String name = CreateJournalServlet.class.getName();
    private static final Logger logger = Logger.getLogger(name);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/CreateJournal.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(String.format("%s%s", "Started servlet ", name));
        CommonDao commonDao =new CommonDaoJdbc();
        Journal journal=null;
        try {
            commonDao.insertNewJournal(req.getParameter("title"), req.getParameter("topic"),
                   Integer.parseInt(req.getParameter("price")));
        }
        catch (DBSystemException | SQLException e) {
            e.printStackTrace();
        }
            resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/enterToCabinet"));
        logger.info(String.format("%s%s", "Finished servlet ", name));

    }
}
