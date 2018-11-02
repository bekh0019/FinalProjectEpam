package com.jdbc.application.servlets;

import com.jdbc.application.dao.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class VerifyBalanceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommonDao commonDao = new CommonDaoJdbc();
        Report report=new Report();
        HttpSession session = req.getSession(false);
        int id=Integer.parseInt(req.getParameter("id"));
        try {
            commonDao.updateReaderVerifiedBalance(id);
                report.sendMail("Your balance was successfully updated");
        } catch (DBSystemException | SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/toListReader");
        dispatcher.forward(req, resp);
    }
}

