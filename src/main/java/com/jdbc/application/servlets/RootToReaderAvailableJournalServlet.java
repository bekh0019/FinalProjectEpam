package com.jdbc.application.servlets;

import com.jdbc.application.dao.*;
import com.jdbc.application.model.Journal;
import com.jdbc.application.model.Reader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
/**
 * @author Bekh Artem
 * this Servlet has service function. It helps
 * to display all available Journals for Reader on .jsp page
 * and get Reader's verified balance from session to make
 * sure that he has enough money to subscribe on Journal
 */
public class RootToReaderAvailableJournalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommonDao commonDao = new CommonDaoJdbc();
        List<Journal> journals = null;
        HttpSession session=req.getSession(false);
        try {
            journals = commonDao.selectAllAvailableJournalsForReader(((Reader)session.getAttribute("identity")).getId());
        } catch (SQLException | DBSystemException e) {
            e.printStackTrace();
        }
        req.setAttribute("balance",((Reader)session.getAttribute("identity")).getVerifiedBalance());
        req.setAttribute("journals", journals);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/Subscription.jsp");
        dispatcher.forward(req, resp);
    }
}
