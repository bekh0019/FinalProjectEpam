package com.jdbc.application.servlets;

import com.jdbc.application.dao.CommonDao;
import com.jdbc.application.dao.DBSystemException;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class BlockReaderServletTest {
private final HttpServletRequest req= mock(HttpServletRequest.class);
private final HttpServletResponse resp= mock(HttpServletResponse.class);
private final RequestDispatcher dispatcher= mock(RequestDispatcher.class);
private final CommonDao commonDao=mock(CommonDao.class);
private final Logger logger= mock(Logger.class);
private final BlockReaderServlet servlet=new BlockReaderServlet();
    @Before
    public void setupDaoIntoServlet() {
        servlet.setCommonDao(commonDao);
        servlet.setLogger(logger);
    }
    @Test
    public void testDoGet() throws ServletException, IOException, DBSystemException, SQLException {
        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("block")).thenReturn("true");
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
     servlet.doGet(req,resp);
        verify(req,atLeastOnce()).getParameter("id");
        verify(req, atLeastOnce()).getParameter("block");
        verify(req, times(1)).getRequestDispatcher(anyString());
        verify(commonDao, times(1)).updateReaderAccess(anyBoolean(),anyInt());
        verify(dispatcher, times(1)).forward(req, resp);
    }
}