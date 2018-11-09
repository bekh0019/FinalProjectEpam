package com.jdbc.application.servlets;

import com.jdbc.application.dao.*;
import com.jdbc.application.model.Journal;
import com.jdbc.application.model.Reader;
import com.jdbc.application.model.Role;
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

/**
 * @author Bekh Artem
 * Servlet checks session atttribute role
 * and redirect to Admin or Reader cabinet
 * For Reader all his journals are selected.
 * If he get to this servlet via PRG redirect from
 * PutMoneyServlet then there is a varification of updating balance
 * @see PutMoneyServlet
 *
 */
public class EnterToCabinetServlet extends HttpServlet {
    private static final String name = EnterToCabinetServlet.class.getName();
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
        List<Journal> journals=null;

        if(session == null){
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/ErrorLogging.jsp");
            dispatcher.forward(req, resp);
        }
      else if(session.getAttribute("role").toString().equals((Role.Reader).toString()) && ((Reader) session.getAttribute("identity")).isAccess()){
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
