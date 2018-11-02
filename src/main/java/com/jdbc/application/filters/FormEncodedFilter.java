package com.jdbc.application.filters;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormEncodedFilter extends BaseFilter {
    private static final String FILTERABLE_CONTENT_TYPE="application/x-www-form-urlencoded";

    private static final String ENCODING_DEFAULT = "UTF-8";

    private static final String ENCODING_INIT_PARAM_NAME = "encoding";

    private String encoding;

    @Override
    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String contentType = servletRequest.getContentType();
        if (contentType != null && contentType.startsWith(FILTERABLE_CONTENT_TYPE))
            servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);

    }

    public void init(FilterConfig filterConfig) throws ServletException{
        encoding = filterConfig.getInitParameter(ENCODING_INIT_PARAM_NAME);
        if (encoding == null)
            encoding = ENCODING_DEFAULT;
    }
}
