package semester2.ProjekAkhir;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.TreeSet;
import java.awt.event.*;

class BukuInputGUI extends JFrame {
    private JTextField kodeField, judulField, jumlahField;
    private TreeSet<String> pengarangSet = new TreeSet<>();
    private int jumlahPengarang;
    private int pengarangKe = 0;
    Perpustakaan perpustakaan = new Perpustakaan();

    public BukuInputGUI() {
        setTitle("Form Input Buku");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 5, 5));

        add(new JLabel("Kode Buku:"));
        kodeField = new JTextField();
        add(kodeField);

        add(new JLabel("Judul Buku:"));
        judulField = new JTextField();
        add(judulField);

        add(new JLabel("Jumlah Pengarang:"));
        jumlahField = new JTextField();
        add(jumlahField);

        JButton LanjutButton = new JButton("Lanjut");
        JButton SearchButton = new JButton("Search");
        JButton EditButton = new JButton("Edit");
        JButton DeleteButton = new JButton("Delete");
        add(LanjutButton);
        add(SearchButton);
        add(EditButton);
        add(DeleteButton);

        add(new JLabel());

        LanjutButton.addActionListener(e -> prosesLanjut());
        SearchButton.addActionListener(e -> search());
        EditButton.addActionListener(e -> edit());
        DeleteButton.addActionListener(e -> delete());

        setVisible(true);
    }

    private void prosesLanjut() {
        String kode = kodeField.getText().trim();
        String judul = judulField.getText().trim();
        String jumlahText = jumlahField.getText().trim();

        if (kode.isEmpty() || judul.isEmpty() || jumlahText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            jumlahPengarang = Integer.parseInt(jumlahText);
            if (jumlahPengarang <= 0) throw new NumberFormatException();

            pengarangSet.clear();
            pengarangKe = 1;
            inputNamaPengarang(kode, judul);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Jumlah pengarang harus angka > 0!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        perpustakaan.SimpanBuku(kode,judul, pengarangSet);
    }

    public void inputNamaPengarang(String kode, String judul) {
        if (pengarangKe > jumlahPengarang) {
            JOptionPane.showMessageDialog(null, "Buku berhasil disimpan!");
            return;
        }

        String nama = JOptionPane.showInputDialog(null, "Masukkan nama pengarang ke-" + pengarangKe + ":");
        if (nama != null && !nama.isBlank()) {
            pengarangSet.add(nama.trim());
            pengarangKe++;
            inputNamaPengarang(kode, judul);
        } else {
            JOptionPane.showMessageDialog(null, "Nama pengarang tidak boleh kosong.");
            inputNamaPengarang(kode, judul);
        }
    }

    public void search(){
        try{
            String kode = kodeField.getText().trim();
            if(perpustakaan.cariBuku(kode) != null){
                JOptionPane.showMessageDialog(null, perpustakaan.cariBuku(kode));
            }
            else{
                JOptionPane.showMessageDialog(null, "Buku tidak terdaftar!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kode Buku invalid!");
        }
    }
    public void edit() {
        String kode = kodeField.getText().trim();
        String judul = judulField.getText().trim();
        String jumlahText = jumlahField.getText().trim();

        if (kode.isEmpty() || judul.isEmpty() || jumlahText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            jumlahPengarang = Integer.parseInt(jumlahText);
            if (jumlahPengarang <= 0) throw new NumberFormatException();

            pengarangSet.clear();
            pengarangKe = 1;
            editNamaPengarang(kode, judul);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Jumlah pengarang harus angka > 0!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editNamaPengarang(String kode, String judul) {
        if (pengarangKe > jumlahPengarang) {
            JOptionPane.showMessageDialog(null, "Buku berhasil diedit!");
            return;
        }

        String nama = JOptionPane.showInputDialog(null, "Masukkan nama pengarang ke-" + pengarangKe + ":");
        if (nama != null && !nama.isBlank()) {
            pengarangSet.add(nama.trim());
            pengarangKe++;
            inputNamaPengarang(kode, judul);
        } else {
            JOptionPane.showMessageDialog(null, "Nama pengarang tidak boleh kosong.");
            inputNamaPengarang(kode, judul);
        }
        perpustakaan.editBuku(kode,judul, pengarangSet);
    }

    public void delete() {
        try {
            String kode = kodeField.getText().trim();
            perpustakaan.hapusBuku(kode);
            JOptionPane.showMessageDialog(null, "Buku berhasil didelete!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public static void main(String[] args) {
        BukuInputGUI gui = new BukuInputGUI();
    }
}

