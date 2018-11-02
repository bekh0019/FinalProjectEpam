package com.jdbc.application.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8"); // кодировка ответа
        req.setCharacterEncoding("UTF-8");
        //  super.doPost(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost (req, resp);
        HttpSession session=req.getSession(false);
        session.invalidate();
        RequestDispatcher dispatcher = req.getRequestDispatcher(String.format("%s%s", req.getContextPath(), "/all"));
        dispatcher.forward(req, resp);


    }
}
