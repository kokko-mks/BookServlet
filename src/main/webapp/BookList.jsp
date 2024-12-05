<%--蔵書一覧画面--%>
<%@page import="io.github.demo.DAO.Book" %>
<%@page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--リクエストスコープからbooks(蔵書リストを取得--%>
<% List<Book> books = (List<Book>) request.getAttribute("books"); %>
<html>
<head>
    <title>蔵書一覧</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h1>蔵書一覧</h1>
<button>
    <a href="./ShowRegisterBookServlet">新規蔵書登録</a>
</button>

<form action="./BookListServlet" method="post">

    <p>
        <label>
            タイトル：<input type="text" name="title" value="${requestScope.title}" placeholder="タイトルで検索">
        </label>
    </p>
    <p>
        <label>
            著者：<input type="text" name="author" value="${requestScope.author}" placeholder="著者で検索">
        </label>
    </p>

    <p>
        <label>
            出版社：<input type="text" name="publisher" value="${requestScope.publisher}" placeholder="出版社で検索">
        </label>
    </p>

    <input type="submit" value="検索">

</form>

<!-- エラーメッセージの表示 -->
<p class="errorMsg">${requestScope.errorMsg}</p>

<p></p>

<button>
    <!-- 全蔵書を表示するリンク -->
    <a href="./BookListServlet">一覧を表示</a>
</button>

<table border="1">
    <!-- 蔵書情報を表示するテーブル -->
    <tr>
        <th>ID</th>
        <th>タイトル</th>
        <th>著者</th>
        <th>出版社</th>
        <th>出版日</th>
    </tr>



<%--蔵書リストが存在し空ではない場合に表示--%>
    <% if (books != null && !books.isEmpty()) {
    for (Book book : books) { %>
    <tr>
        <!-- 蔵書情報を各列に表示 -->
        <td><%= book.getId() %></td>
        <td><a href="./EditBookServlet?id=<%= book.getId() %>"><%= book.getTitle() %></a></td>
        <td><%= book.getAuthor()%></td>
        <td><%= book.getPublisher()%></td>
        <td><%= book.getPublishDate()%></td>
    </tr>
    <% }} else {%>
    <%--蔵書リストが空の場合の処理--%>
        <tr>
            <td colspan="5">蔵書が見つかりませんでした。</td>
        </tr>
    <% } %>
</table>
</body>
</html>
