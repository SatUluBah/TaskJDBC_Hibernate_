package jm.task.core.jdbc.util;

import java.io.File;
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
    private final static String FILENAME = "src/main/java/recources/hibernate.cfg.xml";



    public Connection getConn() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }


    public static SessionFactory getSessionFactory() {
        Configuration config = new Configuration().configure(new File(FILENAME));
        config.addAnnotatedClass(User.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties());
        return config.buildSessionFactory(builder.build());
    }


}
