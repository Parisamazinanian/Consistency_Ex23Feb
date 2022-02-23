package Transaction6;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TransactionTest {

    @Test
    public void test_EX_6() {
        Transaction transaction = new Transaction();
        MultiThread multiThread = new MultiThread();
        transaction.createTransaction();
        multiThread.runInsert();
        List<Person> people = multiThread.runUpdate();
        List<Person> people2 = multiThread.runRead();
        Person changedPerson = people2.stream().filter(person -> "ChangedName".equals(person.getName())).findFirst().get();
        Assert.assertEquals(4, people2.size());
        Assert.assertEquals(people.get(0).getName(), changedPerson.getName());
        Assert.assertEquals(1, countRecords());
    }

    private int countRecords() {
        int total = 0;
        try (Connection connection = DBConnection.getConnection(); Statement stmt = connection.createStatement()) {
            String sql = "SELECT COUNT(*) AS total FROM WORK ";
            ResultSet count = stmt.executeQuery(sql);
            while(count.next()){
                total = count.getInt("total");
            }
            return total;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

}
