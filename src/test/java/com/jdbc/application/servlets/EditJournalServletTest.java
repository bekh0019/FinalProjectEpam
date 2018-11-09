package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.DBSystemException;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EditJournalServletTest {
    private final HttpServletRequest req= mock(HttpServletRequest.class);
    private final HttpServletResponse resp= mock(HttpServletResponse.class);
    private final CommonDao commonDao=mock(CommonDao.class);
    private final Logger logger= mock(Logger.class);
    EditJournalServlet servlet=new EditJournalServlet();
    @Before
    public void setupDaoIntoServlet() {
        servlet.setCommonDao(commonDao);
        servlet.setLogger(logger);
    }
    @Test
    public void doPost() throws IOException, DBSystemException, SQLException {
        when(req.getParameter("title")).thenReturn("Ferrari");
        when(req.getParameter("topic")).thenReturn("Sport");
        when(req.getParameter("price")).thenReturn("300");
        when(req.getParameter("id_journal")).thenReturn("2");
        servlet.doPost(req,resp);
        verify(req,atLeastOnce()).getParameter("title");
        verify(req,atLeastOnce()).getParameter("topic");
        verify(req,atLeastOnce()).getParameter("price");
        verify(req,atLeastOnce()).getParameter("id_journal");
        verify(commonDao, times(1)).updateJournalById(anyString(),anyString(),anyInt(),anyInt());
    }
    @Test(expected = NullPointerException.class)
    public void testDoPostException() throws ServletException, IOException {
        servlet.doPost(null, null);
    }
}