<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.model.Todolist" %>
<%@ page import="com.example.dao.TodoListDAO" %>
<%@ page import="jakarta.persistence.EntityManagerFactory" %>
<%@ page import="jakarta.persistence.EntityManager" %>
<%@ page import="jakarta.persistence.Persistence" %>


<%
<%
    String itemText = request.getParameter("item");
    if (itemText != null && !itemText.isEmpty()) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("Todolist");
            entityManager = entityManagerFactory.createEntityManager();
            TodoListDAO todoListDAO = new TodoListDAO(entityManager);

            Todolist item = new Todolist(itemText);
            todoListDAO.addItem(item);

            out.println("Item '" + itemText + "' has been added.");
        } catch (Exception e) {
            e.printStackTrace();        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    } else {
        out.println("Invalid item.");
    }
%>
%>

<!DOCTYPE html>
<html>
<head>
    <title>Item Added</title>
</head>
<body>
<h1>Item Added Successfully</h1>
<p>Title: <%= item.getItem() %></p>

</body>
</html>

