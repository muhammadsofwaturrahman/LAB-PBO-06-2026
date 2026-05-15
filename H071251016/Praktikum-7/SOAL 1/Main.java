import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        Gudang gudang = new Gudang(50);

        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 2; i++) {
            executor.execute(new Pemasok(gudang));
        }

        for (int i = 1; i <= 3; i++) {
            executor.execute(new Kurir(gudang));
        }

        Thread monitoring = new Thread(new Monitoring(gudang));
        monitoring.start();

        try {

            Thread.sleep(15000);

            executor.shutdownNow();
            monitoring.interrupt();

            executor.awaitTermination(5, TimeUnit.SECONDS);

            System.out.println("\nSistem gudang dihentikan.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}