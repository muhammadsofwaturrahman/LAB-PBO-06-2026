import java.util.Random;

public class Kurir implements Runnable {

    private final Gudang gudang;
    private final Random random = new Random();

    public Kurir(Gudang gudang) {
        this.gudang = gudang;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            try {

                int jumlah = random.nextInt(5) + 1;

                gudang.ambilStok(jumlah,
                        Thread.currentThread().getName());

                Thread.sleep((random.nextInt(2) + 2) * 1000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}