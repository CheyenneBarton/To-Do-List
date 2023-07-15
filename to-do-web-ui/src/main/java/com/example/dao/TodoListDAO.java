package com.example.dao;

import com.example.model.Todolist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class TodoListDAO {
    private EntityManager entityManager;

    public TodoListDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Todolist> getAllItems() {
        TypedQuery<Todolist> query = entityManager.createQuery("SELECT i FROM Todolist i", Todolist.class);
        return query.getResultList();
    }
    public void addItem(Todolist item) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(item);
        transaction.commit();
    }
    public void deleteItem(int itemId) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Todolist item = entityManager.find(Todolist.class, itemId);
        if (item != null) {
            entityManager.remove(item);
        }
        transaction.commit();
    }
}