package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.CommonDaoJdbc;
import com.jdbc.application.dao.DBSystemException;
import com.jdbc.application.model.Journal;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SearchJournalServlet extends HttpServlet {
    private static final String NAME = SearchJournalServlet.class.getName();
    private static final Logger LOGGER = Logger.getLogger(NAME);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info(String.format("%s%s", "Started servlet ", NAME));
        CommonDao commonDao = new CommonDaoJdbc();
      List<Journal> journals=null;
        try {
            journals = commonDao.selectJournalByTitle(req.getParameter("title"));

        }



        catch (DBSystemException | SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("journals", journals);
        RequestDispatcher dispatcher = req.getRequestDispatcher(String.format("%s%s", req.getContextPath(), "/all"));
        dispatcher.forward(req, resp);

        LOGGER.info(String.format("%s%s", "Finished servlet ", NAME));
    }
    }

