package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.CommonDaoJdbc;
import com.jdbc.application.dao.DBSystemException;
import com.jdbc.application.dao.Reader;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
    private static final Logger LOGGER = Logger.getLogger(NAME);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info(String.format("%s%s", "Started servlet ", NAME));
        CommonDao commonDao=new CommonDaoJdbc();
        HttpSession session = req.getSession(false);
        try {
            commonDao.updateReaderUnverifiedBalance(Integer.parseInt(req.getParameter("unverifiedBalance")),
                    ((Reader)session.getAttribute("identity")).getId());
        } catch (DBSystemException | SQLException e) {
            e.printStackTrace();
        }
        session.setAttribute("money",true);
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/enterToCabinet"));
        LOGGER.info(String.format("%s%s", "Finished servlet ", NAME));
    }
}
