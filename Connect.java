import java.sql.*;

public class Connect {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/Stock";
        String user = "user";
        String password = "password";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to the database");
            
            // Create a new table called USERS
            String createTableSQL = "CREATE TABLE IF NOT EXISTS USERS (ID INT PRIMARY KEY, NAME VARCHAR(255), AGE INT, CITY VARCHAR(255))";
            conn.createStatement().executeUpdate(createTableSQL);
            System.out.println("Table created successfully");

            // Fill the table with 10 random users
            for (int i = 0; i < 10; i++) {
                String insertSQL = "INSERT INTO USERS VALUES (" + i + ", 'User" + i + "', " + (int) (Math.random() * 100) + ", 'City" + i + "')";
                conn.createStatement().executeUpdate(insertSQL);
            }
            System.out.println("Table filled successfully");

            // Print the table
            String selectSQL = "SELECT * FROM USERS";
            ResultSet resultSet = conn.createStatement().executeQuery(selectSQL);
            while (resultSet.next()) {
                // Process each row from the result set if needed
                System.out.println("ID: " + resultSet.getInt("ID") + ", Name: " + resultSet.getString("NAME") + ", Age: " + resultSet.getInt("AGE") + ", City: " + resultSet.getString("CITY"));
            }
            System.out.println("Table printed successfully");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database");
            e.printStackTrace();
        }
    }
}
