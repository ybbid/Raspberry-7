<%--
  Created by IntelliJ IDEA.
  User: leacher
  Date: 16-8-4
  Time: 下午1:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:useBean id="my" class="com.cltsp.bean.LF805" scope="page"/>
    <jsp:setProperty name="my" property="minBp" />
    hr:<%= my.getHr()%>
    <hr>
    hp:<%= my.getMaxBp()%>
    <hr>
    lp:<%= my.getMinBp()%>
</body>
</html>
