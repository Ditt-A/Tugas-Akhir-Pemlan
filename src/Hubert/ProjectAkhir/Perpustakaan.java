package semester2.src.Hubert.ProjectAkhir;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;


public class Perpustakaan {
    ArrayList<Buku> listBuku;
    ArrayList<Mahasiswa> listMahasiswa;
    HashMap<String, String> daftarPinjam;

    public Perpustakaan() {
        listBuku = new ArrayList<>();
        listMahasiswa = new ArrayList<>();
        daftarPinjam = new HashMap<>();
    }

    //Method untuk Tab Buku
    public void simpanBuku(String kode, String judul, TreeSet<String> pengarang){
        listBuku.add(new Buku(kode, judul, pengarang));
    }

    public String cariBuku(String kode){
        for (Buku buku : listBuku) {
            if(buku.getID().equals(kode)){
                return "Kode Buku: "+ buku.getID() + " Judul: "+ buku.getJudul()+ " Pengarang: "+ buku.getPengarang();
            }
        }
        return null;
    }

    public void editBuku(String kode, String judul, TreeSet<String> pengarang) throws Exception{
        for(Buku buku : listBuku){
            if(buku.getID().equals(kode)){
                buku.setJudul(judul);
                buku.setPengarang(pengarang);
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

                String[] parts = currentLine.split(",");

                if (parts.length > 0 && parts[0].equals(kode)) {
                    String pengarangGabung = String.join(",", pengarang);
                    String lineBaru = kode + ";" + judul + ";" + pengarangGabung;
                    writer.write(lineBaru);
                } else {
                    writer.write(currentLine);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        } else {
            throw new Exception("Buku gagal diedit!");
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
            throw new Exception("Buku gagal dihapus!");
        }
    }
    //Sampai Sini

    //Method untuk Tab Mahasiswa
    public void simpanMahasiswa(String nim, String nama, String prodi){
        listMahasiswa.add(new Mahasiswa(nim, nama, prodi));
    }
    public String cariMahasiswa(String nim){
        for (Mahasiswa pengguna : listMahasiswa) {
            if(pengguna.getNim().equals(nim)){
                return "NIM: "+ pengguna.getNim()+" Nama: "+pengguna.getNama()+" Prodi: "+pengguna.getProdi();
            }
        }
        return null;
    }

    public void editMahasiswa(String nim, String nama, String prodi) throws Exception{
        for(Mahasiswa pengguna : listMahasiswa){
            if(pengguna.getNim().equals(nim)){
                pengguna.setNama(nama);
                pengguna.setProdi(prodi);
            }
        }
        File inputFile = new File("dataUser.txt");
        File tempFile = new File("userTemp.txt");

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {

                String[] parts = currentLine.split(",");

                if (parts.length > 0 && parts[0].equals(nim)) {
                    String lineBaru = nim + ";" + nama + ";" + prodi;
                    writer.write(lineBaru);
                } else {
                    writer.write(currentLine);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        } else {
            throw new Exception("User gagal diedit!");
        }
    }

    public void hapusMahasiswa(String nim) throws Exception{
        for (Mahasiswa pengguna : listMahasiswa) {
            if(pengguna.getNim().equals(nim)){
                listMahasiswa.remove(pengguna);
            }
        }
        File inputFile = new File("dataUser.txt");
        File tempFile = new File("userTemp.txt");

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {

                String[] parts = currentLine.split(";");
                if (parts.length > 0 && parts[0].equals(nim)) {
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
            throw new Exception("User gagal dihapus");
        }
    }
    //Sampai Sini

}
