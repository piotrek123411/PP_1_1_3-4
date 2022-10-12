package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class MainHibernate {

    public static void main(String[] args) {

        var userDao = new UserDaoHibernateImpl();
        var user1 = new User("Пер", "вый", (byte) 78);
        var user2 = new User("Вто", "рой", (byte) 74);
        var user3 = new User("Тре", "тий", (byte) 59);
        var user4 = new User("Четвер", "тый", (byte) 74);

        userDao.saveUser(user1);
        userDao.saveUser(user2);
        userDao.saveUser(user3);
        userDao.saveUser(user4);
        List<User> users = userDao.getUsers();
        users.forEach(System.out::println);
        userDao.deleteAll();
    }
}
