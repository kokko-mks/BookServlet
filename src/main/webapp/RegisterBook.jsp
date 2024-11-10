<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新規蔵書登録</title>
</head>
<body>

<h1>新規蔵書登録</h1>

<div class="errorMsg">
    <p>${requestScope.titleError}</p>
    <p>${requestScope.authorError}</p>
    <p>${requestScope.publisherError}</p>
    <p>${requestScope.publishDateStrError}</p>
</div>

<form action="./RegisterBookServlet" method="post">
    <p>
        <label>
          タイトル： <input type="text" name="title" value="${requestScope.title}">
        </label>
    </p>
    <p>
        <label>
           著者： <input type="text" name="author" value="${requestScope.author}">
        </label>
    </p>
    <p>
        <label>
           出版社： <input type="text" name="publisher" value="${requestScope.publisher}">
        </label>
    </p>
    <p>
        <label>
           出版日： <input type="date" name="publish_date" value="${requestScope.publishDateStr}">
        </label>
    </p>

    <input type="submit" value="登録">
</form>

<a href="BookListServlet">蔵書一覧に戻る</a>
</body>
</html>
