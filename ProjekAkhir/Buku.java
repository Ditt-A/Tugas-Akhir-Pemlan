package semester2.ProjekAkhir;

import java.util.*;
import java.time.*;

public class Buku {
    private String idBuku;
    private String judulBuku;
    private TreeSet<String> Pengarang;
    private String kodePeminjam = null;
    private LocalDate tanggalDiPinjam = null;
    private boolean dipinjam = false;

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

    public boolean getStatus() {
        return this.dipinjam;
    }

    public StringJoiner getPengarang() {
        StringJoiner pengarang = new StringJoiner(",", "[", "]");
        for(String s : Pengarang) {
            pengarang.add(s);
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

    public String toString() {
        return idBuku + ',' + judulBuku + ',' + getPengarang();
    }


}
