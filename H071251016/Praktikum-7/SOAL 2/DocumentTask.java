import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class DocumentTask implements Runnable {

    private final String fileName;
    private final DataProcessor processor;
    private final ConcurrentHashMap<String, ResultData> hasil;
    private final CountDownLatch latch;

    public DocumentTask(String fileName,
                        DataProcessor processor,
                        ConcurrentHashMap<String, ResultData> hasil,
                        CountDownLatch latch) {

        this.fileName = fileName;
        this.processor = processor;
        this.hasil = hasil;
        this.latch = latch;
    }

    @Override
    public void run() {

        long start = System.currentTimeMillis();

        try {

            int jumlahKata = processor.process(fileName);

            long end = System.currentTimeMillis();

            long durasi = end - start;

            String threadName = Thread.currentThread().getName();

            hasil.put(fileName,
                    new ResultData(jumlahKata, threadName, durasi));

            System.out.println("[" + threadName + "] selesai memproses "
                    + fileName + " (" + jumlahKata + " kata)");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            latch.countDown();
        }
    }
}