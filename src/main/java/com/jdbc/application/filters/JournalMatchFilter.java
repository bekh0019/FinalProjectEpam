package com.jdbc.application.filters;

import com.jdbc.application.dao.*;
import com.jdbc.application.model.Journal;
import org.apache.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Bekh Artem
 * Filter checks all Journals in DB
 * before inserting new one to avoid duplicates
 */
public class JournalMatchFilter extends BaseFilter {
    private static final String name = UserMatchFilter.class.getName();
    private static final Logger logger = Logger.getLogger(name);
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        logger.info(String.format("%s%s", "Started filter ", name));
        CommonDao commonDao = new CommonDaoJdbc();
        boolean matchFound = false;
        List<Journal> journals=null;
        String title = req.getParameter("title");
        try {
            journals = commonDao.selectAllJournals();
        }
        catch (DBSystemException | SQLException e) {
            e.printStackTrace();
        }


        if(journals != null){
            for (Journal journal : journals) {
                if(journal.getTitle().equals(title)){
                    matchFound = true;
                    break;
                }
            }
        }
        if(matchFound){
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/errors/ErrorJournalMatchFound.jsp");
            dispatcher.forward(req, resp);
        }
        else filterChain.doFilter(req, resp);
        logger.info(String.format("%s%s", "Finished filter ", name));
    }
}
