package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class MainHibernate {

    public static void main(String[] args) {

        var userDao = new UserDaoHibernateImpl();
        userDao.saveUser("Вто", "рой", (byte) 74);
        userDao.saveUser("Тре", "тий", (byte) 59);
        userDao.saveUser("Четвер", "тый", (byte) 74);
        userDao.saveUser("Пер", "вый", (byte) 78);

        userDao.createUsersTable();

        List<User> users = userDao.getAllUsers();
        users.forEach(System.out::println);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
