package semester2.ProjekAkhir;

import java.time.LocalDate;
import java.util.*;


public class Perpustakaan {
    ArrayList<Buku> listBuku;
    static Scanner input = new Scanner(System.in);

    public void addBuku(Buku book) {
        listBuku.add(book);
        System.out.println("Buku berhasil ditambahkan");
    }

    public void peminjaman(String id) {
        String idPeminjam = input.nextLine();
        for(Buku b : listBuku) {
            if(b.getID().equals(id)) {
                b.setTanggalPinjam(LocalDate.now());
                b.setKodePeminjam(idPeminjam);
            }
        }
    }

    public void pengembalian() {

    }

    public void denda() {
        
    }
}
