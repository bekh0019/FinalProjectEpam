package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.CommonDaoJdbc;
import com.jdbc.application.dao.DBSystemException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class BlockReaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommonDao commonDao = new CommonDaoJdbc();
        int id=Integer.parseInt(req.getParameter("id")) ;
        boolean block=Boolean.valueOf(req.getParameter("block"));
        try {
            if (block){
                commonDao.updateReaderAccess(false,id);
            }
            else {
                commonDao.updateReaderAccess(true,id);
            }
        } catch (DBSystemException | SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/toListReader");
        dispatcher.forward(req, resp);
    }
}

