<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
    <title>Страница для всех</title>
    <link href="${contextPath}css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}css/common.css" rel="stylesheet">
</head>
<body>
<span style="float: right">
    <a href="${contextPath}/changeLocale?lang=en">en</a>
    |
    <a href="${contextPath}/changeLocale?lang=ru">ru</a>
</span>
<h3>Общая страница</h3>
<c:if test="${empty identity}">
    <h4 class="text-center">Hello guest!</h4>
</c:if>

<c:if test="${role} eq 'reader'">
    <h4 class="text-center">Hello ${identity.name} ${identity.surname}  </h4>
    <form method="GET" action="${contextPath}/enterToCabinet" class="form-signin">
        <div class="form-group">
            <button class="btn btn-lg btn-primary btn-block" type="submit">${localeBean.back} ${localeBean.to1} ${localeBean.cabinet}</button>
        </div>
    </form>
</c:if>
<form method="POST" action="${contextPath}/login" class="form-signin" accept-charset="UTF-8">
    <h4 class="text-left">Log in</h4>
    <div class="form-group">
        <input name="login" type="text" class="form-control" autofocus="true" />
        <input name="password" type="password" class="form-control" />

        <button class="btn btn-lg btn-primary btn-block" type="submit">${localeBean.log_In}</button>
        <h4 class="text-center"><a href="${contextPath}/Registration.jsp">${localeBean.create} ${localeBean.account}</a></h4>
    </div>
</form>
<form method="POST" action="${contextPath}/search" class="form-signin">
    <div class="form-group">
        <h5 class="text-left">Search Journal</h5>
        <input name="title" type="text" class="form-control" />
        <div align="center">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Search</button>
        </div>
    </div>
</form>
<form method="POST" action="${contextPath}/sort" class="form-signin">
    <div class="form-group">
        <h6 class="text-left">Please choose the criterias for display the journals</h6>
        <select name="by">
            <option value="no" hidden>Choose</option>
            <optgroup label="By Topics">
                <c:forEach items="${topics}" var="topic" varStatus="status">
                    <option value="${topic}">${topic}</option>
                </c:forEach>
            </optgroup>
        </select>
        <h6 class="text-left">Please select type of sorting:</h6>
        <select name="sorting">
            <option value="no" selected>Without sorting</option>
            <option value="AZ">Sorting alphabetic (AZ)</option>
            <option value="ZA">Sorting alphabetic reverse (ZA)</option>
               <option value="priceA">Ascending</option>
            <option value="priceD">Descending</option>
        </select>
        <div align="center">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sort</button>
        </div>
    </div>
</form>

<h4 class="text-center">List of journals</h4>
<div align="center">
    <table class="tg">
        <tr>
            <th>Title</th>
            <th>Topic</th>
            <th>Price</th>
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