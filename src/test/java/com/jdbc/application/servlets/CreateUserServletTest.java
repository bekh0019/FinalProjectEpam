package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.DBSystemException;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CreateUserServletTest {
    private final HttpServletRequest req= mock(HttpServletRequest.class);
    private final HttpServletResponse resp= mock(HttpServletResponse.class);
    private final RequestDispatcher dispatcher= mock(RequestDispatcher.class);
    private final HttpSession session=mock(HttpSession.class);
    private final Logger logger= mock(Logger.class);
    private final CommonDao commonDao=mock(CommonDao.class);
    private final Reader reader=mock(Reader.class);
    private final Report report=mock(Report.class);
    CreateUserServlet servlet=new CreateUserServlet();
    @Before
    public void setupDaoIntoServlet() {
        servlet.setCommonDao(commonDao);
        servlet.setLogger(logger);
        servlet.setReport(report);
    }
    @Test
    public void doPost() throws ServletException, IOException, DBSystemException, SQLException {
        when(req.getParameter("name")).thenReturn("Artem");
        when(req.getParameter("surname")).thenReturn("Bekh");
        when(req.getParameter("login")).thenReturn("bekh0019");
        when(req.getParameter("password")).thenReturn("23456Qw");
        when(req.getParameter("email")).thenReturn("qwqqw@rt.ru");
        when(req.getSession()).thenReturn(session);
        when(commonDao.selectReader(anyString(),anyString())).thenReturn(reader);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        servlet.doPost(req,resp);
        verify(req,atLeastOnce()).getParameter("name");
        verify(req,atLeastOnce()).getParameter("surname");
        verify(req,atLeastOnce()).getParameter("login");
        verify(req,atLeastOnce()).getParameter("password");
        verify(req,atLeastOnce()).getParameter("email");
        verify(commonDao, times(1)).insertNewReader(anyString(),anyString(),anyString(),anyString(),anyString(),anyBoolean());
        verify(commonDao, times(1)).selectReader(anyString(),anyString());
        verify(session,atLeastOnce()).setAttribute(anyString(),any());
        verify(req, times(1)).getRequestDispatcher(anyString());
        verify(dispatcher, times(1)).forward(req, resp);
verify(report,times(1)).sendMail(anyString(),anyString());
    }
}