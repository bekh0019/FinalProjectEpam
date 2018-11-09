package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.CommonDaoJdbc;
import com.jdbc.application.dao.DBSystemException;
import com.jdbc.application.model.Journal;
import com.jdbc.application.model.LocaleBean;
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

public class ForAllServlet extends HttpServlet {
    private static final String name = ForAllServlet.class.getName();
    private Logger logger;
    private CommonDao commonDao;
    @Override
    public void init() {
        commonDao = new CommonDaoJdbc();
        logger = Logger.getLogger(name);
    }

    public void setCommonDao(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(String.format("%s%s", "Started servlet ", name));
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
        logger.info(String.format("%s%s", "Finished servlet ", name));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(String.format("%s%s", "Started servlet ", name));
        List<Journal> journals=null;
        List<String> topics=null;
        try {
            journals=commonDao.selectAllJournals();
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
        req.setAttribute("journals",journals);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/PageForAll.jsp");
        dispatcher.forward(req, resp);
        logger.info(String.format("%s%s", "Finished servlet ", name));

    }
}
