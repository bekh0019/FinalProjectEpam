package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.CommonDaoJdbc;
import com.jdbc.application.dao.DBSystemException;
import com.jdbc.application.model.Reader;
import org.apache.log4j.Logger;

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
 * Servlet set Reader's access true
 * Now Reader can enter to personal cabinet
 */
public class EmailVerificationServlet extends HttpServlet {
    private static final String name = EmailVerificationServlet.class.getName();
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(String.format("%s%s", "Started servlet ", name));
        HttpSession session = req.getSession(false);
        try {
commonDao.updateReaderAccess(true,((Reader)session.getAttribute("identity")).getId());
        }
        catch (SQLException | DBSystemException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Main.jsp");
        dispatcher.forward(req, resp);
        logger.info(String.format("%s%s", "Finished servlet ", name));
    }
}
