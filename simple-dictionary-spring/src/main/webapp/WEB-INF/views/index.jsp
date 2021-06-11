<%--Created by IntelliJ IDEA.--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <div align="center">
      <h1>Simple Dictionary</h1>
        <form method="post">
          <input type = "text" name = "input" placeholder="Enter a word" value="${input}">
          <input type="submit" value="Search">
        </form>
        <p style="color: red">${message}</p>
        <p><input readonly type = "text" name = "output" placeholder="Result" value="${output}"></p>
    </div>
  </body>
</html>
