package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.model.LocaleBean;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ForAllServletTest {
    private final HttpServletRequest req= mock(HttpServletRequest.class);
    private final HttpServletResponse resp= mock(HttpServletResponse.class);
    private final RequestDispatcher dispatcher= mock(RequestDispatcher.class);
    private final HttpSession session=mock(HttpSession.class);
    private final Logger logger= mock(Logger.class);
    private final LocaleBean localeBean=mock(LocaleBean.class);
    private final CommonDao commonDao=mock(CommonDao.class);
    private final ForAllServlet servlet=mock(ForAllServlet.class);
    @Before
    public void setupDaoIntoServlet() {
        servlet.setCommonDao(commonDao);
        servlet.setLogger(logger);
    }
    @Test
    public void doPost() throws ServletException, IOException {
        when(req.getSession(false)).thenReturn(session);
    when(session.getAttribute(anyString())).thenReturn("identity");
    when(req.getAttribute("localeBean")).thenReturn(localeBean);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        servlet.doPost(req,resp);
        verify(req,atLeastOnce()).setAttribute(anyString(),any());
        verify(req, times(1)).getRequestDispatcher(anyString());
        verify(dispatcher, times(1)).forward(req, resp);
    }

    @Test
    public void doGet() {

    }
}