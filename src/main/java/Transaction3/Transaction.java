package Transaction3;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
        Connection connection = DBConnection.getConnection();
        try (Statement stmt = connection.createStatement()) {
            DataManagement.insertDataIntoWorkTable(stmt);
            int recourdCOunt = DataManagement.insertDataIntoRegistrationTable(stmt);
            if (recourdCOunt < 5) {
                connection.rollback();

            }
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
}