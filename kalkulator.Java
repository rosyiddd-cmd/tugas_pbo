package kalkulator;

import java.util.Scanner;

public class AplikasiKalkulator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean berjalan = true;

        System.out.println("=================================");
        System.out.println("    KALKULATOR KONSOL JAVA       ");
        System.out.println("=================================");

        while (berjalan) {
            double angka1 = inputAngka(scanner, "Masukkan angka pertama : ");
            String op = inputOperator(scanner);
            double angka2 = inputAngka(scanner, "Masukkan angka kedua   : ");

            eksekusiPerhitungan(angka1, op, angka2);

            System.out.print("\nHitung lagi? (y/n): ");
            String respon = scanner.nextLine().trim();
            if (!respon.equalsIgnoreCase("y") && !respon.equalsIgnoreCase("ya")) {
                berjalan = false;
            }
            System.out.println("---------------------------------");
        }

        System.out.println("Program selesai. Terima kasih!");
        scanner.close();
    }

    // Memvalidasi agar pengguna wajib menginputkan angka
    private static double inputAngka(Scanner scanner, String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[!] Input tidak valid. Harap masukkan angka yang benar.");
            }
        }
    }

    // Memvalidasi agar operator sesuai dengan pilihan yang tersedia
    private static String inputOperator(Scanner scanner) {
        while (true) {
            System.out.print("Pilih operator (+, -, *, ×, x, /, ÷) : ");
            String operator = scanner.nextLine().trim();

            if (operator.matches("[+\\-*x×/÷]")) {
                return operator;
            }
            System.out.println("[!] Operator salah! Gunakan +, -, *, ×, x, /, atau ÷.");
        }
    }

    // Melakukan proses kalkulasi berdasarkan operator
    private static void eksekusiPerhitungan(double a, String op, double b) {
        double hasil = 0;
        boolean sukses = true;

        switch (op) {
            case "+" -> hasil = a + b;
            case "-" -> hasil = a - b;
            case "*", "x", "×" -> hasil = a * b;
            case "/", "÷" -> {
                if (b == 0) {
                    System.out.println("[!] Error: Pembagian dengan nol tidak diperbolehkan.");
                    sukses = false;
                } else {
                    hasil = a / b;
                }
            }
        }

        if (sukses) {
            cetakHasil(hasil);
        }
    }

    // Format output: mencetak angka bulat tanpa desimal .0
    private static void cetakHasil(double hasil) {
        if (hasil % 1 == 0) {
            System.out.printf("Hasil : %d\n", (long) hasil);
        } else {
            System.out.printf("Hasil : %s\n", hasil);
        }
    }
}