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
    |
    <a href="${contextPath}/changeLocale?lang=ru&page=cabinet">ru</a>
</span>
<h3>${localeBean.personal} ${localeBean.cabinet}</h3>
<c:if test="${!empty identity}">
    <h4 class="text-center">${localeBean.hello} ${identity.name} ${identity.surname}  </h4>
</c:if>
<c:if test="${money==true}">
<h3 align="center">Спасибо за пополнение счета! Ожидайте подтверждения на ваш почтовый ящик!</h3>
</c:if>
<form method="GET" action="${contextPath}/all" class="form-signin">
    <div class="form-group">
        <button class="btn btn-lg btn-primary btn-block" type="submit">${localeBean.exit} ${localeBean.to}
            ${localeBean.common} ${localeBean.page}</button>
    </div>
</form>
<form method="GET" action="${contextPath}/logout" class="form-signin">
    <div class="form-group">
        <button class="btn btn-lg btn-primary btn-block" type="submit">${localeBean.logOut}</button>
    </div>
</form>
<form method="GET" action="${contextPath}/rootToJournals" class="form-signin" accept-charset="UTF-8">
    <div class="form-group">
        <button class="btn btn-lg btn-primary btn-block" type="submit">${localeBean.subscribe} ${localeBean.on} ${localeBean.journals}</button>
    </div>
</form>
<form method="POST" action="${contextPath}/putmoney" class="form-signin">
    <div class="form-group">
        <input name="unverifiedBalance" type="text" class="form-control" autofocus="true" />
        <button class="btn btn-lg btn-primary btn-block" type="submit">${localeBean.put} ${localeBean.money}</button>
    </div>
</form>
<h4 class="text-center">${localeBean.my} ${localeBean.subscribes}</h4>
<div align="center">
    <table class="tg">
        <tr>
            <th>${localeBean.title}</th>
            <th>${localeBean.topic}</th>
            <th>${localeBean.price}</th>
        </tr>
        <c:forEach items="${journals}" var="journal" varStatus="status">
            <tr valign="top">
                <td>${journal.title}</td>
                <td>${journal.topic}</td>
                <td align="center">${journal.price}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
