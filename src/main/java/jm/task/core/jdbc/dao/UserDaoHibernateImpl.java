//package jm.task.core.jdbc.dao;
//
//
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//
//
//import java.util.List;
//
//public class UserDaoHibernateImpl implements UserDao {
//
//    public UserDaoHibernateImpl() {
//
//    }
//
//
//    @Override
//    public void createUsersTable() {
//        SessionFactory sessionFactory = new Util().getSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        String sql = "CREATE TABLE IF NOT EXISTS users " +
//                "(`user_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
//                "`user_name` VARCHAR(50) NOT NULL, `user_lastname` VARCHAR(50) NOT NULL, " +
//                "`age` TINYINT NOT NULL)";
//
//        session.createSQLQuery(sql).addEntity(User.class);
//
//        transaction.commit();
//        session.close();
//
//    }
//
//    @Override
//    public void dropUsersTable() {
//        SessionFactory sessionFactory = new Util().getSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        String sql = "DROP TABLE IF EXISTS users";
//
//        session.createSQLQuery(sql).addEntity(User.class);
//
//        transaction.commit();
//        session.close();
//
//    }
//
//    @Override
//    public void saveUser(String name, String lastName, byte age) {
//
//    }
//
//    @Override
//    public void removeUserById(long id) {
//
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        return null;
//    }
//
//    @Override
//    public void cleanUsersTable() {
//
//    }
//}
