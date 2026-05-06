package tuprak6;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Library {
    private ArrayList<LibraryItem> items;
    private ArrayList<Member> members;
    private LibraryLogger logger;

    public Library() {
        items = new ArrayList<>();
        members = new ArrayList<>();
        logger = new LibraryLogger();
    }

    public String addItem(LibraryItem item) {
        items.add(item);
        return item.getTitle() + " berhasil ditambahkan";
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public LibraryItem findItemById(int id) {
        for (LibraryItem item : items) {
            if (item.getItemId() == id)
                return item;
        }

        throw new NoSuchElementException("Item tidak ditemukan");
    }

    public Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getMemberId() == id)
                return member;
        }

        throw new NoSuchElementException("Member tidak ditemukan");
    }

    public String getLibraryStatus() {
        String result = "";

        for (LibraryItem item : items) {
            result += item.getDescription() + " | ";

            if (item.isBorrowed())
                result += "Dipinjam\n";
            else
                result += "Tersedia\n";
        }

        return result;
    }

    public LibraryLogger getLogger() {
        return logger;
    }
}
