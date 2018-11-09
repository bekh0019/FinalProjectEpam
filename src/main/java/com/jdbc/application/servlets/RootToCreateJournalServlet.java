package com.jdbc.application.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Bekh Artem
 * this Servlet has service function. It is
 * made to separate logic of creating Journal and
 * coming to .jsp page.
 */
public class RootToCreateJournalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/CreateJournal.jsp");
        dispatcher.forward(req, resp);
    }
}
