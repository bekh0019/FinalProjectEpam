package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.DBSystemException;
import com.jdbc.application.model.Admin;
import com.jdbc.application.model.Reader;
import com.jdbc.application.model.Report;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LoginServletTest {
    private final HttpServletRequest req= mock(HttpServletRequest.class);
    private final HttpServletResponse resp= mock(HttpServletResponse.class);
    private final RequestDispatcher dispatcher= mock(RequestDispatcher.class);
    private final HttpSession session=mock(HttpSession.class);
    private final Logger logger= mock(Logger.class);
    private final CommonDao commonDao=mock(CommonDao.class);
    private final Reader reader=mock(Reader.class);
    private final Admin admin=mock(Admin.class);
    private final LoginServlet servlet=new LoginServlet();
    @Before
    public void setupDaoIntoServlet() {
        servlet.setCommonDao(commonDao);
        servlet.setLogger(logger);
    }
    @Test
    public void doPost() throws DBSystemException, SQLException, ServletException, IOException {
        when(req.getParameter("login")).thenReturn("newUser");
        when(req.getParameter("password")).thenReturn("2323");
        when(req.getSession()).thenReturn(session);
        when(commonDao.selectReader(anyString(),anyString())).thenReturn(reader);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        servlet.doPost(req,resp);
        verify(commonDao, times(1)).selectReader(anyString(),anyString());
        verify(req,atLeastOnce()).getParameter("login");
        verify(req,atLeastOnce()).getParameter("password");
        verify(session,atLeastOnce()).setAttribute(anyString(),any());
        verify(req, times(1)).getRequestDispatcher(anyString());
        verify(dispatcher, times(1)).forward(req, resp);
    }
}