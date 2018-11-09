package com.jdbc.application.filters;

import org.apache.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bekh Artem
 * Filter validate login,email,password using regex.
 */
public class ValidRegDataFilter extends BaseFilter {
    private static final String name = ValidRegDataFilter.class.getName();
    private static final Logger logger = Logger.getLogger(name);
    private Boolean matchFound = false;
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        logger.info(String.format("%s%s", "Started filter ", name));
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String nameUser = req.getParameter("name");
        String surname = req.getParameter("surname");
        String password = req.getParameter("password");
        Pattern pattern=Pattern.compile("^[A-Za-z0-9_-]{6,14}$");
        Matcher matcher=pattern.matcher(login);
        Pattern pattern1=Pattern.compile("([a-zA-z]+)@([a-zA-Z]+)\\.([a-zA-Z]+)");
        Matcher matcher1=pattern1.matcher(email);
        Pattern pattern2=Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
        Matcher matcher2=pattern2.matcher(password);
        //(?=.*[0-9])       <== digit must occur at least once
        //(?=\S+$)          <== no whitespace allowed in the entire string
        //.{8,}             <== anything, at least eight places though
        if (surname.length() < 2 || nameUser.length()<2){
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/errors/ErrorLogging.jsp");
            dispatcher.forward(req, resp);
        }
        if (matcher.find()&&matcher1.find()&&matcher2.find()){
            matchFound=true;
            filterChain.doFilter(req, resp);
        }
      else if(!matchFound){
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/errors/IncorrectLoginOrEmail.jsp");
            dispatcher.forward(req, resp);
        }
        else {
            System.out.println("Happened something strange");
            logger.info(String.format("%s%s", "Unchecked registration data income", name));
            filterChain.doFilter(req,resp);
        }
        logger.info(String.format("%s%s", "Finished filter ", name));
    }
}
