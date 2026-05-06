package tuprak6;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Library library = new Library();

        int pilih;

        do {
             System.out.println("\n=== Sistem Manajemen Perpustakaan ===");
            System.out.println("1. Tambah Item");
            System.out.println("2. Tambah Anggota");
            System.out.println("3. Pinjam Item");
            System.out.println("4. Kembalikan Item");
            System.out.println("5. Lihat Status Perpustakaan");
            System.out.println("6. Lihat Log Aktivitas");
            System.out.println("7. Lihat Item yang Dipinjam Anggota");
            System.out.println("8. Keluar");
            System.out.print("Pilih: ");
            pilih = input.nextInt();

            switch (pilih) {
                case 1:
                    System.out.println("\n1. Buku");
                    System.out.println("2. DVD");
                    System.out.print("Pilih jenis item: ");
                    int jenis = input.nextInt();
                    input.nextLine();

                    

                    if (jenis == 1) {
                    System.out.print("Masukkan judul buku: ");
                    String judulBuku = input.next();

                    System.out.print("Masukkan ID buku: ");
                    int idBuku = input.nextInt();

                    System.out.print("Masukkan penulis buku: ");
                    String penulisBuku = input.next();

                    library.addItem(new Book(judulBuku, idBuku, penulisBuku));
                    System.out.println("Buku ditambahkan");
                    break;
                    } 
                    else if (jenis == 2) {
                        System.out.print("Masukkan judul DVD: ");
                    String judulDVD = input.next();

                    System.out.print("Masukkan ID DVD: ");
                    int idDVD = input.nextInt();

                    System.out.print("Masukkan durasi DVD: ");
                    int durasiDVD = input.nextInt();

                    library.addItem(new DVD(judulDVD, idDVD, durasiDVD));
                    System.out.println("DVD ditambahkan");
                    break;
                    }

                 case 2:
                    input.nextLine();

                    System.out.print("Nama Member: ");
                    String memberName = input.nextLine();

                    System.out.print("ID Member: ");
                    int memberId = input.nextInt();

                    Member member = new Member(memberName, memberId);

                    library.addMember(member);

                    System.out.println("Member berhasil ditambahkan");
                    break;

                // PINJAM ITEM
                case 3:
                    System.out.print("ID Member: ");
                    int borrowMemberId = input.nextInt();

                    System.out.print("ID Item: ");
                    int borrowItemId = input.nextInt();

                    System.out.print("Jumlah hari pinjam: ");
                    int days = input.nextInt();

                    try {
                        Member m = library.findMemberById(borrowMemberId);
                        LibraryItem item =
                                library.findItemById(borrowItemId);

                        String result = m.borrow(item, days);

                        library.getLogger().logActivity(
                                item.getTitle()
                                + " dipinjam oleh "
                                + m.getName()
                        );

                        System.out.println(result);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                // KEMBALIKAN ITEM
                case 4:
                    System.out.print("ID Member: ");
                    int returnMemberId = input.nextInt();

                    System.out.print("ID Item: ");
                    int returnItemId = input.nextInt();

                    System.out.print("Hari keterlambatan: ");
                    int lateDays = input.nextInt();

                    try {
                        Member m = library.findMemberById(returnMemberId);

                        LibraryItem item =
                                library.findItemById(returnItemId);

                        String result =
                                m.returnItem(item, lateDays);

                        library.getLogger().logActivity(
                                item.getTitle()
                                + " dikembalikan oleh "
                                + m.getName()
                        );

                        System.out.println(result);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                // STATUS PERPUSTAKAAN
                case 5:
                    System.out.println("\n=== Status Perpustakaan ===");
                    System.out.println(library.getLibraryStatus());
                    break;

                // LOG AKTIVITAS
                case 6:
                    System.out.println("\n=== Log Aktivitas ===");
                    System.out.println(
                            library.getLogger().getLogs()
                    );
                    break;

                // ITEM DIPINJAM MEMBER
                case 7:
                    System.out.print("ID Member: ");
                    int memberCheckId = input.nextInt();

                    try {
                        Member m =
                                library.findMemberById(memberCheckId);

                        m.getBorrowedItems();

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                // KELUAR
                case 8:
                    System.out.println("Program selesai");
                    break;

                default:
                    System.out.println("Menu tidak tersedia");
            }

        } while (pilih != 8);

        input.close();
    }
}