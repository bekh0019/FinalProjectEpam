package com.jdbc.application.listeners;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.jdbc.application.model.LocaleBean;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * The Listener that launches the logging process using Log4J.
 * The logging process start out when {@link ServletContext} is initialized.
 * Set english default localization.
 * @author Bekh Artem
 */
public class LogListener implements ServletContextListener {
private static final String DEFAULT_LOCALE="en";
    private static final Logger LOGGER = Logger.getLogger(LogListener.class.getName());

    public void contextInitialized(ServletContextEvent event) {
        String homeDir = event.getServletContext().getRealPath("/");
        File propertiesFile = new File(homeDir,"WEB-INF/Log4J_config.properties");
        PropertyConfigurator.configure(propertiesFile.toString());
        Locale locale = new Locale("en", "US");
        ResourceBundle rb = ResourceBundle.getBundle("messages", locale);
        ServletContext servletContext = event.getServletContext();
        servletContext.setAttribute("localeBean", LocaleBean.localBeanBuilder(rb, locale));

        LOGGER.info("ServletContext is just initialized. Logging start out");
        }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        LOGGER.info("ServletContext was destroyed");
    }
}
