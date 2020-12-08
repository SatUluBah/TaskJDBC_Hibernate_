package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


public class Util{
    private final static String URL = "jdbc:mysql://localhost:3306/dbtest?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC";
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";


    public Connection getConn() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

//    private static final SessionFactory sessionFactory = buildSessionFactory();
//
//    private static SessionFactory buildSessionFactory() {
//        try {
//            return new Configuration().configure().buildSessionFactory();
//        }
//        catch (Throwable ex) {
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    public static void shutdown() {
//        getSessionFactory().close();
//    }



//    public SessionFactory getSessionFactory() {
//
//        Configuration configuration = new Configuration();
//        Properties settings = new Properties();
//
//        settings.put(Environment.DRIVER, DRIVER);
//        settings.put(Environment.URL, URL);
//        settings.put(Environment.USER, USER);
//        settings.put(Environment.PASS, PASSWORD);
//        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
//        settings.put(Environment.SHOW_SQL, "true");
//        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//        settings.put(Environment.HBM2DDL_AUTO, "create-drop");
//
//        configuration.setProperties(settings);
//        configuration.addAnnotatedClass(User.class);
//
//        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                .applySettings(configuration.getProperties()).build();
//
//        return configuration.buildSessionFactory(serviceRegistry);
//    }
}
