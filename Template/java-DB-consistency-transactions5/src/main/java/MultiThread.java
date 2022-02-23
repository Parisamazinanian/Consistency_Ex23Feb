import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class MultiThread {

    private Transaction transaction = new Transaction();

    Runnable insertTask = () -> transaction.insertTransaction();

    Callable<ArrayList<Person>> readTask = () -> transaction.readTransaction();

    ExecutorService executor = Executors.newFixedThreadPool(2);

    public void runInsert() {
        executor.submit(insertTask);
    }

    public List<Person> runRead() {
        try {
            return executor.submit(readTask).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }
}
