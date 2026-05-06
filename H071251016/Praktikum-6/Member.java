package tuprak6;
import java.util.ArrayList;

public class Member {
    private String name;
    private int memberId;
    private ArrayList<LibraryItem> borrowedItems;

    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
        borrowedItems = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }

    public String borrow(LibraryItem item, int days) {
        if (item.isBorrowed())
            throw new IllegalStateException("Item sedang dipinjam");

        borrowedItems.add(item);
        return item.borrowItem(days);
    }

    public String returnItem(LibraryItem item, int daysLate) {
        borrowedItems.remove(item);
        item.returnItem();

        double fine = item.calculateFine(daysLate);

        return "Item " + item.getTitle()
                + " berhasil dikembalikan dengan denda Rp " + fine;
    }

    public void getBorrowedItems() {
        if (borrowedItems.isEmpty()) {
            System.out.println("Tidak ada item dipinjam");
            return;
        }

        for (LibraryItem item : borrowedItems) {
            System.out.println(item.getDescription());
        }
    }
}