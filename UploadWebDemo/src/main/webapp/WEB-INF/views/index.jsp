<%--
  Created by IntelliJ IDEA.
  User: zou
  Date: 17-10-18
  Time: 上午10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
Hello World!
<form action="/fileUpload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" accept="image/jpeg,image/png,image/gif" >
    <button type="submit" >提交</button>
</form>
</body>
</html>
