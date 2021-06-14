<%--Created by IntelliJ IDEA.--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>Calculator</title>
  </head>
  <body>
    <div align="center">
      <h2>Calculator</h2>
      <form method = "post">
        <input type = "text" name = "first" value = "${first}">
        <select name = "operation">
          <c:forEach items="${operations}" var="operation">
              <option value="${operation}"
                      <c:if test="${operation == chosenOpr}">selected</c:if>
              >${operation}
              </option>
          </c:forEach>
        </select>
        <input type = "text" name = "second" value = "${second}"><br/><br/>
        <p style="color: red">${message}</p>
        <input type = "submit" value="Calculate">
      </form>
      <input readonly type = "text" name = "result" value = "${result}">
    </div>
  </body>
</html>
