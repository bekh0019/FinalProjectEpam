
package com.jdbc.application.filters;

import com.jdbc.application.dao.*;
import com.jdbc.application.model.Admin;
import com.jdbc.application.model.Reader;
import org.apache.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;
/**
 * @author Bekh Artem
 * Filter checks all Readers in DB
 * before inserting new one to avoid duplicates
 */
public class UserMatchFilter extends BaseFilter {
    private static final String name = UserMatchFilter.class.getName();
    private static final Logger logger = Logger.getLogger(name);

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        logger.info(String.format("%s%s", "Started filter ", name));

       CommonDao commonDao = new CommonDaoJdbc();
        Set<Reader> allReaders = null;
        Admin admin = null;
        boolean matchFound = false;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        try {
            allReaders = commonDao.selectAllReaders();
            admin = commonDao.selectAdmin(login,password);
        }
        catch (DBSystemException | SQLException e) {
            e.printStackTrace();
        }


        if(allReaders != null){
            for (Reader reader : allReaders) {
                if(reader.getLogin().equals(login) || reader.getEmail().equals(email)){
                    matchFound = true;
                    break;
                }
            }
        }
        if(admin != null && admin.getLogin().equals(login)){
                    matchFound = true;
                }



        if(matchFound){
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/errors/ErrorUserMatchFound.jsp");
            dispatcher.forward(req, resp);
        }
        else filterChain.doFilter(req, resp);

        logger.info(String.format("%s%s", "Finished filter ", name));
    }
    }


