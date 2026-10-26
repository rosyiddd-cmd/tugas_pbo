import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class kasir extends JFrame {
    private JComboBox<String> comboBarang;
    private JTextField txtHarga, txtJumlah, txtBayar;
    private JTable tabelKeranjang;
    private DefaultTableModel modelTabel;
    private JLabel lblTotal;
    private JTextArea txtStruk;

    // Data pilihan barang dan harganya
    private final String[] namaBarang = {
        "-- Pilih Barang --", 
        "Buku Tulis", 
        "Pensil 2B", 
        "Pulpen Gel", 
        "Penggaris 30cm", 
        "Penghapus", 
        "Tipe-X", 
        "Spidol Boardmarker"
    };
    
    private final int[] hargaBarang = {0, 5000, 3000, 4500, 4000, 2000, 7000, 8500};
    
    private double totalBelanja = 0;
    private NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    public kasir() {
        setTitle("Aplikasi Kasir Pembayaran");
        setSize(850, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(255, 247, 242)); // Tema putih & oranye lembut

        // Panel Utama dengan Grid 2 Kolom
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        mainPanel.setBackground(new Color(255, 247, 242));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // --- KOLOM KIRI: Form Input Barang ---
        JPanel panelKiri = new JPanel();
        panelKiri.setLayout(new BoxLayout(panelKiri, BoxLayout.Y_AXIS));
        panelKiri.setBackground(Color.WHITE);
        panelKiri.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 230, 213)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblJudul1 = new JLabel("Kasir Pembayaran");
        lblJudul1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblJudul1.setForeground(new Color(255, 102, 0));
        lblJudul1.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelKiri.add(lblJudul1);
        panelKiri.add(Box.createVerticalStrut(15));

        panelKiri.add(createLabel("Pilih Barang"));
        comboBarang = new JComboBox<>(namaBarang);
        comboBarang.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        
        // Event otomatis saat barang dipilih, harga langsung terisi
        comboBarang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBarang.getSelectedIndex();
                txtHarga.setText(String.valueOf(hargaBarang[index]));
            }
        });
        panelKiri.add(comboBarang);
        panelKiri.add(Box.createVerticalStrut(10));

        panelKiri.add(createLabel("Harga Satuan (Rp)"));
        txtHarga = new JTextField("0");
        txtHarga.setEditable(false);
        txtHarga.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        txtHarga.setBackground(new Color(255, 240, 230));
        panelKiri.add(txtHarga);
        panelKiri.add(Box.createVerticalStrut(10));

        panelKiri.add(createLabel("Jumlah"));
        txtJumlah = new JTextField("1");
        txtJumlah.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panelKiri.add(txtJumlah);
        panelKiri.add(Box.createVerticalStrut(20));

        JButton btnTambah = createButton("+ Tambah ke Keranjang", new Color(255, 102, 0));
        btnTambah.addActionListener(e -> tambahItemToKeranjang());
        panelKiri.add(btnTambah);

        mainPanel.add(panelKiri);

        // --- KOLOM KANAN: Keranjang & Pembayaran ---
        JPanel panelKanan = new JPanel();
        panelKanan.setLayout(new BoxLayout(panelKanan, BoxLayout.Y_AXIS));
        panelKanan.setBackground(Color.WHITE);
        panelKanan.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 230, 213)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblJudul2 = new JLabel("Daftar Belanja");
        lblJudul2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblJudul2.setForeground(new Color(255, 102, 0));
        lblJudul2.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelKanan.add(lblJudul2);
        panelKanan.add(Box.createVerticalStrut(10));

        modelTabel = new DefaultTableModel(new String[]{"Barang", "Harga", "Qty", "Subtotal"}, 0);
        tabelKeranjang = new JTable(modelTabel);
        JScrollPane scrollPane = new JScrollPane(tabelKeranjang);
        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));
        panelKanan.add(scrollPane);
        panelKanan.add(Box.createVerticalStrut(10));

        JPanel panelTotal = new JPanel(new BorderLayout());
        panelTotal.setBackground(new Color(255, 240, 230));
        panelTotal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelTotal.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        JLabel lblTotalText = new JLabel("Total Belanja:");
        lblTotalText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTotal = new JLabel("Rp 0");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTotal.setForeground(new Color(255, 102, 0));
        panelTotal.add(lblTotalText, BorderLayout.WEST);
        panelTotal.add(lblTotal, BorderLayout.EAST);
        panelKanan.add(panelTotal);
        panelKanan.add(Box.createVerticalStrut(10));

        panelKanan.add(createLabel("Uang Dibayar (Rp)"));
        txtBayar = new JTextField();
        txtBayar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panelKanan.add(txtBayar);
        panelKanan.add(Box.createVerticalStrut(10));

        JButton btnBayar = createButton("Proses Pembayaran", new Color(255, 102, 0));
        btnBayar.addActionListener(e -> prosesPembayaran());
        panelKanan.add(btnBayar);

        panelKanan.add(Box.createVerticalStrut(5));
        JButton btnReset = createButton("Reset Transaksi", Color.WHITE);
        btnReset.setForeground(new Color(255, 102, 0));
        btnReset.setBorder(BorderFactory.createLineBorder(new Color(255, 102, 0)));
        btnReset.addActionListener(e -> resetTransaksi());
        panelKanan.add(btnReset);

        mainPanel.add(panelKanan);
        add(mainPanel, BorderLayout.CENTER);

        // --- BAGIAN BAWAH: Nota / Struk ---
        txtStruk = new JTextArea(6, 20);
        txtStruk.setFont(new Font("Courier New", Font.PLAIN, 12));
        txtStruk.setEditable(false);
        txtStruk.setBackground(new Color(255, 248, 245));
        JScrollPane scrollStruk = new JScrollPane(txtStruk);
        scrollStruk.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(255, 153, 85)), "Nota Pembayaran"
        ));
        
        JPanel panelBawah = new JPanel(new BorderLayout());
        panelBawah.setBackground(new Color(255, 247, 242));
        panelBawah.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
        panelBawah.add(scrollStruk);
        add(panelBawah, BorderLayout.SOUTH);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(new Color(85, 85, 85));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JButton createButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setBackground(bg);
        btn.setForeground(bg.equals(Color.WHITE) ? new Color(255, 102, 0) : Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void tambahItemToKeranjang() {
        int selectedIndex = comboBarang.getSelectedIndex();
        if (selectedIndex == 0) {
            JOptionPane.showMessageDialog(this, "Silakan pilih barang terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nama = namaBarang[selectedIndex];
        double harga = hargaBarang[selectedIndex];
        int jumlah;

        try {
            jumlah = Integer.parseInt(txtJumlah.getText());
            if (jumlah <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah barang harus angka positif!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double subtotal = harga * jumlah;
        totalBelanja += subtotal;

        modelTabel.addRow(new Object[]{nama, rupiahFormat.format(harga), jumlah, rupiahFormat.format(subtotal)});
        lblTotal.setText(rupiahFormat.format(totalBelanja));

        // Reset input form setelah ditambah
        comboBarang.setSelectedIndex(0);
        txtHarga.setText("0");
        txtJumlah.setText("1");
    }

    private void prosesPembayaran() {
        if (modelTabel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Keranjang belanja masih kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double bayar;
        try {
            bayar = Double.parseDouble(txtBayar.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan nominal uang bayar yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (bayar < totalBelanja) {
            JOptionPane.showMessageDialog(this, "Uang pembayaran kurang dari total belanja!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double kembali = bayar - totalBelanja;

        StringBuilder sb = new StringBuilder();
        sb.append("         --- NOTA PEMBAYARAN KASIR ---\n");
        sb.append("        ").append(new Date().toString()).append("\n");
        sb.append("--------------------------------------------------\n");
        for (int i = 0; i < modelTabel.getRowCount(); i++) {
            sb.append(String.format("- %s x%s = %s\n", 
                modelTabel.getValueAt(i, 0), 
                modelTabel.getValueAt(i, 2), 
                modelTabel.getValueAt(i, 3)
            ));
        }
        sb.append("--------------------------------------------------\n");
        sb.append(String.format("TOTAL   : %s\n", rupiahFormat.format(totalBelanja)));
        sb.append(String.format("BAYAR   : %s\n", rupiahFormat.format(bayar)));
        sb.append(String.format("KEMBALI : %s\n", rupiahFormat.format(kembali)));
        sb.append("      Terima Kasih Atas Kunjungan Anda!\n");

        txtStruk.setText(sb.toString());
    }

    private void resetTransaksi() {
        modelTabel.setRowCount(0);
        totalBelanja = 0;
        lblTotal.setText("Rp 0");
        txtBayar.setText("");
        txtStruk.setText("");
        comboBarang.setSelectedIndex(0);
        txtHarga.setText("0");
        txtJumlah.setText("1");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new kasir().setVisible(true);
        });
    }
            }
