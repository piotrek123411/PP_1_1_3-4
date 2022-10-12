package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl {
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).getResultList();
        }
    }

    public void deleteAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();

        }
    }
}
