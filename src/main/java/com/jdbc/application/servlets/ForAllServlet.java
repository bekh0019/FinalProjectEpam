package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.CommonDaoJdbc;
import com.jdbc.application.dao.DBSystemException;
import com.jdbc.application.model.LocaleBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ForAllServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession(false);
        if (session!=null) {
            req.setAttribute("identity", session.getAttribute("identity"));
        }
        LocaleBean localeBean= (LocaleBean) req.getAttribute("localeBean");
        if(!(localeBean==null)){
            req.setAttribute("localeBean",localeBean);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/PageForAll.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommonDao commonDao = new CommonDaoJdbc();
        List<String> topics=null;
        try {
            topics=   commonDao.selectDistinctTopics();
        } catch (SQLException | DBSystemException e) {
            e.printStackTrace();
        }
        HttpSession session=req.getSession(false);
        if (session!=null) {
            req.setAttribute("identity", session.getAttribute("identity"));
        }
      LocaleBean localeBean= (LocaleBean) req.getAttribute("localeBean");
        if(!(localeBean==null)){
            req.setAttribute("localeBean",localeBean);
        }
        req.setAttribute("topics",topics);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/PageForAll.jsp");
        dispatcher.forward(req, resp);


    }
}
