<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
    <title>Страница отображения пользователей</title>
    <link href="${contextPath}css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}css/common.css" rel="stylesheet">
    <link rel="shortcut icon" href="favicon.ico"/>
</head>
<body>
<span style="float: right">
    <a href="${contextPath}/changeLocale?lang=en&page=cabinet">en</a>

    <a href="${contextPath}/changeLocale?lang=ru&page=cabinet">ru</a>
</span>
<h3>Это личный кабинет Админа</h3>
<form method="GET" action="${contextPath}/all" class="form-signin">
    <div class="form-group">
        <button class="btn btn-lg btn-primary btn-block" type="submit">${localeBean.exit} ${localeBean.to}
            ${localeBean.common} ${localeBean.page}</button>
    </div>
</form>
<form method="GET" action="${contextPath}/rootToCreateJournal" class="form-signin" accept-charset="UTF-8">
    <div class="form-group">
        <button class="btn btn-lg btn-primary btn-block" type="submit">${localeBean.create} ${localeBean.journal1}</button>
    </div>
</form>
    <form method="GET" action="${contextPath}/toListReader" class="form-signin" accept-charset="UTF-8">
    <div class="form-group">
        <button class="btn btn-lg btn-primary btn-block" type="submit">${localeBean.change} ${localeBean.status}</button>
    </div>
</form>
<form method="GET" action="${contextPath}/toListJournal" class="form-signin" accept-charset="UTF-8">
    <div class="form-group">
        <button class="btn btn-lg btn-primary btn-block" type="submit">${localeBean.modify} ${localeBean.journal1}</button>
    </div>
</form>
<form method="GET" action="${contextPath}/logout" class="form-signin">
    <div class="form-group">
        <button class="btn btn-lg btn-primary btn-block" type="submit">${localeBean.logOut}</button>
    </div>
</form>

</body>
</html>