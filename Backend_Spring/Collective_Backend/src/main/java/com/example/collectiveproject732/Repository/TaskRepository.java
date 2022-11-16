package com.example.collectiveproject732.Repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.example.collectiveproject732.Model.Task;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TaskRepository implements ITaskRepository {

    protected static SessionFactory sessionFactory;

    @Override
    public Optional<Task> save(Task task) {
        if (task == null) {
            throw new IllegalArgumentException();
        }

        Transaction transaction = null;
        Optional<Task> result = Optional.empty();

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(task);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

            if (transaction != null)
                transaction.rollback();

            result = Optional.of(task);
        }

        return result;
    }
}
