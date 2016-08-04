<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Hello Jsp!</h1>
    <jsp:useBean id="my" class="com.cltsp.bean.LF805" scope="page"/>
    <form name="loginf" action="admin/jb.jsp" method="post">
        <table>
            <tr>
                <td>HR</td>
                <td><input type="text" name="Hr" value=""></td>
            </tr>
            <tr>
                <td>HP</td>
                <td><input type="text" name="maxBP" value=""></td>
            </tr>
            <tr>
                <td>LP</td>
                <td><input type="text" name="minBP" value=""></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" id="Submit"></td>
            </tr>
        </table>
    </form>
</body>
</html>
