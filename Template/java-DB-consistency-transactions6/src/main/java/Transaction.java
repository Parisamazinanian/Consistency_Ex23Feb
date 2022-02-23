import java.sql.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Transaction {

    public void createTransaction() {
        Connection connection = DBConnection.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            DataManagement.sqlCreateTable(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBConnection.closeConnection(connection);
        }

    }

    public void insertTransaction() {
        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            DataManagement.insertDataIntoWorkTable(stmt);
            DataManagement.insertDataIntoRegistrationTable(stmt);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Person> readTransaction() {
        ArrayList<Person> people = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection();
            Statement stmt = connection.createStatement()) {
            people = DataManagement.getDataFromTable(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public ArrayList<Person> updateTransaction() {
        ArrayList<Person> people = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM Registration where id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = null) {
            stmt.setInt(1, 1);
            people = DataManagement.getDataForUpdate(stmt);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }
}

