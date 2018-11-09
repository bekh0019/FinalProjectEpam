package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.CommonDaoJdbc;
import com.jdbc.application.dao.DBSystemException;
import com.jdbc.application.model.Reader;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/putmoney")
public class PutMoneyServlet extends HttpServlet {
    private static final String NAME = PutMoneyServlet.class.getName();
    private  Logger logger;
    private CommonDao commonDao;
    @Override
    public void init() {
        commonDao = new CommonDaoJdbc();
        logger = Logger.getLogger(NAME);
    }

    public void setCommonDao(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info(String.format("%s%s", "Started servlet ", NAME));
        HttpSession session = req.getSession(false);
        try {
            commonDao.updateReaderUnverifiedBalance(Integer.parseInt(req.getParameter("unverifiedBalance")),
                    ((Reader)session.getAttribute("identity")).getId());
        } catch (DBSystemException | SQLException e) {
            e.printStackTrace();
        }
        session.setAttribute("money",true);
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/enterToCabinet"));
        logger.info(String.format("%s%s", "Finished servlet ", NAME));
    }
}
