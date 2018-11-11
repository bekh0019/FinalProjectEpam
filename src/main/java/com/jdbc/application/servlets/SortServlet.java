package com.jdbc.application.servlets;

import com.jdbc.application.dao.*;
import com.jdbc.application.model.Journal;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Bekh Artem
 * servlet can display all journals
 * with searched topic and sort them alphabeticly
 * or by price
 * servlet can do the same with all Journals
 */
public class SortServlet extends HttpServlet {
    private static final String NAME = SortServlet.class.getName();
    private static final Logger LOGGER = Logger.getLogger(NAME);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info(String.format("%s%s", "Started servlet ", NAME));
        CommonDao commonDao = new CommonDaoJdbc();
        List<String> topics=null;
        List<Journal> journals=null;
        String topic=req.getParameter("by");
        try {
            topics=   commonDao.selectDistinctTopics();
           journals=commonDao.selectJournalByTitle(req.getParameter("title"));

            if (topic.equals("no") && req.getParameter("sorting").equals("no")) {

              journals= commonDao.selectAllJournals();
            }
            else if (topic.equals("no")&&req.getParameter("sorting").equals("AZ")){
            journals  = commonDao.selectAllJournalsByTitleAlphabetic();
            } else if (topic.equals("no")&&req.getParameter("sorting").equals("ZA")){
                journals= commonDao.selectAllJournalsByTitleReverse();
            } else if (topic.equals("no")&&req.getParameter("sorting").equals("priceA")){
            journals  = commonDao.selectAllJournalsByPriceToBigger();
            }else if (topic.equals("no")&&req.getParameter("sorting").equals("priceD")){
            journals  = commonDao.selectAllJournalsByPriceToSmaller();
            }
            else if (req.getParameter("sorting").equals("no")){
                journals  = commonDao.selectAllJournalsByTopicWithoutSorting(topic);
            } else if (req.getParameter("sorting").equals("AZ")){
                journals  = commonDao.selectAllJournalsByTopicSortingAlphabetic(topic);
            } else if (req.getParameter("sorting").equals("ZA")){
                journals  = commonDao.selectAllJournalsByTopicSortingReverse(topic);
            } else if (req.getParameter("sorting").equals("priceA")){
                journals  = commonDao.selectAllJournalsByTopicSortingPriceToBigger(topic);
            } else if (req.getParameter("sorting").equals("priceD")){
                journals  = commonDao.selectAllJournalsByTopicSortingPriceToSmaller(topic);
            }
        }



        catch (DBSystemException | SQLException e) {
            e.printStackTrace();
        }


        req.setAttribute("journals", journals);
        req.setAttribute("topics", topics);
        RequestDispatcher dispatcher = req.getRequestDispatcher(String.format("%s%s", req.getContextPath(), "/all"));
        dispatcher.forward(req, resp);

        LOGGER.info(String.format("%s%s", "Finished servlet ", NAME));
    }
}
