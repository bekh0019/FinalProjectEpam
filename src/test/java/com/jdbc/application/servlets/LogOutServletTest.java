package com.jdbc.application.servlets;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LogOutServletTest {
    private final HttpServletRequest req= mock(HttpServletRequest.class);
    private final HttpServletResponse resp= mock(HttpServletResponse.class);
    private final RequestDispatcher dispatcher= mock(RequestDispatcher.class);
    private final HttpSession session=mock(HttpSession.class);
    private final Logger logger= mock(Logger.class);
    LogOutServlet servlet=new LogOutServlet();
    @Before
    public void setupDaoIntoServlet() {
        servlet.setLogger(logger);
    }
    @Test
    public void doPost() {
    }

    @Test
    public void doGet() throws ServletException, IOException {
        when(req.getSession(false)).thenReturn(session);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        servlet.doGet(req,resp);
        verify(session,atLeastOnce()).invalidate();
        verify(req, times(1)).getRequestDispatcher(anyString());
        verify(dispatcher, times(1)).forward(req, resp);
    }
}