<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
    <title>Страница для всех</title>
    <link href="${contextPath}css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}css/common.css" rel="stylesheet">
    <link rel="shortcut icon" href="favicon.ico"/>
</head>
<body>
<h3>${localeBean.common1} ${localeBean.page1}</h3>
<c:if test="${empty identity}">
    <h4 class="text-center">${localeBean.hello} ${localeBean.guest1}</h4>
    <form method="POST" action="${contextPath}/login" class="form-signin" accept-charset="UTF-8">
        <h4 class="text-left">${localeBean.log2in}</h4>
        <div class="form-group">
            <input name="login" type="text" class="form-control" autofocus="true" />
            <input name="password" type="password" class="form-control" />

            <button class="btn btn-lg btn-primary btn-block" type="submit">${localeBean.log_In}</button>
            <h4 class="text-center"><a href="${contextPath}/Registration.jsp">${localeBean.create} ${localeBean.account}</a></h4>
        </div>
    </form>
</c:if>
<c:if test="${!empty identity}">
    <%--<h4 class="text-center">Hello ${identity.name} ${identity.surname}  </h4>--%>
    <form method="GET" action="${contextPath}/enterToCabinet" class="form-signin">
        <div class="form-group">
            <button class="btn btn-lg btn-primary btn-block" type="submit">${localeBean.back} ${localeBean.to1} ${localeBean.cabinet}</button>
        </div>
    </form>
</c:if>
<form method="POST" action="${contextPath}/search" class="form-signin">
    <div class="form-group">
        <h5 class="text-left">${localeBean.search} ${localeBean.journal}</h5>
        <input name="title" type="text" class="form-control" />
        <div align="center">
            <button class="btn btn-lg btn-primary btn-block" type="submit">${localeBean.search}</button>
        </div>
    </div>
</form>
<form method="POST" action="${contextPath}/sort" class="form-signin">
    <div class="form-group">
        <h6 class="text-left">${localeBean.please} ${localeBean.choose} ${localeBean.display} ${localeBean.journals1}</h6>
        <select name="by">
            <option value="no" hidden>${localeBean.choose1}</option>
            <optgroup label="${localeBean.topics}">
                <c:forEach items="${topics}" var="topic" varStatus="status">
                    <option value="${topic}">${topic}</option>
                </c:forEach>
            </optgroup>
        </select>
        <h6 class="text-left">${localeBean.please} ${localeBean.select} ${localeBean.type1}</h6>
        <select name="sorting">
            <option value="no" selected>${localeBean.without}</option>
            <option value="AZ">${localeBean.alphabetic}</option>
            <option value="ZA">${localeBean.reverse}</option>
               <option value="priceA">${localeBean.ascending}</option>
            <option value="priceD">${localeBean.descending}</option>
        </select>
        <div align="center">
            <button class="btn btn-lg btn-primary btn-block" type="submit">${localeBean.sort}</button>
        </div>
    </div>
</form>

<h4 class="text-center">${localeBean.list1} ${localeBean.journals1}</h4>
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