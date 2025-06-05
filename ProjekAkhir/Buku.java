package semester2.ProjekAkhir;

import java.util.*;
import java.time.*;

public class Buku {
    private String idBuku;
    private String judulBuku;
    private TreeSet<String> Pengarang;
    private String kodePeminjam = null;
    private LocalDate tanggalDiPinjam = null;

    public Buku(String idBuku ,String judulBuku, TreeSet<String> Pengarang) {
        this.idBuku = idBuku;
        this.judulBuku = judulBuku;
        this.Pengarang = Pengarang;
    }

    public String getID() {
        return this.idBuku;
    }

    public String getJudul() {
        return this.judulBuku;
    }

    public String getPengarang() {
        String pengarang = "";
        int count = 0;
        for(String s : Pengarang) {
            pengarang += s;
            if(count < (Pengarang.size())) {
                pengarang += ", ";
            }

        }
        return pengarang;
    }

    public String getKodePeminjam() {
        return this.kodePeminjam;
    }
    public LocalDate getTanggalPinjam() {
        return this.tanggalDiPinjam;
    }

    public void setID(String id) {
        this.idBuku = id;
    }

    public void setKodePeminjam(String kode) {
        this.kodePeminjam = kode;
    }
    public void setTanggalPinjam(LocalDate n) {
        this.tanggalDiPinjam = n;
    }




}
