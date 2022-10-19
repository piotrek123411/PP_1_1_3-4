package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import static jm.task.core.jdbc.util.HibernateUtil.getInstance;

public class UserDaoHibernateImpl implements UserDao {

    public static final SessionFactory sessionFactory = (SessionFactory) getInstance().getSessionFactory();

//    public void saveUser(User user) {
//        try (Session session = sessionFactory.openSession()) {
//            session.save(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
////    public void saveUser(User user) {
////        Session session = sessionFactory.openSession();
////        session.save(user);
////    }
//
//
//
//    public List<User> getUsers() {
//        try (Session session = (Session) sessionFactory.openSession().beginTransaction()) {
//            return session.createSQLQuery("SELECT * FROM users").getResultList();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return List.of(null);
//    }
//
//    public void deleteAll() {
//        Transaction transaction = null;
//        try (Session session = (Session) sessionFactory.openSession().beginTransaction()) {
//            transaction = session.beginTransaction();
//            session.createQuery("DELETE FROM User").executeUpdate();
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }

    @Override
    public void createUsersTable() {
        try(Session session = sessionFactory.openSession()) {
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)").addEntity(User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Transaction transaction = null;
//        try (Session session = (Session) sessionFactory.openSession().beginTransaction()) {
//            transaction = session.getTransaction();
//            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
//                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
//                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
//                    "age TINYINT NOT NULL)").addEntity(User.class);
//            query.executeUpdate();
//            transaction.commit();
//        }  catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            session.createSQLQuery("DROP TABLE IF EXISTS users").addEntity(User.class);

        }  catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Transaction transaction = null;
//        try (Session session = (Session) sessionFactory.openSession().beginTransaction()) {
//            transaction = session.beginTransaction();
//            User user = new User();
//            user.setName(name);
//            user.setLastName(lastName);
//            user.setAge(age);
//            session.save(user);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {

            User user = session.load(User.class, id);
            session.delete(user);

            System.out.println("Deleted: " + user.getId() + " user");
        }  catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            session.createSQLQuery("select * from users").getResultList();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
        session.createSQLQuery("TRUNCATE TABLE users ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
