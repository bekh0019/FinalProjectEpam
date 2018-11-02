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
    <form method="POST" action="${contextPath}/editJournal?id_journal=${journal.id}" class="form-signin" accept-charset="UTF-8">
        <div class="form-group">
            <h5 class="text-left">Title</h5>
            <input name="title" value="${journal.title}" type="text" class="form-control" />
            <h5 class="text-left">Topic</h5>
            <input name="topic" value="${journal.topic}" type="text" class="form-control" />
            <h5 class="text-left">Price</h5>
            <input name="price" value="${journal.price}" type="text" class="form-control" />
            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>

        </div>

    </form>
</div>
</body>
</html>
