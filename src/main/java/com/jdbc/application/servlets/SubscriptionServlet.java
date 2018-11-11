package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.CommonDaoJdbc;
import com.jdbc.application.dao.DBSystemException;
import com.jdbc.application.model.Reader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Bekh Artem
 * servlet contains implementation
 * of dao method with transaction
 * After transaction has been commited
 * Reader with updated credentials
 * adds to session attribute
 */
public class SubscriptionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession(false);
        CommonDao commonDao = new CommonDaoJdbc();
        Reader reader=null;
        int id_journal=Integer.parseInt(req.getParameter("id")) ;
     int id_reader=((Reader)session.getAttribute("identity")).getId();
     try {
         commonDao.subscribeToJournal(id_reader,id_journal);
     } catch (DBSystemException | SQLException e) {
         e.printStackTrace();
     }
     try {
         reader =commonDao.selectReaderById(id_reader);
     } catch (DBSystemException | SQLException e) {
         e.printStackTrace();
     }
     session.setAttribute("identity",reader);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/rootToJournals");
        dispatcher.forward(req, resp);
    }
}
