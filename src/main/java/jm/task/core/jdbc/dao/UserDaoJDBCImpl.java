package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoJDBCImpl implements UserDao {
    Connection conn;

    {
        try {
            conn = new Util().getConn();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS users " +
                    "(`user_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "`user_name` VARCHAR(50) NOT NULL, `user_lastname` VARCHAR(50) NOT NULL, " +
                    "`age` TINYINT NOT NULL)");
            conn.commit();
        } catch (SQLException throwables) {
            System.err.println("Исключение в блоке TRY при создании таблицы");
            throwables.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                System.err.println("Исключение в rollback при создании таблицы");
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                System.err.println("Исключение в setAutoCommit при создании таблицы");
            }
        }

    }

    public void dropUsersTable() {
        try {
            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            statement.execute("DROP TABLE IF EXISTS users");
            conn.commit();
        } catch (SQLException throwables) {
            System.err.println("Исключение в блоке TRY при удалении таблицы");
            throwables.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                System.err.println("Исключение в rollback при удалении таблицы");
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                System.err.println("Исключение в setAutoCommit при удалении таблицы");
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try {
            conn.setAutoCommit(false);
            String userData = "INSERT INTO users (user_name, user_lastname, age) VALUES (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(userData);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            if (preparedStatement.execute()) {
                System.out.println("User с именем – " + name + " добавлен в базу данных");
            }
            conn.commit();
        } catch (SQLException throwables) {
            System.err.println("Исключение в блоке TRY при сохранении user");
            throwables.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                System.err.println("Исключение в rollback при сохранении user");
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                System.err.println("Исключение в setAutoCommit при сохранении user");
            }
        }
    }

    public void removeUserById(long id) {

        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id=?");
            ps.setLong(1, id);
            conn.commit();
        } catch (SQLException throwables) {
            System.err.println("Исключение в блоке TRY при removeUserById");
            throwables.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                System.err.println("Исключение в rollback при removeUserById");
                throwables.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                System.err.println("Исключение в setAutoCommit при removeUserById");
                throwables.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();
        try {
            conn.setAutoCommit(false);
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("user_id"));
                user.setName(rs.getString("user_name"));
                user.setLastName(rs.getString("user_lastname"));
                user.setAge(rs.getByte("age"));
                list.add(user);
            }
            conn.commit();
        } catch (SQLException throwables) {
            System.err.println("Исключение в блоке TRY при getAllUsers");
            throwables.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                System.err.println("Исключение в rollback при getAllUsers");
                throwables.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                System.err.println("Исключение в setAutoCommit при getAllUsers");
                throwables.printStackTrace();
            }
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            conn.setAutoCommit(false);
            conn.createStatement().execute("TRUNCATE TABLE users");
            conn.commit();
        } catch (SQLException throwables) {
            System.err.println("Исключение в блоке TRY при cleanUsersTable");
            throwables.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                System.err.println("Исключение в rollback при cleanUsersTable");
                throwables.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                System.err.println("Исключение в setAutoCommit при cleanUsersTable");
                throwables.printStackTrace();
            }
        }
    }
}
