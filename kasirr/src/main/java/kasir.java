import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class kasir extends JFrame {

    // Daftar Barang beserta Harganya
    private String[] namaBarangList = {
        "-- Pilih Barang --", 
        "Buku Tulis", 
        "Pensil 2B", 
        "Pulpen Gel", 
        "Penggaris 30cm", 
        "Penghapus", 
        "Tipe-X", 
        "Spidol Boardmarker"
    };
    
    private int[] hargaBarangList = {0, 5000, 3000, 4500, 4000, 2000, 7000, 8500};

    // Komponen GUI
    private JComboBox<String> cbBarang;
    private JTextField tfHarga, tfJumlah, tfBayar;
    private JTable tabelKeranjang;
    private DefaultTableModel tableModel;
    private JLabel lblTotal;
    private JTextArea taStruk;

    // Struktur Data Keranjang Belanja
    private ArrayList<ItemBelanja> keranjang = new ArrayList<>();
    private int totalBelanja = 0;
    
    // Format Rupiah Indonesia
    private Locale localeID = new Locale("id", "ID");
    private NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    public kasir() {
        setTitle("Aplikasi Kasir Pembayaran - Java Swing");
        setSize(850, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Warna Tema Utama (Putih & Oranye)
        Color bgTheme = new Color(255, 247, 242);
        Color orangePrimary = new Color(255, 102, 0);
        Color cardBg = Color.WHITE;

        getContentPane().setBackground(bgTheme);
        setLayout(new BorderLayout(10, 10));

        // --- PANEL UTAMA (Grid 2 Kolom) ---
        JPanel panelUtama = new JPanel(new GridLayout(1, 2, 15, 15));
        panelUtama.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelUtama.setBackground(bgTheme);

        // --- KARTU 1: INPUT BARANG ---
        JPanel panelInput = new JPanel();
        panelInput.setLayout(new BoxLayout(panelInput, BoxLayout.Y_AXIS));
        panelInput.setBackground(cardBg);
        panelInput.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 230, 213), 2),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblJudul1 = new JLabel("Form Transaksi Kasir");
        lblJudul1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblJudul1.setForeground(orangePrimary);
        lblJudul1.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelInput.add(lblJudul1);
        panelInput.add(Box.createRigidArea(new Dimension(0, 15)));

        // Pilihan Barang (Dropdown)
        panelInput.add(buatLabel("Pilih Barang:"));
        cbBarang = new JComboBox<>(namaBarangList);
        cbBarang.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        cbBarang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = cbBarang.getSelectedIndex();
                int harga = hargaBarangList[selectedIndex];
                tfHarga.setText(String.valueOf(harga));
            }
        });
        panelInput.add(cbBarang);
        panelInput.add(Box.createRigidArea(new Dimension(0, 10)));

        // Harga Satuan (Otomatis & Readonly)
        panelInput.add(buatLabel("Harga Satuan (Rp):"));
        tfHarga = new JTextField("0");
        tfHarga.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        tfHarga.setEditable(false);
        tfHarga.setBackground(new Color(255, 240, 230));
        tfHarga.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panelInput.add(tfHarga);
        panelInput.add(Box.createRigidArea(new Dimension(0, 10)));

        // Jumlah Beli
        panelInput.add(buatLabel("Jumlah:"));
        tfJumlah = new JTextField("1");
        tfJumlah.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panelInput.add(tfJumlah);
        panelInput.add(Box.createRigidArea(new Dimension(15, 15)));

        // Tombol Tambah
        JButton btnTambah = buatTombol("Tambah ke Keranjang", orangePrimary);
        btnTambah.addActionListener(e -> tambahKeKeranjang());
        panelInput.add(btnTambah);

        panelUtama.add(panelInput);

        // --- KARTU 2: KERANJANG & PEMBAYARAN ---
        JPanel panelKeranjang = new JPanel();
        panelKeranjang.setLayout(new BoxLayout(panelKeranjang, BoxLayout.Y_AXIS));
        panelKeranjang.setBackground(cardBg);
        panelKeranjang.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 230, 213), 2),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblJudul2 = new JLabel("Daftar Belanja");
        lblJudul2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblJudul2.setForeground(orangePrimary);
        lblJudul2.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelKeranjang.add(lblJudul2);
        panelKeranjang.add(Box.createRigidArea(new Dimension(0, 10)));

        // Tabel Keranjang
        String[] kolom = {"Barang", "Harga", "Qty", "Subtotal"};
        tableModel = new DefaultTableModel(kolom, 0);
        tabelKeranjang = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelKeranjang);
        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        panelKeranjang.add(scrollPane);
        panelKeranjang.add(Box.createRigidArea(new Dimension(0, 10)));

        // Box Total
        JPanel panelTotal = new JPanel(new BorderLayout());
        panelTotal.setBackground(new Color(255, 240, 230));
        panelTotal.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        panelTotal.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        JLabel lblTotalTeks = new JLabel("Total Belanja:");
        lblTotalTeks.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTotal = new JLabel("Rp 0");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTotal.setForeground(orangePrimary);
        
        panelTotal.add(lblTotalTeks, BorderLayout.WEST);
        panelTotal.add(lblTotal, BorderLayout.EAST);
        panelKeranjang.add(panelTotal);
        panelKeranjang.add(Box.createRigidArea(new Dimension(0, 10)));

        // Input Bayar
        panelKeranjang.add(buatLabel("Uang Dibayar (Rp):"));
        tfBayar = new JTextField();
        tfBayar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panelKeranjang.add(tfBayar);
        panelKeranjang.add(Box.createRigidArea(new Dimension(10, 10)));

        // Tombol Proses & Reset
        JButton btnBayar = buatTombol("Proses Pembayaran", orangePrimary);
        btnBayar.addActionListener(e -> prosesPembayaran());
        panelKeranjang.add(btnBayar);
        
        panelKeranjang.add(Box.createRigidArea(new Dimension(0, 5)));
        JButton btnReset = buatTombol("Reset Transaksi", new Color(120, 120, 120));
        btnReset.addActionListener(e -> resetKasir());
        panelKeranjang.add(btnReset);

        panelUtama.add(panelKeranjang);
        add(panelUtama, BorderLayout.CENTER);

        // --- AREA STRUK NOTA (DI BAGIAN BAWAH) ---
        taStruk = new JTextArea(8, 20);
        taStruk.setEditable(false);
        taStruk.setFont(new Font("Monospaced", Font.PLAIN, 12));
        taStruk.setBackground(new Color(255, 252, 249));
        
        JScrollPane scrollStruk = new JScrollPane(taStruk);
        scrollStruk.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(orangePrimary), " Nota / Struk Pembayaran "
        ));
        
        JPanel panelBawah = new JPanel(new BorderLayout());
        panelBawah.setBorder(new EmptyBorder(0, 15, 15, 15));
        panelBawah.setBackground(bgTheme);
        panelBawah.add(scrollStruk, BorderLayout.CENTER);
        
        add(panelBawah, BorderLayout.SOUTH);
    }

    // Fungsi Pembantu Membuat Label
    private JLabel buatLabel(String teks) {
        JLabel label = new JLabel(teks);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(new Color(80, 80, 80));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    // Fungsi Pembantu Membuat Tombol Kustom
    private JButton buatTombol(String teks, Color warnaBg) {
        JButton tombol = new JButton(teks);
        tombol.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        tombol.setBackground(warnaBg);
        tombol.setForeground(Color.WHITE);
        tombol.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tombol.setFocusPainted(false);
        tombol.setAlignmentX(Component.LEFT_ALIGNMENT);
        return tombol;
    }

    // Logika Tambah Item ke Keranjang
    private void tambahKeKeranjang() {
        int selectedIndex = cbBarang.getSelectedIndex();
        if (selectedIndex == 0) {
            JOptionPane.showMessageDialog(this, "Silakan pilih barang terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nama = namaBarangList[selectedIndex];
        int harga = hargaBarangList[selectedIndex];
        
        int jumlah;
        try {
            jumlah = Integer.parseInt(tfJumlah.getText());
            if (jumlah <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah barang harus berupa angka positif!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int subtotal = harga * jumlah;
        keranjang.add(new ItemBelanja(nama, harga, jumlah, subtotal));
        
        // Masukkan ke Tabel GUI
        tableModel.addRow(new Object[]{nama, formatRupiah.format(harga), jumlah, formatRupiah.format(subtotal)});
        
        // Hitung Total
        totalBelanja += subtotal;
        lblTotal.setText(formatRupiah.format(totalBelanja));

        // Reset input form kecil
        cbBarang.setSelectedIndex(0);
        tfHarga.setText("0");
        tfJumlah.setText("1");
    }

    // Logika Proses Pembayaran & Cetak Struk
    private void prosesPembayaran() {
        if (keranjang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Keranjang belanja masih kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int uangBayar;
        try {
            uangBayar = Integer.parseInt(tfBayar.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan nominal uang bayar yang valid!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (uangBayar < totalBelanja) {
            JOptionPane.showMessageDialog(this, "Uang pembayaran kurang dari total belanja!", "Pembayaran Gagal", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int kembalian = uangBayar - totalBelanja;

        // Buat Teks Struk
        StringBuilder struk = new StringBuilder();
        struk.append("           --- NOTA PEMBAYARAN KASIR ---\n");
        struk.append("               Tanggal: ").append(new Date()).append("\n");
        struk.append("------------------------------------------------------\n");
        for (ItemBelanja item : keranjang) {
            struk.append(String.format(" %-15s x%-3d  @%-8s = %s\n", 
                item.nama, item.jumlah, formatRupiah.format(item.harga), formatRupiah.format(item.subtotal)));
        }
        struk.append("------------------------------------------------------\n");
        struk.append(" TOTAL BELANJA : ").append(formatRupiah.format(totalBelanja)).append("\n");
        struk.append(" UANG DIBAYAR  : ").append(formatRupiah.format(uangBayar)).append("\n");
        struk.append(" KEMBALIAN     : ").append(formatRupiah.format(kembalian)).append("\n");
        struk.append("------------------------------------------------------\n");
        struk.append("         Terima Kasih Atas Kunjungan Anda!\n");

        taStruk.setText(struk.toString());
        JOptionPane.showMessageDialog(this, "Pembayaran Berhasil! Kembalian: " + formatRupiah.format(kembalian), "Sukses", JOptionPane.INFORMATION_MESSAGE);
    }

    // Logika Reset Transaksi
    private void resetKasir() {
        keranjang.clear();
        tableModel.setRowCount(0);
        totalBelanja = 0;
        lblTotal.setText("Rp 0");
        tfBayar.setText("");
        cbBarang.setSelectedIndex(0);
        tfHarga.setText("0");
        tfJumlah.setText("1");
        taStruk.setText("");
    }

    // Main Method agar langsung bisa dire Run di NetBeans
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new kasir().setVisible(true);
            }
        });
    }
}

// Kelas Pendukung untuk Item Belanja
class ItemBelanja {
    String nama;
    int harga;
    int jumlah;
    int subtotal;

    public ItemBelanja(String nama, int harga, int jumlah, int subtotal) {
        this.nama = nama;
        this.harga = harga;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
    }
            }
