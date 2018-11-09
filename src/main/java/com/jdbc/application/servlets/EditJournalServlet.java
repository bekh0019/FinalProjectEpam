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
 * Servlet get Journal new credentials
 * from request and update Journal
 */
public class EditJournalServlet extends HttpServlet {
    private static final String name = EditJournalServlet.class.getName();
    private  Logger logger;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info(String.format("%s%s", "Started servlet ", name));
        String title=req.getParameter("title");
        String topic=req.getParameter("topic");
        int price=Integer.parseInt(req.getParameter("price"));
        int id_journal=Integer.parseInt(req.getParameter("id_journal"));
        try {
            commonDao.updateJournalById(title,topic,
                    price,id_journal);
        }
        catch (DBSystemException | SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/enterToCabinet"));
        logger.info(String.format("%s%s", "Finished servlet ", name));

    }
    }

