package Database;

import java.sql.*;

public class DatabaseManager {
    private Connection connection;

    public void connect(String url, String username, String password) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found");
            e.printStackTrace();
            return;
        }

        connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connected to the database");
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            System.out.println("Connection to the database closed");
        }
    }


    public boolean registerUser(int rnokpp, int number, String surname, String name, String password) {
        try {
            // Підготовка SQL-запиту для вставки даних нового користувача
            String sql = "INSERT INTO user_info (RNOKPP, phone_number, surname, name, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, rnokpp);
            statement.setInt(2, number);
            statement.setString(3, surname);
            statement.setString(4, name);
            statement.setString(5, password);

            // Виконання запиту
            int rowsInserted = statement.executeUpdate();
            statement.close();

            // Перевірка, чи було успішно вставлено новий рядок
            if (rowsInserted > 0) {
                System.out.println("User registered successfully");
                return true;
            } else {
                System.out.println("Failed to register user");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean userVote(int id_user, int choice) {
        try {
            String sql = "INSERT INTO candidates (id_user, choice) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id_user);
            statement.setInt(2, choice);

            // Виконання запиту
            int rowsInserted = statement.executeUpdate();
            statement.close();

            // Перевірка, чи було успішно вставлено новий рядок
            if (rowsInserted > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getUserId(int rnokpp) {
        try {
            String sql = "SELECT id_user FROM user_info WHERE RNOKPP = ?";

            // Підготовка запиту
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, rnokpp);

            // Виконання запиту
            ResultSet resultSet = preparedStatement.executeQuery();

            // Обробка результатів
            if (resultSet.next()) {
                int user_id = resultSet.getInt("id_user");
                return user_id;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
