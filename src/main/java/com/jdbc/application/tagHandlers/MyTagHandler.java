package com.jdbc.application.tagHandlers;

import java.util.Calendar;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
public class MyTagHandler extends TagSupport{

    public int doStartTag() {
        JspWriter out=pageContext.getOut();//returns the instance of JspWriter
        try{
            out.print(Calendar.getInstance().getTime());//printing date and time using JspWriter
        }catch(Exception e){System.out.println(e.getMessage());}
        return SKIP_BODY;//will not evaluate the body content of the tag
    }
}
