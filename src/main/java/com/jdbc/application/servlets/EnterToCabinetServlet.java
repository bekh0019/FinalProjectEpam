package com.jdbc.application.servlets;

import com.jdbc.application.dao.*;
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
import java.util.Set;

public class EnterToCabinetServlet extends HttpServlet {
    private static final String name = EnterToCabinetServlet.class.getName();
    private static final Logger logger = Logger.getLogger(name);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info(String.format("%s%s", "Started servlet ", name));
        CommonDao commonDao = new CommonDaoJdbc();
        HttpSession session = req.getSession(false);
        List<Journal> journals=null;

        if(session == null){
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/ErrorLogging.jsp");
            dispatcher.forward(req, resp);
        }
        if(session.getAttribute("role") == Role.Reader && ((Reader) session.getAttribute("identity")).isAccess()){
            try {
                journals=  commonDao.selectAllReaderJournal(((Reader)session.getAttribute("identity")).getId());
                if ((Boolean) (session.getAttribute("money"))){
                  Reader reader=commonDao.selectReader(((Reader) session.getAttribute("identity")).getLogin(),
                          ((Reader) session.getAttribute("identity")).getPassword());
                    session.setAttribute("identity",reader);
                }
            } catch (SQLException | DBSystemException e) {
                e.printStackTrace();
            }
            req.setAttribute("journals", journals);
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/ReaderCabinet.jsp");
            dispatcher.forward(req, resp);
        }
        else if(session.getAttribute("role") == Role.Admin){

            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/AdminCabinet.jsp");
            dispatcher.forward(req, resp);
        }
        else{
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/errors/ErrorLogging.jsp");
            dispatcher.forward(req, resp);
        }


        logger.info(String.format("%s%s", "Finished servlet ", name));
    }
}
