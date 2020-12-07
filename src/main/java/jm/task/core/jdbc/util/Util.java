package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util{
    private final static String URL = "jdbc:mysql://localhost:3306/dbtest?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC";
    private final static String USER = "root";
    private final static String PASSWORD = "root";

    public Connection getConn() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }


}
