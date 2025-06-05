package semester2.ProjekAkhir;

public class Pengguna {
    private String nim;
    private String nama;
    private String prodi;
    private long denda = 0;

    public Pengguna(String nim, String nama, String prodi) {
        this.nim = nim;
        this.nama = nama;
        this.prodi = prodi;
    }

    public String getNim() {
        return this.nim;
    }

    public String getNama() {
        return this.nama;
    }

    public String getProdi() {
        return this.prodi;
    }

    public String toString() {
        return nim + "," + nama + "," + prodi;
    }

    public static Pengguna fromString(String s) {
        String[] a = s.split(",");
        return new Pengguna(a[0], a[1], a[2]);
    }

}
