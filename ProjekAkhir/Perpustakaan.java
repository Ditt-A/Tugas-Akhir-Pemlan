package semester2.ProjekAkhir;

import javax.swing.*;
import java.io.*;
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
    public void hapusBuku(String kode) throws Exception{
        for(Buku buku : listBuku){
            if(buku.getID().equals(kode)){
                listBuku.remove(buku);
            }
        }
        File inputFile = new File("dataBuku.txt");
        File tempFile = new File("bukuTemp.txt");

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {

                String[] parts = currentLine.split(";");
                if (parts.length > 0 && parts[0].equals(kode)) {
                    continue;
                }
                writer.write(currentLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        } else {
            throw new Exception("Buku gagal dihapus");
        }
    }
    //Sampai Sini

    //Method untuk Tab Pengguna


}
