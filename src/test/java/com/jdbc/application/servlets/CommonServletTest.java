package com.jdbc.application.servlets;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class CommonServletTest {
    private final HttpServletRequest req= mock(HttpServletRequest.class);
    private final HttpServletResponse resp= mock(HttpServletResponse.class);
    private final RequestDispatcher dispatcher= mock(RequestDispatcher.class);
    private final Logger logger= mock(Logger.class);
    private final CommonServlet servlet=new CommonServlet();
    @Before
    public void setupDaoIntoServlet() {
        servlet.setLogger(logger);
    }
    @Test
    public void doPost() {
        servlet.doPost(req,resp);
    }

    @Test
    public void doGet() throws ServletException, IOException {
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        servlet.doGet(req,resp);
        verify(dispatcher, times(1)).forward(req, resp);
    }
    @Test(expected = NullPointerException.class)
    public void testDoGetException() throws ServletException, IOException {
        servlet.doGet(null, null);
    }
}