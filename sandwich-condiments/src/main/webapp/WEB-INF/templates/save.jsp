<%--Created by IntelliJ IDEA.--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sandwich Condiments</title>
</head>
<body>
    <div align="center">
        <h3>Chosen Condiments</h3>
        <c:forEach var="condiment" items="${condiments}">
            <p>${condiment}</p>
        </c:forEach>
        <br/>
        <a href="/">Back</a>
    </div>
</body>
</html>
