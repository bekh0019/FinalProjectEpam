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
 * Filter for Journal credentials
 */
public class JournalFilter extends BaseFilter {
    private static final String name = ValidRegDataFilter.class.getName();
    private static final Logger logger = Logger.getLogger(name);
    private Boolean matchFound = false;
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
       logger.info(String.format("%s%s", "Started filter ", name));
        String title=req.getParameter("title");
        String topic=req.getParameter("topic");
        String price=req.getParameter("price");
        Pattern pattern=Pattern.compile("^[a-zA-zА-Яа-я0-9]{2,20}$");
        Matcher matcher=pattern.matcher(title);
        Pattern pattern1=Pattern.compile("[a-zA-zА-Яа-я]{2,20}");
        Matcher matcher1=pattern1.matcher(topic);
        Pattern pattern2=Pattern.compile("[0-9]+");
        Matcher matcher2=pattern2.matcher(price);
        if (matcher.find()&&matcher1.find()&&matcher2.find()){
            matchFound=true;
            filterChain.doFilter(req, resp);
        }
        else if(!matchFound){
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/errors/IncorrectJournalCredentials.jsp");
            dispatcher.forward(req, resp);
        }

        logger.info(String.format("%s%s", "Finished filter ", name));
    }
}
