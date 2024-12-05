<%--ログイン成功画面--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成功</title>
</head>
<body>
<h1>ログインが成功しました</h1>
<%--リクエストスコープに設定されたname--%>
<p>ようこそ${requestScope.name}さん</p>
<%--BookListServletにリクエストが送信--%>
<a href="./BookListServlet">蔵書一覧閲覧画面へ</a>

</body>
</html>
