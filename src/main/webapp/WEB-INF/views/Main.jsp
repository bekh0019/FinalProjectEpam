<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
    <title>Пример главной страницы</title>
    <link href="${contextPath}css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}css/common.css" rel="stylesheet">
</head>
<body>
<form method="POST" action="${contextPath}/login" class="form-signin" accept-charset="UTF-8">
    <h4 class="text-left">Log in</h4>

    <div class="form-group">
        <input name="login" type="text" class="form-control" autofocus="true" />
        <input name="password" type="password" class="form-control" />

        <button class="btn btn-lg btn-primary btn-block" type="submit">${localeBean.log_In}</button>
        <h4 class="text-center"><a href="${contextPath}Registration.jsp">${localeBean.create} ${localeBean.account}</a></h4>
    </div>

</form>


<form method="GET" action="${contextPath}/all" class="form-signin">
    <div class="form-group">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Enter as a guest</button>
    </div>
</form>



</body>
</html>