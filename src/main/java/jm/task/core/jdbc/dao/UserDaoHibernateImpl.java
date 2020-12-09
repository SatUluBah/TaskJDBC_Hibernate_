package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import org.hibernate.exception.SQLGrammarException;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(`user_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "`user_name` VARCHAR(50) NOT NULL, `user_lastname` VARCHAR(50) NOT NULL, " +
                "`age` TINYINT NOT NULL)";
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (SQLGrammarException e) {
            System.err.println(e.getCause().getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        String sql = "DROP TABLE IF EXISTS users";
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (SQLGrammarException e) {
            System.out.println(e.getCause().getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.save(user);
            transaction.commit();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println("User с именем " + name + " добавлен в базу данных.");
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.createQuery("DELETE FROM User WHERE id = " + id).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
             users = session.createQuery("FROM User").list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
        session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
        transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
}
