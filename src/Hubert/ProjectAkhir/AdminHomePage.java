package semester2.src.Hubert.ProjectAkhir;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class AdminHomePage extends JFrame{
    Perpustakaan perpustakaan = new Perpustakaan();
    private JTable tableBuku;
    private JTable tableTransaksi;
    private DefaultTableModel modelBuku;
    private DefaultTableModel modelTransaksi;
    private JTextField tfKode, tfJudul, tfPengarang, tfJumlah;
    private TreeSet<String> pengarangSet = new TreeSet<>();
    private int jumlah =0;
    private int pengarangKe = 0;
    public AdminHomePage() {
        setTitle("Halaman Utama Admin");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Tab 1: Manajemen Buku
        JPanel panelBuku = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        tfKode = new JTextField(15);
        tfJudul = new JTextField(15);
        tfPengarang = new JTextField(15);
        tfJumlah = new JTextField(15);

        String[] labels = {"Kode Buku", "Judul", "Jumlah Pengarang", "Jumlah"};
        JTextField[] fields = {tfKode, tfJudul, tfPengarang, tfJumlah};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            formPanel.add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1;
            formPanel.add(fields[i], gbc);
        }


        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add");
        JButton btnEdit = new JButton("Edit");
        JButton btnSearch = new JButton("Search");
        JButton btnDelete = new JButton("Delete");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnDelete);

        modelBuku = new DefaultTableModel(new Object[]{"Kode", "Judul", "Pengarang", "Jumlah"}, 0);
        tableBuku = new JTable(modelBuku);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(buttonPanel, BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(tableBuku), BorderLayout.CENTER);

        panelBuku.add(formPanel, BorderLayout.NORTH);
        panelBuku.add(centerPanel, BorderLayout.CENTER);

        // Tab 2: Transaksi Mahasiswa
        JPanel panelTransaksi = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField tfCari = new JTextField(20);
        JButton btnCari = new JButton("Cari");
        topPanel.add(new JLabel("Cari:"));
        topPanel.add(tfCari);
        topPanel.add(btnCari);

        modelTransaksi = new DefaultTableModel(new Object[]{"NIM", "Kode Buku", "Tanggal Pinjam", "Jam Pinjam"}, 0);
        tableTransaksi = new JTable(modelTransaksi);

        panelTransaksi.add(topPanel, BorderLayout.NORTH);
        panelTransaksi.add(new JScrollPane(tableTransaksi), BorderLayout.CENTER);

        btnAdd.addActionListener(e -> addBuku());
        btnEdit.addActionListener(e -> editBuku());
        btnSearch.addActionListener(e -> cariBuku());
        btnDelete.addActionListener(e -> hapusBuku());


        // Tambahkan ke tabbedPane
        tabbedPane.addTab("Manajemen Buku", panelBuku);
        tabbedPane.addTab("Transaksi Mahasiswa", panelTransaksi);
        tampilkanTabelBuku();

        add(tabbedPane);
        setVisible(true);
    }
    private void addBuku(){
        String Kode = tfKode.getText();
        String Judul = tfJudul.getText();
        String jumlahPengarang = tfPengarang.getText().trim();
        String Jumlah = tfJumlah.getText().trim();
        if(perpustakaan.cariBuku(Kode) != null){
            JOptionPane.showMessageDialog(this, "Kode buku tidak boleh sama!");
            return;
        }
        if (Kode.isEmpty() || Judul.isEmpty() || jumlahPengarang.isEmpty() || Jumlah.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            jumlah = Integer.parseInt(jumlahPengarang);
            if (jumlah <= 0) throw new NumberFormatException();

            pengarangSet.clear();
            pengarangKe = 1;
            inputNamaPengarang(Kode, Judul);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Jumlah pengarang harus angka > 0!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        try {
            perpustakaan.simpanBuku(Kode, Judul, pengarangSet, Integer.parseInt(Jumlah));
            tampilkanTabelBuku();
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public void inputNamaPengarang(String kode, String judul) {
        if (pengarangKe > jumlah) {
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
    public void tampilkanTabelBuku(){
        modelBuku.setRowCount(0);
        try{
            File file = new File("dataBuku.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(";");
                String kode = parts[0].trim();
                String judul = parts[1].trim();
                String pengarang = parts[2].trim();
                String jumlah = parts[3].trim();
                modelBuku.addRow(new Object[]{kode,judul, pengarang,jumlah});
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void editBuku(){
        String kode = tfKode.getText().trim();
        String judul = tfJudul.getText().trim();
        String jumlahPengarangText = tfPengarang.getText().trim();
        String jumlahBukuText = tfJumlah.getText().trim();

        if (kode.isEmpty() || judul.isEmpty() || jumlahPengarangText.isEmpty() || jumlahBukuText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            jumlah = Integer.parseInt(jumlahPengarangText);
            if (jumlah <= 0) throw new NumberFormatException();

            pengarangSet.clear();
            pengarangKe = 1;
            editNamaPengarang(kode, judul, Integer.parseInt(jumlahBukuText));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Jumlah pengarang harus angka > 0!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void editNamaPengarang(String kode, String judul, int i) {
        if (pengarangKe > jumlah) {
            try {
                perpustakaan.editBuku(kode, judul, pengarangSet, i);
                tampilkanTabelBuku();
                JOptionPane.showMessageDialog(null, "Buku berhasil diedit!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            return;
        }

        String nama = JOptionPane.showInputDialog(null, "Masukkan nama pengarang ke-" + pengarangKe + ":");
        if (nama != null && !nama.isBlank()) {
            pengarangSet.add(nama.trim());
            pengarangKe++;
            editNamaPengarang(kode, judul, i);
        } else {
            JOptionPane.showMessageDialog(null, "Nama pengarang tidak boleh kosong.");
            editNamaPengarang(kode, judul, i);
        }
    }
    private void cariBuku(){

    }
    private void hapusBuku(){

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminHomePage::new);
    }
}

