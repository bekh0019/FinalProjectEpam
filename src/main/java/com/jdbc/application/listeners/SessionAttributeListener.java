package com.jdbc.application.listeners;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * The Listener that writes into log when {@link HttpSessionBindingEvent} happens
 * and attributed is added or deleted to/from Session.
 * @author Bekh Artem
 */
public class SessionAttributeListener implements HttpSessionAttributeListener {

    private static final Logger LOGGER = Logger.getLogger(SessionAttributeListener.class.getName());


    public void attributeAdded(HttpSessionBindingEvent event) {

        LOGGER.info("Attribute was added to Session");
    }

    public void attributeRemoved(HttpSessionBindingEvent event) {

        LOGGER.info("Attribute was deleted from Session");
    }

    public void attributeReplaced(HttpSessionBindingEvent event) {/*NOP*/}
}
