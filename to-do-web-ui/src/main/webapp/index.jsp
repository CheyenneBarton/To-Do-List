<%@ page import="com.example.model.Todolist" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>To-Do List</title>
</head>
<body>
<h1>To-Do List</h1>
<form method="post" action="TodoListServlet">
    <label for="item">Item:</label>
    <input type="text" id="item" name="item" required /><br><br>
    <input type="hidden" name="action" value="add" />
    <input type="submit" value="Add Item" />
</form>
<ul>
    <% List<Todolist> listOfItems = (List<Todolist>) request.getAttribute("listOfItems");
        if (listOfItems != null) {
            for (Todolist item : listOfItems) { %>
    <li><%= item.getItem() %></li>
    <% }
    } %>


</ul>
<form method="post" action="TodoListServlet">
    <label for="itemIndex">Index:</label>
    <input type="number" id="itemIndex" name="itemIndex" required /><br><br>
    <input type="hidden" name="action" value="delete" />
    <input type="submit" value="Delete Item" />
</form>
</body>
</html>
