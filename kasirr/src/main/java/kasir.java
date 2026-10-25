import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class kasir {
    public static void main(String[] args) throws IOException {
        // Membuat server lokal pada port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new KasirHandler());
        server.setExecutor(null);
        
        System.out.println("==================================================");
        System.out.println("Server Kasir Berhasil Dijalankan!");
        System.out.println("Buka browser dan akses: http://localhost:8080/");
        System.out.println("==================================================");
        
        server.start();
    }

    static class KasirHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Kode HTML + CSS + JavaScript dengan daftar pilihan barang dan harga
            String htmlResponse = """
                <!DOCTYPE html>
                <html lang="id">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Aplikasi Kasir Toko Modern</title>
                    <style>
                        body {
                            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                            background-color: #f4f7f6;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            height: 100vh;
                            margin: 0;
                        }
                        .card {
                            background: white;
                            padding: 30px;
                            border-radius: 12px;
                            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
                            width: 380px;
                        }
                        h2 {
                            text-align: center;
                            color: #333;
                            margin-top: 0;
                            margin-bottom: 20px;
                        }
                        .form-group {
                            margin-bottom: 15px;
                        }
                        label {
                            display: block;
                            margin-bottom: 5px;
                            font-weight: 600;
                            font-size: 13px;
                            color: #555;
                        }
                        select, input {
                            width: 100%;
                            padding: 10px;
                            box-sizing: border-box;
                            border: 1px solid #ddd;
                            border-radius: 6px;
                            font-size: 14px;
                            background-color: #fafafa;
                        }
                        select:focus, input:focus {
                            border-color: #007bff;
                            outline: none;
                            background-color: #fff;
                        }
                        button {
                            width: 100%;
                            background-color: #007bff;
                            color: white;
                            padding: 12px;
                            border: none;
                            border-radius: 6px;
                            cursor: pointer;
                            font-size: 15px;
                            font-weight: bold;
                            margin-top: 10px;
                            transition: background 0.2s;
                        }
                        button:hover {
                            background-color: #0056b3;
                        }
                        .result {
                            margin-top: 20px;
                            padding: 15px;
                            background-color: #eef2f7;
                            border-radius: 6px;
                            font-size: 14px;
                            line-height: 1.6;
                        }
                        .error {
                            color: #dc3545;
                            font-weight: bold;
                        }
                        .success {
                            color: #28a745;
                            font-weight: bold;
                        }
                    </style>
                </head>
                <body>
                    <div class="card">
                        <h2>Kasir Toko Kita</h2>
                        
                        <div class="form-group">
                            <label>Pilih Barang:</label>
                            <select id="pilihanBarang" onchange="updateHarga()">
                                <option value="" disabled selected>-- Pilih Produk --</option>
                                <option value="10000">Indomie Goreng Spesial - Rp 10.000</option>
                                <option value="18000">Nasi Goreng Spesial - Rp 18.000</option>
                                <option value="5000">Es Teh Manis - Rp 5.000</option>
                                <option value="20000">Ayam Geprek - Rp 20.000</option>
                                <option value="12000">Kopi Susu Gula Aren - Rp 12.000</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Harga Satuan (Rp):</label>
                            <input type="number" id="harga" readonly style="background-color: #e9ecef;">
                        </div>

                        <div class="form-group">
                            <label>Jumlah Beli:</label>
                            <input type="number" id="jumlah" value="1" min="1">
                        </div>

                        <div class="form-group">
                            <label>Uang Tunai Dibayar (Rp):</label>
                            <input type="number" id="bayar" placeholder="Contoh: 50000">
                        </div>

                        <button onclick="hitungKasir()">Hitung Transaksi</button>
                        
                        <div class="result" id="hasilArea" style="display: none;">
                            <div id="teksHasil"></div>
                        </div>
                    </div>

                    <script>
                        // Fungsi otomatis mengisi harga saat barang dipilih dari dropdown
                        function updateHarga() {
                            let select = document.getElementById('pilihanBarang');
                            let hargaInput = document.getElementById('harga');
                            hargaInput.value = select.value;
                        }

                        function hitungKasir() {
                            let select = document.getElementById('pilihanBarang');
                            let selectedOption = select.options[select.selectedIndex];
                            
                            if (select.value === "") {
                                let hasilArea = document.getElementById('hasilArea');
                                hasilArea.style.display = 'block';
                                document.getElementById('teksHasil').innerHTML = '<span class="error">Silakan pilih barang terlebih dahulu!</span>';
                                return;
                            }
                            
                            let namaBarang = selectedOption.text.split(' - ')[0];
                            let harga = parseFloat(document.getElementById('harga').value) || 0;
                            let jumlah = parseInt(document.getElementById('jumlah').value) || 0;
                            let bayar = parseFloat(document.getElementById('bayar').value) || 0;
                            
                            let hasilArea = document.getElementById('hasilArea');
                            let teksHasil = document.getElementById('teksHasil');
                            
                            hasilArea.style.display = 'block';
                            
                            if (jumlah <= 0) {
                                teksHasil.innerHTML = '<span class="error">Jumlah beli minimal 1!</span>';
                                return;
                            }
                            
                            let totalBelanja = harga * jumlah;
                            let kembalian = bayar - totalBelanja;
                            
                            if (bayar < totalBelanja) {
                                let kurang = totalBelanja - bayar;
                                teksHasil.innerHTML = `Barang: <b>${namaBarang}</b> (${jumlah}x)<br>` +
                                                      `Total Belanja: Rp ${totalBelanja.toLocaleString('id-ID')}<br>` +
                                                      `<span class="error">Uang kurang Rp ${kurang.toLocaleString('id-ID')}</span>`;
                            } else {
                                teksHasil.innerHTML = `Barang: <b>${namaBarang}</b> (${jumlah}x)<br>` +
                                                      `Total Belanja: Rp ${totalBelanja.toLocaleString('id-ID')}<br>` +
                                                      `Uang Dibayar: Rp ${bayar.toLocaleString('id-ID')}<br>` +
                                                      `<span class="success">Kembalian: Rp ${kembalian.toLocaleString('id-ID')}</span>`;
                            }
                        }
                    </script>
                </body>
                </html>
                """;

            exchange.sendResponseHeaders(200, htmlResponse.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(htmlResponse.getBytes());
            os.close();
        }
    }
}