
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        String[] dokumen = {
                "Dokumen_A.txt",
                "Dokumen_B.txt",
                "Dokumen_C.txt",
                "Dokumen_D.txt",
                "Dokumen_E.txt",
                "Dokumen_F.txt",
                "Dokumen_G.txt",
                "Dokumen_H.txt",
                "Dokumen_I.txt",
                "Dokumen_J.txt"
        };

        ExecutorService executor = Executors.newFixedThreadPool(4);

        ConcurrentHashMap<String, ResultData> hasil =
                new ConcurrentHashMap<>();

        CountDownLatch latch =
                new CountDownLatch(dokumen.length);

        DataProcessor processor = new DataProcessor();

        for (String doc : dokumen) {

            executor.execute(
                    new DocumentTask(
                            doc,
                            processor,
                            hasil,
                            latch
                    )
            );
        }

        try {

            latch.await();

            System.out.println("\n===== HASIL AKHIR =====");

            int totalKata = 0;
            long totalDurasi = 0;

            System.out.printf("%-20s %-15s %-15s %-10s\n",
                    "Dokumen",
                    "Thread",
                    "Durasi(ms)",
                    "Kata");

            for (String doc : hasil.keySet()) {

                ResultData data = hasil.get(doc);

                System.out.printf("%-20s %-15s %-15d %-10d\n",
                        doc,
                        data.threadName,
                        data.durasi,
                        data.jumlahKata);

                totalKata += data.jumlahKata;
                totalDurasi += data.durasi;
            }

            double rataRata =
                    (double) totalDurasi / dokumen.length;

            System.out.println("\nTotal Kata : " + totalKata);

            System.out.println("Rata-rata Waktu : "
                    + rataRata + " ms");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}