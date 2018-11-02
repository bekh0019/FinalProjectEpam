package com.jdbc.application.servlets;

import org.apache.log4j.Logger;
import com.jdbc.application.model.LocaleBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChangeLocaleServlet extends HttpServlet {
    private static final String NAME = ChangeLocaleServlet.class.getName();
    private static final Logger LOGGER = Logger.getLogger(NAME);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOGGER.info(String.format("%s%s", "Started servlet ", NAME));

        HttpSession session = req.getSession(false);

        String lang = req.getParameter("lang");
        Locale locale = Locale.getDefault();
        if(lang.equals("ru")){
            locale = new Locale("ru", "RU");
        }
        else if(lang.equals("en")){
            locale = new Locale("en", "US");
        }
        ResourceBundle rb = ResourceBundle.getBundle("messages", locale);

        if(session == null || session.getAttribute("identity") == null){
            req.setAttribute("localeBean", LocaleBean.localBeanBuilder(rb, locale));
            RequestDispatcher dispatcher = req.getRequestDispatcher(String.format("%s%s", req.getContextPath(), "/all"));
            dispatcher.forward(req, resp);
        }
        else{
            session.setAttribute("localeBean", LocaleBean.localBeanBuilder(rb, locale));
            RequestDispatcher dispatcher = req.getRequestDispatcher(String.format("%s%s", req.getContextPath(), "/enterToCabinet"));
            dispatcher.forward(req, resp);
        }

        LOGGER.info(String.format("%s%s", "Finished servlet ", NAME));
    }
}
