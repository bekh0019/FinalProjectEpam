<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
    <title>Page to create journal</title>
    <link href="${contextPath}css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}css/common.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form method="POST" action="${contextPath}/createJournal" class="form-signin" accept-charset="UTF-8">
        <div class="form-group">
            <h5 class="text-left">${localeBean.title}</h5>
            <input name="title" type="text" class="form-control" />
            <h5 class="text-left">${localeBean.topic}</h5>
            <input name="topic" type="text" class="form-control" />
            <h5 class="text-left">${localeBean.price}</h5>
            <input name="price" type="text" class="form-control" />
            <button class="btn btn-lg btn-primary btn-block" type="submit">${localeBean.submit}</button>
        </div>

    </form>
</div>
</body>
</html>
