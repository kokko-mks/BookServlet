<%--ユーザーが「ユーザー名」と「パスワード」を入力してサーバーに送信するためのフォーム--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>図書館蔵書検索</title>
</head>
<body>
<h1>図書館蔵書検索</h1>
<%--データをPOSTリクエストで送信--%>
<%--入力された情報はLoginServletへ--%>
<form action="./LoginServlet" method="post">

  ユーザー名：<input type="text" name="name"><br>
  パスワード：<input type="password" name="pass"><br>

  <button type="submit">ログイン</button>

</form>

</body>