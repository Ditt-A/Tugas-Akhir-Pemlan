package semester2.src.Hubert.ProjectAkhir;

import javax.swing.*;
import java.awt.*;
// import java.util.TreeSet;

class MahasiswaInputGUI extends JFrame {
    private JTextField nimField, namaField, prodiField;
    Perpustakaan perpustakaan = new Perpustakaan();

    public MahasiswaInputGUI() {
        setTitle("Form Input Mahasiswa");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 5, 5));

        add(new JLabel("NIM:"));
        nimField = new JTextField();
        add(nimField);

        add(new JLabel("Nama:"));
        namaField = new JTextField();
        add(namaField);

        add(new JLabel("Prodi:"));
        prodiField = new JTextField();
        add(prodiField);

        JButton DaftarButton = new JButton("Add");
        JButton SearchButton = new JButton("Search");
        JButton EditButton = new JButton("Edit");
        JButton DeleteButton = new JButton("Delete");
        JButton ClearButton = new JButton("Clear");
        add(DaftarButton);
        add(SearchButton);
        add(EditButton);
        add(DeleteButton);

        add(new JLabel());

        DaftarButton.addActionListener(e -> daftarMahasiswa());
        SearchButton.addActionListener(e -> searchMahasiswa());
        EditButton.addActionListener(e -> {
            try {
                editMahasiswa();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        DeleteButton.addActionListener(e -> deleteMahasiswa());
        ClearButton.addActionListener(e -> clearMahasiswa());

        setVisible(true);
    }
    private void daftarMahasiswa() {
        try {
            String nim = nimField.getText();
            String nama = namaField.getText();
            String prodi = prodiField.getText();
            perpustakaan.simpanMahasiswa(nim, nama, prodi);
            JOptionPane.showMessageDialog(null, "Mahasiswa berhasil didaftarkan!!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    private void searchMahasiswa() {
        try{
            String nim = nimField.getText();
            String hasil = perpustakaan.cariMahasiswa(nim);
            if(hasil != null){
                JOptionPane.showMessageDialog(null, hasil);
            }
            else{
                JOptionPane.showMessageDialog(null, "Mahasiswa tidak ditemukan!");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    private void editMahasiswa() throws Exception {
        String nim = nimField.getText().trim();
        String nama = namaField.getText().trim();
        String prodi = prodiField.getText().trim();

        if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            perpustakaan.editMahasiswa(nim, nama, prodi);
            JOptionPane.showMessageDialog(null, "Mahasiswa berhasil diedit!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    private void deleteMahasiswa() {
        try {
            String nim = nimField.getText().trim();
            perpustakaan.hapusMahasiswa(nim);
            JOptionPane.showMessageDialog(null, "User berhasil didelete!");
        } catch (Exception e) {
            String message = e.getMessage();
            if (message == null || message.isBlank()) {
                message = "Terjadi kesalahan saat menghapus User.";
            }
            JOptionPane.showMessageDialog(this, message);
        }
    }
    private void clearMahasiswa() {
        nimField.setText("");
        namaField.setText("");
        prodiField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MahasiswaInputGUI().setVisible(true));
    }
}
