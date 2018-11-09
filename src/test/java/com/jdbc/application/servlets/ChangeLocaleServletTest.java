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

import static org.mockito.Mockito.*;

public class ChangeLocaleServletTest {
    private final HttpServletRequest req= mock(HttpServletRequest.class);
    private final HttpServletResponse resp= mock(HttpServletResponse.class);
    private final RequestDispatcher dispatcher= mock(RequestDispatcher.class);
    private final HttpSession session=mock(HttpSession.class);
    private final Logger logger= mock(Logger.class);
    private final ChangeLocaleServlet servlet=new ChangeLocaleServlet();
    @Before
    public void setupDaoIntoServlet() {
        servlet.setLogger(logger);
    }
    @Test
    public void testDoGet() throws ServletException, IOException {
when(req.getParameter("lang")).thenReturn("ru");
when(req.getParameter("page")).thenReturn("main");
when(req.getSession(false)).thenReturn(session);
when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
servlet.doGet(req,resp);
        verify(req,atLeastOnce()).getParameter("lang");
        verify(req,atLeastOnce()).getParameter("page");
        verify(req,atLeastOnce()).getSession(anyBoolean());
        verify(session,atLeastOnce()).setAttribute(anyString(),any());
        verify(req, times(1)).getRequestDispatcher(anyString());
        verify(dispatcher, times(1)).forward(req, resp);
    }
    @Test(expected = NullPointerException.class)
    public void testDoGetException() throws ServletException, IOException {
        servlet.doGet(null, null);
    }
}