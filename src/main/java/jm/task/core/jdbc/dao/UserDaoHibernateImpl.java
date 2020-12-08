package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import org.hibernate.exception.SQLGrammarException;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    SessionFactory sessionFactory = Util.getSessionFactory();


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(`user_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "`user_name` VARCHAR(50) NOT NULL, `user_lastname` VARCHAR(50) NOT NULL, " +
                "`age` TINYINT NOT NULL)";
        try {
            session.createSQLQuery(sql).executeUpdate();
        } catch (SQLGrammarException e) {
            System.err.println(e.getCause().getMessage());
        }
        transaction.commit();
        session.close();

    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS users";
        try {
            session.createSQLQuery(sql).executeUpdate();
        } catch (SQLGrammarException e) {
            System.out.println(e.getCause().getMessage());
        }
        transaction.commit();
        session.close();


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        transaction.commit();
        session.close();
        System.out.println("User с именем " + name + " добавлен в базу данных.");
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User WHERE id = " + id).executeUpdate();
        transaction.commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
        transaction.commit();
        session.close();

    }
}
