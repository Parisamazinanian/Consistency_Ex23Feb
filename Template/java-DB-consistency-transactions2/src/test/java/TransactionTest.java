
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TransactionTest {

    @Test
    public void test_EX_2() {
        Transaction transaction = new Transaction();
        transaction.createTransaction();
        transaction.insertTransaction();
        ArrayList<Person> people = transaction.readTransaction();
        Assert.assertEquals(8, people.size());
    }

}
