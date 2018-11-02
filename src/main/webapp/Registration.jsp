<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
    <title>Пример стартовой страницы</title>
    <link href="${contextPath}css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}css/common.css" rel="stylesheet">
</head>
<body>
<span style="float: right">
    <a href="${contextPath}/changeLocale?lang=en">en</a>
    |
    <a href="${contextPath}/changeLocale?lang=ru">ru</a>
</span>
<h4 class="text-center">Welcome to the official website of the Journal's Catalog</h4>
<div class="container">
    <form method="POST" action="${contextPath}/createUser" class="form-signin" accept-charset="UTF-8">
        <div class="form-group">
            <h5 class="text-left">${localeBean.login}</h5>
            <input name="login" type="text" class="form-control" autofocus="true" />
            <h5 class="text-left">${localeBean.password}</h5>
            <input name="password" type="password" class="form-control" />
            <h5 class="text-left">Name</h5>
            <input name="name" type="text" class="form-control" />
            <h5 class="text-left">Surname</h5>
            <input name="surname" type="text" class="form-control" />
            <h5 class="text-left">Email</h5>
            <input name="email" type="text" class="form-control" />
            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </div>

    </form>
</div>
</body>
</html>
