package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.DBSystemException;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class CreateJournalServletTest {
    private final HttpServletRequest req= mock(HttpServletRequest.class);
    private final HttpServletResponse resp= mock(HttpServletResponse.class);
    private final CommonDao commonDao=mock(CommonDao.class);
    private final Logger logger= mock(Logger.class);
    private final CreateJournalServlet servlet=new CreateJournalServlet();
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
        servlet.doPost(req,resp);
        verify(req,atLeastOnce()).getParameter("title");
        verify(req, atLeastOnce()).getParameter("topic");
        verify(req, atLeastOnce()).getParameter("price");
        verify(commonDao, times(1)).insertNewJournal(anyString(),anyString(),anyInt());
       // verify(resp, times(1)).sendRedirect(anyString(),anyString(), "/enterToCabinet");
    }
}