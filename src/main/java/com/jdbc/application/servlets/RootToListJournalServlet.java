package com.jdbc.application.servlets;

import com.jdbc.application.dao.*;
import com.jdbc.application.model.Journal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
/**
 * @author Bekh Artem
 * this Servlet has service function. It helps
 * to display all available Journals on .jsp page
 */
public class RootToListJournalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommonDao commonDao = new CommonDaoJdbc();
        List<Journal> journals = null;
        try {
            journals = commonDao.selectAllJournals();
        } catch (SQLException | DBSystemException e) {
            e.printStackTrace();
        }
        req.setAttribute("journals", journals);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/ChangeJournal.jsp");
        dispatcher.forward(req, resp);
    }
}
