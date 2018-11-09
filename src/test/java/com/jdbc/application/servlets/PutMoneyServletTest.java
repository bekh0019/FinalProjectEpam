package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.DBSystemException;
import com.jdbc.application.model.Reader;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class PutMoneyServletTest {
    private final HttpServletRequest req= mock(HttpServletRequest.class);
    private final HttpServletResponse resp= mock(HttpServletResponse.class);
    private final RequestDispatcher dispatcher= mock(RequestDispatcher.class);
    private final HttpSession session=mock(HttpSession.class);
    private final Logger logger= mock(Logger.class);
    private final CommonDao commonDao=mock(CommonDao.class);
    private final Reader reader=mock(Reader.class);
    PutMoneyServlet servlet=new PutMoneyServlet();
    @Before
    public void setupDaoIntoServlet() {
        servlet.setCommonDao(commonDao);
        servlet.setLogger(logger);
    }
    @Test
    public void doPost() throws IOException, DBSystemException, SQLException {
        when(req.getSession(false)).thenReturn(session);
        when(reader.getId()).thenReturn(Integer.parseInt("1"));
        when(req.getParameter("unverifiedBalance")).thenReturn("300");
        when((Reader)session.getAttribute(anyString())).thenReturn(reader);
        servlet.doPost(req,resp);
        verify(commonDao, times(1)).updateReaderUnverifiedBalance(anyInt(),anyInt());
        verify(session,atLeastOnce()).setAttribute(anyString(),any());
    }

}