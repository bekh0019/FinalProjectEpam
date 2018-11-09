package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.CommonDaoJdbc;
import com.jdbc.application.dao.DBSystemException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Bekh Artem
 * Servlet get Journal id from request
 * and delete Journal
 */
public class DeleteJournalServlet extends HttpServlet {
    private static final String name = DeleteJournalServlet.class.getName();
    private CommonDao commonDao;
    private Logger logger;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(String.format("%s%s", "Started servlet ", name));
        int id=Integer.parseInt(req.getParameter("id")) ;
        try {
                commonDao.deleteJournalById(id);
        } catch (DBSystemException | SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/toListJournal");
        dispatcher.forward(req, resp);
        logger.info(String.format("%s%s", "Finished servlet ", name));
    }
}
