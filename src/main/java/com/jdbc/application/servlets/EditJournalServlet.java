package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.CommonDaoJdbc;
import com.jdbc.application.dao.DBSystemException;
import com.jdbc.application.dao.Journal;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EditJournalServlet extends HttpServlet {
    private static final String name = EditJournalServlet.class.getName();
    private static final Logger logger = Logger.getLogger(name);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Journal journal=null;
        CommonDao commonDao =new CommonDaoJdbc();
        try {
            journal= commonDao.selectJournalById(Integer.parseInt(req.getParameter("id")));


        }
        catch (DBSystemException | SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("journal",journal);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/EditJournal.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(String.format("%s%s", "Started servlet ", name));
        CommonDao commonDao =new CommonDaoJdbc();
        String title=req.getParameter("title");
        String topic=req.getParameter("topic");
        int price=Integer.parseInt(req.getParameter("price"));
        int id_journal=Integer.parseInt(req.getParameter("id_journal"));
        try {
            commonDao.updateJournalById(title,topic,
                    price,id_journal);
        }
        catch (DBSystemException | SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/enterToCabinet"));
        logger.info(String.format("%s%s", "Finished servlet ", name));

    }
    }

