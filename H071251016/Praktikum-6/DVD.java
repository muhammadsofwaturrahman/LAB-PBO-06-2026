package tuprak6;

public class DVD extends LibraryItem {
    private int duration;

    public DVD(String title, int itemId, int duration) {
        super(title, itemId);
        this.duration = duration;
    }

    public String getDescription() {
        return "DVD: " + title + ", durasi " + duration + " menit, ID: " + itemId;
    }

    public String borrowItem(int days) {
        if (days > 7)
            throw new IllegalArgumentException("Maksimal pinjam DVD 7 hari");

        if (isBorrowed)
            throw new IllegalArgumentException("DVD sedang dipinjam");

        isBorrowed = true;
        return "Item " + title + " berhasil dipinjam selama " + days + " hari";
    }

    public double calculateFine(int daysLate) {
        return daysLate * 25000;
    }
}