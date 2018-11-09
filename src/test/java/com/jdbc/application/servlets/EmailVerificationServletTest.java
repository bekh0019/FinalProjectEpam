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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class EmailVerificationServletTest {
    private final HttpServletRequest req= mock(HttpServletRequest.class);
    private final HttpServletResponse resp= mock(HttpServletResponse.class);
    private final RequestDispatcher dispatcher= mock(RequestDispatcher.class);
    private final HttpSession session=mock(HttpSession.class);
    private final Logger logger= mock(Logger.class);
    private final Reader reader=mock(Reader.class);
    private final CommonDao commonDao=mock(CommonDao.class);
    EmailVerificationServlet servlet=new EmailVerificationServlet();
    @Before
    public void setupDaoIntoServlet() {
        servlet.setCommonDao(commonDao);
        servlet.setLogger(logger);
    }
    @Test
    public void doGet() throws ServletException, IOException, DBSystemException, SQLException {
        when(req.getSession(false)).thenReturn(session);
        when(reader.getId()).thenReturn(Integer.parseInt("1"));
        when((Reader)session.getAttribute(anyString())).thenReturn(reader);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        servlet.doGet(req,resp);
        verify(commonDao, times(1)).updateReaderAccess(anyBoolean(),anyInt());
        verify(req, times(1)).getRequestDispatcher(anyString());
        verify(dispatcher, times(1)).forward(req, resp);
    }
    @Test(expected = NullPointerException.class)
    public void testDoGetException() throws ServletException, IOException {
        servlet.doGet(null, null);
    }
}