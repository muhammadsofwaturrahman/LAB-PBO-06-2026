
public class Gudang {
    private int stok;
    private final int kapasitasMaksimal;

    public Gudang(int kapasitasMaksimal) {
        this.kapasitasMaksimal = kapasitasMaksimal;
        this.stok = 0;
    }

    public synchronized void tambahStok(int jumlah, String nama)
            throws InterruptedException {

        while (stok + jumlah > kapasitasMaksimal) {
            System.out.println(nama + " menunggu, gudang penuh...");
            wait();
        }

        stok += jumlah;
        System.out.println(nama + " menambah " + jumlah +
                " barang | Stok sekarang: " + stok);

        notifyAll();
    }

    public synchronized void ambilStok(int jumlah, String nama)
            throws InterruptedException {

        while (stok < jumlah) {
            System.out.println(nama + " menunggu, stok kurang...");
            wait();
        }

        stok -= jumlah;
        System.out.println(nama + " mengambil " + jumlah +
                " barang | Stok sekarang: " + stok);

        notifyAll();
    }

    public synchronized int getStok() {
        return stok;
    }

    public int getKapasitasMaksimal() {
        return kapasitasMaksimal;
    }
}