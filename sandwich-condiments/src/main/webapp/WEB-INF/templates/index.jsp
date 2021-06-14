<%--Created by IntelliJ IDEA.--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Choose Sandwich Condiments</title>
  </head>
  <body>
    <div align="center">
      <h3>Sandwich Condiments</h3>

      <form method="post" >
        <input type="checkbox" name = "condiments" value="Lettuce">Lettuce
        <input type="checkbox" name = "condiments" value="Tomato">Tomato
        <input type="checkbox" name = "condiments" value="Mustard">Mustard
        <input type="checkbox" name = "condiments" value="Sprouts">Sprouts  <br/><br/>
        <p style="color: red">${message}</p>
        <input type = "submit" value = "Save">
      </form>
    </div>
  </body>
</html>
