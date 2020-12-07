package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = new Util().getConn().createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users (\n" +
                    "  `user_id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `user_name` VARCHAR(100) NOT NULL,\n" +
                    "  `user_lastname` VARCHAR(100) NOT NULL,\n" +
                    "  `age` INT(3) NULL,\n" +
                    "  PRIMARY KEY (`user_id`),\n" +
                    "  UNIQUE INDEX `id_UNIQUE` (`user_id` ASC) VISIBLE)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Statement statement = new Util().getConn().createStatement()) {
            statement.execute("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void saveUser(String name, String lastName, byte age) {


        try {
            String userData = "INSERT INTO users (user_name, user_lastname, age) VALUES (?,?,?)";
            PreparedStatement preparedStatement = new Util().getConn().prepareStatement(userData);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            if (preparedStatement.execute()) {
                System.out.println("User с именем – " + name + " добавлен в базу данных");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void removeUserById(long id) {

            String str = "DELETE FROM users WHERE id = " + id;
        try {
            new Util().getConn().createStatement().execute(str);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();
        try {
            ResultSet rs = new Util().getConn().createStatement().executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("user_id"));
                user.setName(rs.getString("user_name"));
                user.setLastName(rs.getString("user_lastname"));
                user.setAge(rs.getByte("age"));
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            new Util().getConn().createStatement().execute("TRUNCATE TABLE users");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
