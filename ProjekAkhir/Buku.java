package semester2.ProjekAkhir;

import java.util.*;

public class Buku {
    Map<String, List<String>> judulPengarang;
    String kodePeminjam;

    public Buku(Map<String, List<String>> judulPengarang, String kodePeminjam) {
        this.judulPengarang = judulPengarang;
        this.kodePeminjam = kodePeminjam;
    }
}
