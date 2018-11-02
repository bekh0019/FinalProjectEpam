<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
    <title>Страница отображения пользователей</title>
    <link href="${contextPath}css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}css/common.css" rel="stylesheet">
</head>
<body>
<div class="container">

    <form method="GET" action="${contextPath}/enterToCabinet" class="form-signin">
        <div class="form-group">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Go back to personal cabinet</button>
        </div>
    </form>

    <h4 class="text-center">List of all journals</h4>
    <div align="center">
        <table class="tg">
            <tr>
                <th>Journal</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            <c:forEach items="${journals}" var="journal" varStatus="status">
                <tr valign="top">
                    <td>${journal.title}</td>
                    <td>
                        <a href="${pageContext.servletContext.contextPath}/deleteJournal?id=${journal.id}">Delete</a>
                    </td>
                    <td>
                        <a href="${pageContext.servletContext.contextPath}/editJournal?id=${journal.id}">Edit</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>

</form>
</body>
</html>
