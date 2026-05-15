public class Monitoring implements Runnable {

    private final Gudang gudang;

    public Monitoring(Gudang gudang) {
        this.gudang = gudang;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {

            int stok = gudang.getStok();
            int kapasitas = gudang.getKapasitasMaksimal();

            System.out.println("\n===== STATUS GUDANG =====");
            System.out.println("Stok : " + stok + "/" + kapasitas);

            int persen = (stok * 20) / kapasitas;

            System.out.print("[");

            for (int i = 0; i < 20; i++) {
                if (i < persen) {
                    System.out.print("#");
                } else {
                    System.out.print("-");
                }
            }

            System.out.println("]");
            System.out.println("=========================\n");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}