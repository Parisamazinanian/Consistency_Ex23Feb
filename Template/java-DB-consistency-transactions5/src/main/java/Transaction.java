import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
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
            sleep(2000);
            connection.commit();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Person> readTransaction() {
        ArrayList<Person> people = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection();
            Statement stmt = connection.createStatement()) {
            sleep(100);
            people = DataManagement.getDataFromTable(stmt);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
        return people;
    }
}

