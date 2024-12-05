<%--蔵書情報更新画面--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>蔵書更新</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h1>蔵書情報更新</h1>

<%--エラーメッセージが表示される--%>
<div class="errorMsg">
    <p>${requestScope.titleError}</p>
    <p>${requestScope.authorError}</p>
    <p>${requestScope.publisherError}</p>
    <p>${requestScope.publishDateStrError}</p>
</div>

<%--更新データを送信するためのフォーム--%>
<form action="./UpdateBookServlet" method="post">
    <p>
        <label>
            <%--初期値はリクエストスコープから取得--%>
            タイトル： <input type="text" name="title" value="${requestScope.title}">
        </label>
    </p>
    <p>
        <label>
            <%--初期値はリクエストスコープから取得--%>
            著者：  <input type="text" name="author" value="${requestScope.author}">
        </label>
    </p>
    <p>
        <label>
            <%--初期値はリクエストスコープから取得--%>
            出版社： <input type="text" name="publisher" value="${requestScope.publisher}">
        </label>
    </p>
    <p>
        <label>
            <%--初期値はリクエストスコープから取得--%>
            出版日: <input type="date" name="publish_date" value="${requestScope.publishDate}">
        </label>
    </p>
    <%--idを隠して送信。初期値はリクエストスコープから取得--%>
    <input type="hidden" name="id" value="${requestScope.id}">

    <%--フォームを送信する更新ボタン--%>
    <input type="submit" value="更新">
</form>

<p>
    <%--指定したIDの本を削除するためのリンク--%>
    <button><a href="DeleteBookServlet?id=${requestScope.id}">削除</a></button>
</p>

<%--蔵書一覧画面へのリンク--%>
<a href="./BookListServlet">蔵書一覧に戻る</a>


</body>
</html>
