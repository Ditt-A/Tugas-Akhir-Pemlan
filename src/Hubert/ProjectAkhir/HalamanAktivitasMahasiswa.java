package semester2.src.Hubert.ProjectAkhir;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
// import java.awt.event.*;

public class HalamanAktivitasMahasiswa extends JFrame {

    private DefaultTableModel tabelModel;

    public HalamanAktivitasMahasiswa() {
        setTitle("Aktivitas Mahasiswa");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Pinjam Buku", createPinjamBukuTab());
        tabbedPane.addTab("Pengembalian Buku", createPengembalianBukuTab());
        tabbedPane.addTab("Manajemen Akun", createManajemenAkunTab());

        add(tabbedPane);
    }

    // ------------------ TAB PINJAM ------------------
    private JPanel createPinjamBukuTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(0x3B1A12));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);

        JTextField cariField = new JTextField(20);
        JButton cariBtn = new JButton("Cari");
        JButton listBtn = new JButton("List Buku");
        JButton pinjamBtn = new JButton("Pinjam");

        topPanel.add(new JLabel("Kode Buku:"));
        topPanel.add(cariField);
        topPanel.add(cariBtn);
        topPanel.add(pinjamBtn);
        topPanel.add(listBtn);

        // Tabel data buku dipinjam semua mahasiswa
        tabelModel = new DefaultTableModel();
        tabelModel.setColumnIdentifiers(new String[]{"NIM", "Kode Buku", "Judul", "Tanggal Pinjam"});
        JTable table = styledTable(tabelModel);
        JScrollPane tableScroll = new JScrollPane(table);

        // Dummy interaksi tombol
        cariBtn.addActionListener(e -> {
            String kode = cariField.getText();
            // Simulasi data
            if (kode.equalsIgnoreCase("B001")) {
                JOptionPane.showMessageDialog(panel, "Buku ditemukan:\nKode: B001\nJudul: Algoritma", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel, "Stok buku habis atau tidak ditemukan.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });

        pinjamBtn.addActionListener(e -> {
            // Simulasi peminjaman sukses
            tabelModel.addRow(new String[]{"123456", "B001", "Algoritma", "2025-06-06"});
            JOptionPane.showMessageDialog(panel, "Buku berhasil dipinjam!");
        });

        listBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(panel, "Buku Tersedia:\nB001 - Algoritma\nB002 - Struktur Data", "List Buku", JOptionPane.INFORMATION_MESSAGE);
        });

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(tableScroll, BorderLayout.CENTER);
        return panel;
    }

    // ------------------ TAB PENGEMBALIAN ------------------
    private JPanel createPengembalianBukuTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(0x3B1A12));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);

        JTextField cariField = new JTextField(20);
        JButton cariBtn = new JButton("Cari");
        JButton listBtn = new JButton("List Buku Dipinjam");
        JButton kembalikanBtn = new JButton("Kembalikan");

        topPanel.add(new JLabel("Kode Buku:"));
        topPanel.add(cariField);
        topPanel.add(cariBtn);
        topPanel.add(kembalikanBtn);
        topPanel.add(listBtn);

        JTable table = styledTable(tabelModel);
        JScrollPane tableScroll = new JScrollPane(table);

        cariBtn.addActionListener(e -> {
            String kode = cariField.getText();
            boolean ditemukan = false;
            for (int i = 0; i < tabelModel.getRowCount(); i++) {
                if (tabelModel.getValueAt(i, 1).equals(kode)) {
                    ditemukan = true;
                    JOptionPane.showMessageDialog(panel, "Transaksi ditemukan untuk kode " + kode);
                    break;
                }
            }
            if (!ditemukan) {
                JOptionPane.showMessageDialog(panel, "Buku tidak dipinjam oleh Anda.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });

        kembalikanBtn.addActionListener(e -> {
            String kode = cariField.getText();
            for (int i = 0; i < tabelModel.getRowCount(); i++) {
                if (tabelModel.getValueAt(i, 1).equals(kode)) {
                    tabelModel.removeRow(i);
                    JOptionPane.showMessageDialog(panel, "Buku berhasil dikembalikan.");
                    return;
                }
            }
            JOptionPane.showMessageDialog(panel, "Tidak ada data peminjaman untuk kode tersebut.", "Error", JOptionPane.ERROR_MESSAGE);
        });

        listBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(panel, "Buku yang Anda pinjam:\nB001 - Algoritma", "List Buku Dipinjam", JOptionPane.INFORMATION_MESSAGE);
        });

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(tableScroll, BorderLayout.CENTER);
        return panel;
    }

    // ------------------ TAB MANAJEMEN AKUN ------------------
    private JPanel createManajemenAkunTab() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0x3B1A12));
        panel.setLayout(new GridBagLayout());

        JButton editBtn = new JButton("Edit Akun");
        JButton hapusBtn = new JButton("Hapus Akun");

        editBtn.setPreferredSize(new Dimension(200, 40));
        hapusBtn.setPreferredSize(new Dimension(200, 40));

        editBtn.setBackground(new Color(111, 0, 44));
        hapusBtn.setBackground(new Color(111, 0, 44));
        editBtn.setForeground(Color.WHITE);
        hapusBtn.setForeground(Color.WHITE);

        panel.add(editBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(hapusBtn);

        // Dummy actions
        editBtn.addActionListener(e -> JOptionPane.showMessageDialog(panel, "Fitur Edit Akun"));
        hapusBtn.addActionListener(e -> JOptionPane.showMessageDialog(panel, "Fitur Hapus Akun"));

        return panel;
    }

    private JTable styledTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(24);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(0x5D2E2E));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setSelectionBackground(new Color(0xA0522D));
        table.setSelectionForeground(Color.WHITE);
        
        // Warna zebra pada tabel
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(0xF2E8DC) : new Color(0xEADBC8));
                }
                return c;
            }
        });

        return table;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HalamanAktivitasMahasiswa().setVisible(true));
    }
}

