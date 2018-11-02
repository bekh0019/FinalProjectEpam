package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.CommonDaoJdbc;
import com.jdbc.application.dao.DBSystemException;
import com.jdbc.application.dao.Reader;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class EmailVerificationServlet extends HttpServlet {
    private static final String name = EmailVerificationServlet.class.getName();
    private static final Logger logger = Logger.getLogger(name);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(String.format("%s%s", "Started servlet ", name));
        CommonDao commonDao =new CommonDaoJdbc();
        HttpSession session = req.getSession(false);
        try {
commonDao.updateReaderAccess(true,((Reader)session.getAttribute("identity")).getId());
        }
        catch (SQLException | DBSystemException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/all");
        dispatcher.forward(req, resp);
        logger.info(String.format("%s%s", "Finished servlet ", name));
    }
}
