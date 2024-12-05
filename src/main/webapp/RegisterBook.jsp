<%--新規蔵書登録画面--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新規蔵書登録</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<h1>新規蔵書登録</h1>

<!--入力エラーがあればここに表示される-->
<div class="errorMsg">
    <p>${requestScope.titleError}</p>
    <p>${requestScope.authorError}</p>
    <p>${requestScope.publisherError}</p>
    <p>${requestScope.publishDateStrError}</p>
</div>

<!--登録用フォーム。送信先はRegisterBookServlet-->
<form action="./RegisterBookServlet" method="post">
    <p>
        <label>
          タイトル： <input type="text" name="title" value="${requestScope.title}">
        <%--ユーザーが入力したタイトルを保持--%>
        </label>
    </p>
    <p>
        <label>
           著者： <input type="text" name="author" value="${requestScope.author}">
            <%--ユーザーが入力した著者を保持--%>
        </label>
    </p>
    <p>
        <label>
           出版社： <input type="text" name="publisher" value="${requestScope.publisher}">
            <%--ユーザーが入力した出版社を保持--%>
        </label>
    </p>
    <p>
        <label>
           出版日： <input type="date" name="publish_date" value="${requestScope.publishDateStr}">
            <%--ユーザーが入力した出版日を保持--%>
        </label>
    </p>

    <%--フォームを送信--%>
    <input type="submit" value="登録">
</form>

<%--蔵書一覧に戻るリンク--%>
<a href="BookListServlet">蔵書一覧に戻る</a>
</body>
</html>
