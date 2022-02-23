import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
            DataManagement.insertDataIntoTable(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

