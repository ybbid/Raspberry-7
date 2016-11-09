<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content>
    <meta name="author" content>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="/resources/js/bootstrap.min.js"></script>
    <title>Title</title>
</head>
<body>
    <div class="container">
        <form action="" class="from-signin">
            <h2 class="form-sigin-heading">管理员登陆</h2>
            <label for="inputUsername" class="sr-only">用户名</label>
            <input type="text" id="inputUsername" class="form-control" placeholder="用户名" required autofocus>
            <br/>
            <label for="inputPassword" class="sr-only">密码</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="密码" required>
            <br/>
            <button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
        </form>
    </div>
</body>
</html>
