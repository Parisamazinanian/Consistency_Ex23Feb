package Transaction1;

import Transaction1.DBConnection;
import Transaction1.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionTest {

    @Test
    public void test_EX_1() {
        Transaction transaction = new Transaction();
        transaction.createTransaction();
        transaction.insertTransaction();
        int total = countRecords();
        Assert.assertEquals(4, total);
    }

    private int countRecords() {
        int total = 0;
        try (Connection connection = DBConnection.getConnection(); Statement stmt = connection.createStatement()) {
            String sql = "SELECT COUNT(*) AS total FROM REGISTRATION ";
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
