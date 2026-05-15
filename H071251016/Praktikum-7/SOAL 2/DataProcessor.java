
import java.util.Random;

public class DataProcessor {

    private final Random random = new Random();

    public int process(String fileName) throws InterruptedException {

        int delay = random.nextInt(1501) + 500;

        Thread.sleep(delay);

        return random.nextInt(900) + 100;
    }
}