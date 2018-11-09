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
            <button class="btn btn-lg btn-primary btn-block" type="submit">${localeBean.back} ${localeBean.cabinet1}</button>
        </div>
    </form>

    <h4 class="text-center">${localeBean.list1} ${localeBean.readers}</h4>
    <div align="center">
        <table class="tg">
            <tr>
                <th>${localeBean.reader1}</th>
                <th>${localeBean.access}</th>
                <th>${localeBean.block}</th>
                <th>${localeBean.balance}</th>
            </tr>
            <c:forEach items="${readers}" var="reader" varStatus="status">
                <tr valign="top">
                    <td>${reader.surname} ${reader.name}</td>
                    <td>
                        <c:if test = "${reader.access == true}">
                            ${localeBean.active}
                        </c:if>
                        <c:if test = "${reader.access == false}">
                            ${localeBean.blocked}
                        </c:if>
                    </td>
                    <td>
                        <c:if test = "${reader.access == true}">
                            <a href="${pageContext.servletContext.contextPath}/blockReader?id=${reader.id}&block=true"> ${localeBean.block1}</a>
                        </c:if>
                        <c:if test = "${reader.access == false}">
                            <a href="${pageContext.servletContext.contextPath}/blockReader?id=${reader.id}&block=false"> ${localeBean.unblock}</a>
                        </c:if>
                    </td>
                    <td>
                        <c:if test = "${reader.unverifiedBalance !=0}">
                            <a href="${pageContext.servletContext.contextPath}/verifyBalance?id=${reader.id}"> ${localeBean.verify}</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>


</body>
</html>
