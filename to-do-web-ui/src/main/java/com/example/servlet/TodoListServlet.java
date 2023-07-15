package com.example.servlet;

import java.io.*;
import java.util.List;

import com.example.dao.TodoListDAO;
import com.example.model.Todolist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "TodoListServlet", urlPatterns = "/TodoListServlet")
public class TodoListServlet extends HttpServlet {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;
    private TodoListDAO todoListDAO;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("Todolist");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        todoListDAO = new TodoListDAO(entityManager);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Todolist> listOfItems = todoListDAO.getAllItems();
        request.setAttribute("listOfItems", listOfItems);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String item = request.getParameter("item");
            todoListDAO.addItem(new Todolist(item));
        } else if ("delete".equals(action)) {
            int itemIndex = Integer.parseInt(request.getParameter("itemIndex")); //parameter is received
            todoListDAO.deleteItem(itemIndex);
        }
        doGet(request, response);
    }

    @Override
    public void destroy() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
