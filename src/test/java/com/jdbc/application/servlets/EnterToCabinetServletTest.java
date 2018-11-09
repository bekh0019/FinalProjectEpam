package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.DBSystemException;
import com.jdbc.application.model.Journal;
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
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class EnterToCabinetServletTest {
    private final HttpServletRequest req= mock(HttpServletRequest.class);
    private final HttpServletResponse resp= mock(HttpServletResponse.class);
    private final RequestDispatcher dispatcher= mock(RequestDispatcher.class);
    private final HttpSession session=mock(HttpSession.class);
    private final Logger logger= mock(Logger.class);
    private final CommonDao commonDao=mock(CommonDao.class);
    private final Reader reader=mock(Reader.class);
    private final List<Journal> journals= Collections.singletonList(mock(Journal.class));
    private final EnterToCabinetServlet servlet=new EnterToCabinetServlet();
    @Before
    public void setupDaoIntoServlet() {
        servlet.setCommonDao(commonDao);
        servlet.setLogger(logger);
    }

    @Test
    public void doGetPositive() throws DBSystemException, SQLException, ServletException, IOException {
        when(req.getSession(false)).thenReturn(session);
        when(reader.isAccess()).thenReturn(true);
        when(reader.getId()).thenReturn(1);
        when(reader.getLogin()).thenReturn("newUser");
        when(reader.getPassword()).thenReturn("Howareyou1");
        when(reader.toString()).thenReturn("reader");
        when(session.getAttribute("identity")).thenReturn(reader);
        when(session.getAttribute("role")).thenReturn(reader);
        when(session.getAttribute("money")).thenReturn(Boolean.valueOf("true"));
        when(commonDao.selectReader(reader.getLogin(),reader.getPassword())).thenReturn(reader);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        servlet.doGet(req,resp);
        verify(commonDao, times(1)).selectAllReaderJournal(anyInt());
        verify(commonDao, times(1)).selectReader(anyString(),anyString());
        verify(session,atLeastOnce()).setAttribute(anyString(),any());
        verify(session,atLeastOnce()).getAttribute(anyString());
        verify(req,atLeastOnce()).setAttribute(anyString(),any());
        verify(req, times(1)).getRequestDispatcher(anyString());
        verify(dispatcher, times(1)).forward(req, resp);

    }    @Test
    public void doGetNegative() throws DBSystemException, SQLException, ServletException, IOException {
        when(req.getSession(false)).thenReturn(null);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        servlet.doGet(req,resp);
        verify(commonDao,never()).selectAllReaderJournal(anyInt());
        verify(commonDao, never()).selectReader(anyString(),anyString());
        verify(session,never()).setAttribute(anyString(),any());
        verify(session,never()).getAttribute(anyString());
        verify(req,never()).setAttribute(anyString(),any());
        verify(req, times(1)).getRequestDispatcher(anyString());
        verify(dispatcher, times(1)).forward(req, resp);

    }
}