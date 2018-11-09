package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.CommonDaoJdbc;
import com.jdbc.application.dao.DBSystemException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Bekh Artem
 * servlet insert new Journal into DB
 * Redirect to personal cabinet
 */
public class CreateJournalServlet extends HttpServlet {
    private static final String name = CreateJournalServlet.class.getName();
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info(String.format("%s%s", "Started servlet ", name));
        try {
            commonDao.insertNewJournal(req.getParameter("title"), req.getParameter("topic"),
                   Integer.parseInt(req.getParameter("price")));
        }
        catch (DBSystemException | SQLException e) {
            e.printStackTrace();
        }
            resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/enterToCabinet"));
        logger.info(String.format("%s%s", "Finished servlet ", name));

    }
}
