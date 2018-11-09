package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.CommonDaoJdbc;
import com.jdbc.application.dao.DBSystemException;
import com.jdbc.application.model.Journal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
/**
 * @author Bekh Artem
 * this Servlet has service function. It is
 * made to separate logic of editing Journal and
 * coming to .jsp page.
 * As well it provides current values of Journal's
 * title,topic and price on .jsp page
 */
public class RootToEditJournalServlet extends HttpServlet {
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
}
