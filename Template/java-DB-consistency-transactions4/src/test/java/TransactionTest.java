
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TransactionTest {

    @Test
    public void test_EX_4() {
        Transaction transaction = new Transaction();
        transaction.createTransaction();
        transaction.insertTransaction();
        ArrayList<Person> people = transaction.readTransaction();
        Assert.assertEquals(4, people.size());
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
