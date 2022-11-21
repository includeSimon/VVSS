package com.example.collectiveproject732.Repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.example.collectiveproject732.Model.Task;
import org.hibernate.query.Query;
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

    @Override
    public Optional<Task> delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }

        Transaction transaction = null;
        Optional<Task> result = Optional.empty();

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            result = findOne(Math.toIntExact(id));
            result.ifPresent(session::delete);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction != null)
                transaction.rollback();
        }

        return result;
    }

    @Override
    public Optional<Task> findOne(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }

        Optional<Task> result = Optional.empty();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Task entity = session.createQuery("from Task where id=:id", Task.class)
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .uniqueResult();
            transaction.commit();
            result = entity != null ? Optional.of(entity) : result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction != null)
                transaction.rollback();
        }

        return result;
    }

    @Override
    public Optional<Task> update(Task entity) {
        if (entity == null) {
            throw new IllegalArgumentException();
        }

        Transaction transaction = null;
        Optional<Task> result = Optional.empty();

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction != null)
                transaction.rollback();
            result = Optional.of(entity);
        }

        return result;
    }

}
