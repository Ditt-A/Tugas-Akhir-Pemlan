package semester2.ProjekAkhir;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


public class Perpustakaan {
    ArrayList<Buku> listBuku;
    ArrayList<Pengguna> listPengguna;
    HashMap<String, String> daftarPinjam;
    static Scanner input = new Scanner(System.in);

    public Perpustakaan() {
        listBuku = new ArrayList<>();
        listPengguna = new ArrayList<>();
        daftarPinjam = new HashMap<>();
    }
    //Method untuk Tab Buku
    public void SimpanBuku(String kode, String judul, TreeSet<String> pengarang){
        listBuku.add(new Buku(kode, judul, pengarang));
    }

    public String cariBuku(String kode){
        for (Buku buku : listBuku) {
            if(buku.getID().equals(kode)){
                return "Kode Buku: "+ buku.getID() + "Judul: "+ buku.getJudul()+ "Pengarang: "+ buku.getPengarang();
            }
        }
        return null;
    }

    public void editBuku(String kode, String judul, TreeSet<String> pengarang){
        for(Buku buku : listBuku){
            if(buku.getID().equals(kode)){
                buku.setJudul(judul);
                buku.setPengarang(pengarang);
            }
        }
    }
    public void hapusBuku(String kode){
        for(Buku buku : listBuku){
            if(buku.getID().equals(kode)){
                listBuku.remove(buku);
            }
        }
    }
    //Sampai Sini

    //Method untuk Tab Pengguna


}
