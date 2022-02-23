import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
        String sql = "INSERT INTO Registration VALUES (default, 'Zara', 'Ali', 18)";
        statement.executeUpdate(sql);

        sql = "INSERT INTO Registration VALUES (default, 'Mahnaz', 'Fatma', 25)";
        statement.executeUpdate(sql);

        sql = "INSERT INTO Registration VALUES (default, 'Zaid', 'Khan', 30)";
        statement.executeUpdate(sql);

        sql = "INSERT INTO Registration VALUES(default, 'Sumit', 'Mittal', 28)";
        statement.executeUpdate(sql);
    }

    public static ArrayList<Person> getDataFromTable(Statement statement) throws SQLException {
        String sql = "SELECT id, name, lastName, age FROM Registration";
        ResultSet rs = statement.executeQuery(sql);

        ArrayList<Person> peopleList = new ArrayList<>();

        while(rs.next()) {
            //TODO INSERT CODE HERE

            peopleList.add(new Person(id, first, last, age));
        }

        rs.close();

        return peopleList;
    }

    private static void dropTable(Statement statement) throws SQLException {
        String sql = "DROP TABLE IF EXISTS REGISTRATION";
        statement.executeUpdate(sql);
    }

}
