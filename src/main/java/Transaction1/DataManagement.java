package Transaction1;

import java.sql.SQLException;
import java.sql.Statement;

public class DataManagement {

    public static void sqlCreateTable(Statement statement) throws SQLException {
        dropTable(statement);
        String sql = "CREATE TABLE IF NOT EXISTS REGISTRATION " +
                "(id IDENTITY NOT NULL PRIMARY KEY, " +
                " name VARCHAR(255), " +
                " lastName VARCHAR(255), " +
                " age INTEGER)";
        statement.executeUpdate(sql);
    }

    public static void insertDataIntoTable(Statement statement) throws SQLException {
        String sql = "INSERT INTO Registration VALUES (default, 'Pari', 'Mazi', 30)";
        statement.executeUpdate(sql);
        sql = "INSERT INTO Registration VALUES (default, 'Sara', 'Salami', 18)";
        statement.executeUpdate(sql);
        sql = "INSERT INTO Registration VALUES (default, 'Ali', 'Sina', 25)";
        statement.executeUpdate(sql);
        sql = "INSERT INTO Registration VALUES (default, 'Rebecca', 'Smith', 35)";
        statement.executeUpdate(sql);
    }

    private static void dropTable(Statement statement) throws SQLException {
        String sql = "DROP TABLE IF EXISTS REGISTRATION";
        statement.executeUpdate(sql);
    }

}
