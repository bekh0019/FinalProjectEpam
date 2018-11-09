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

/**
 * @author Bekh Artem
 * class get parameter lang from request and
 * set localization to session attribute
 * according to the value of parameter.
 * Choosing localization is available at personal cabinet
 * or on page Main
 */
public class ChangeLocaleServlet extends HttpServlet {
    private static final String NAME = ChangeLocaleServlet.class.getName();
    private Logger logger;
    @Override
    public void init() {
        logger = Logger.getLogger(NAME);

    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(String.format("%s%s", "Started servlet ", NAME));
        Locale locale=Locale.getDefault();
        HttpSession session = req.getSession(false);
        String lang = req.getParameter("lang");
        String page=req.getParameter("page");
        if(lang.equals("ru")){
            locale = new Locale("ru", "RU");
        }
        else if(lang.equals("en")){
            locale = new Locale("en", "US");
        }
        ResourceBundle rb = ResourceBundle.getBundle("messages", locale);

        if(page.equals("main")){
            session.setAttribute("localeBean", LocaleBean.localBeanBuilder(rb, locale));
            RequestDispatcher dispatcher = req.getRequestDispatcher(String.format("%s%s", req.getContextPath(), "/Main.jsp"));
            dispatcher.forward(req, resp);
        }
        else if(page.equals("cabinet")){
            session.setAttribute("localeBean", LocaleBean.localBeanBuilder(rb, locale));
            RequestDispatcher dispatcher = req.getRequestDispatcher(String.format("%s%s", req.getContextPath(), "/enterToCabinet"));
            dispatcher.forward(req, resp);
        }

        logger.info(String.format("%s%s", "Finished servlet ", NAME));
    }
}
