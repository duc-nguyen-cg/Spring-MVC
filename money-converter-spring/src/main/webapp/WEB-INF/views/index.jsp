<%--Created by IntelliJ IDEA.--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Money Converter</title>
  </head>
  <body>
    <div align = "center">
      <h3>Simple Money Converter</h3>
      <form method = "post">
        <p>USD :
          <input type="text" name = "usd" value = "${usd}"><input type = "submit" value = "Convert">
        </p>
      </form>
      <br/>
      <p style="color: red">${message}</p>
      <p>VND :
        <input readonly type="text" name = "vnd" value = "${vnd}">
      </p>
    </div>
  </body>
</html>
