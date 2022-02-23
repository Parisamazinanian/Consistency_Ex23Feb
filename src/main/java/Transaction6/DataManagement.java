package Transaction6;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class DataManagement {

    public static void sqlCreateTable(Statement statement) throws SQLException {
        dropTable(statement);
        String sql = "CREATE TABLE IF NOT EXISTS REGISTRATION " +
                "(id IDENTITY NOT NULL PRIMARY KEY, " +
                " name VARCHAR(255), " +
                " lastName VARCHAR(255), " +
                " age INTEGER)";
        statement.executeUpdate(sql);

        sql = "CREATE TABLE IF NOT EXISTS WORK " +
                "(work_id IDENTITY NOT NULL PRIMARY KEY, " +
                " name VARCHAR(255), " +
                " difficulty INTEGER)";
        statement.executeUpdate(sql);
    }

    public static int insertDataIntoRegistrationTable(Statement statement) throws SQLException {
        String sql = "INSERT INTO Registration VALUES (default, 'Zara', 'Ali', 18);" +
                "INSERT INTO Registration VALUES (default, 'Mahnaz', 'Fatma', 25);" +
                "INSERT INTO Registration VALUES (default, 'Zaid', 'Khan', 30);" +
                "INSERT INTO Registration VALUES(default, 'Sumit', 'Mittal', 28)";
        return statement.executeUpdate(sql);
    }

    public static void insertDataIntoWorkTable(Statement statement) throws SQLException {
        String sql = "INSERT INTO WORK VALUES (default, 'Hard Work', 20)";
        statement.executeUpdate(sql);
    }

    public static void insertWrongDataIntoWorkTable(Statement statement) throws SQLException {
        String sql = "INSERT INTO WORK VALUES (default, 'Hard Work', 'Wrong')";
        statement.executeUpdate(sql);
    }

    public static ArrayList<Person> getDataFromTable(Statement statement) throws SQLException {
        String sql = "SELECT id, name, lastName, age FROM Registration";
        ResultSet rs = statement.executeQuery(sql);

        ArrayList<Person> peopleList = new ArrayList<>();

        while(rs.next()) {
            int id  = rs.getInt("id");
            int age = rs.getInt("age");
            String first = rs.getString("name");
            String last = rs.getString("lastName");

            peopleList.add(new Person(id, first, last, age));
        }

        rs.close();

        return peopleList;
    }

    public static ArrayList<Person> getDataForUpdate(PreparedStatement statement) throws SQLException {

        ResultSet rs = statement.executeQuery();
        ArrayList<Person> peopleList = new ArrayList<>();
        while(rs.next()) {
            int id  = rs.getInt("id");
            int age = rs.getInt("age");
            rs.updateString("name", "ChangedName");
            rs.updateRow();
            String first = rs.getString("name");
            String last = rs.getString("lastName");

            peopleList.add(new Person(id, first, last, age));
        }

        rs.close();

        return peopleList;
    }

    private static void dropTable(Statement statement) throws SQLException {
        String sql = "DROP TABLE IF EXISTS REGISTRATION;" +
                "DROP TABLE IF EXISTS WORK";
        statement.executeUpdate(sql);
    }

}