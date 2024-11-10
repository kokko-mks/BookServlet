<%@page import="io.github.demo.DAO.Book" %>
<%@page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Book> books = (List<Book>) request.getAttribute("books"); %>
<html>
<head>
    <title>蔵書一覧</title>
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

<p class="errorMsg">${requestScope.errorMsg}</p>

<p></p>

<button>
    <a href="./BookListServlet">一覧を表示</a>
</button>

<table border="1">
    <tr>
        <th>ID</th>
        <th>タイトル</th>
        <th>著者</th>
        <th>出版社</th>
        <th>出版日</th>
    </tr>



<% if (books != null && !books.isEmpty()) {
    for (Book book : books) { %>
    <tr>
        <td><%= book.getId() %></td>
        <td><a href="./EditBookServlet?id=<%= book.getId() %>"><%= book.getTitle() %></a></td>
        <td><%= book.getAuthor()%></td>
        <td><%= book.getPublisher()%></td>
        <td><%= book.getPublishDate()%></td>
    </tr>
    <% }} else {%>
        <tr>
            <td colspan="5">蔵書が見つかりませんでした。</td>
        </tr>
    <% } %>
</table>
</body>
</html>
