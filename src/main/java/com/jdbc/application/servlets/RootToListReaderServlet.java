package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.CommonDaoJdbc;
import com.jdbc.application.dao.DBSystemException;
import com.jdbc.application.model.Reader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;
/**
 * @author Bekh Artem
 * this Servlet has service function. It helps
 * to display all available Readers on .jsp page
 */
public class RootToListReaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommonDao commonDao = new CommonDaoJdbc();
        Set<Reader> readers = null;
        try {
            readers = commonDao.selectAllReaders();
        } catch (SQLException | DBSystemException e) {
            e.printStackTrace();
        }
        req.setAttribute("readers", readers);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/ChangeReaderStatus.jsp");
        dispatcher.forward(req, resp);
    }

}
