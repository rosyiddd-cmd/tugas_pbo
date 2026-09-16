/**
 * TUGAS PRAKTIKUM ALGORITMA DAN PEMROGRAMAN DENGAN JAVASCRIPT
 * Nama    : M. Abdul Rosyid
 * NIM     : 251101023
 * Kelas   : TI3B
 */

// ==========================================
// KUMPULAN FUNGSI SOAL 1 - 3 (STRING & CHAR)
// ==========================================

function balikKalimat(kalimat) {
  let hasil = "";
  for (let i = kalimat.length - 1; i >= 0; i--) {
    hasil += kalimat[i];
  }
  return hasil;
}

function hitungJumlahHuruf(kalimat, hurufCari) {
  let jumlah = 0;
  for (let i = 0; i < kalimat.length; i++) {
    if (kalimat[i].toLowerCase() === hurufCari.toLowerCase()) {
      jumlah++;
    }
  }
  return jumlah;
}

function hitungJumlahKarakter(kalimat) {
  return kalimat.length;
}

// ==========================================
// KUMPULAN FUNGSI SOAL 4 - 15 (POLA ANGKA)
// ==========================================

function polaSoal4() {
  let res = "";
  for (let i = 1; i <= 6; i++) {
    for (let j = 0; j < i; j++) res += i;
  }
  return res;
}

function polaSoal5() {
  let res = "";
  for (let i = 6; i >= 1; i--) {
    for (let j = 0; j < i; j++) res += i;
  }
  return res;
}

function polaSoal6() {
  let res = "";
  for (let i = 1; i <= 6; i++) {
    for (let j = 1; j <= i; j++) res += j;
  }
  return res;
}

function polaSoal7() {
  let res = "";
  for (let i = 6; i >= 1; i--) {
    for (let j = i; j >= 1; j--) res += j;
  }
  return res;
}

function polaSoal8() {
  let res = "";
  for (let i = 1; i <= 6; i++) {
    if (i % 2 !== 0) {
      for (let j = 0; j < i; j++) res += i;
    } else {
      for (let j = 1; j <= i; j++) res += j;
    }
  }
  return res;
}

function polaSoal9() {
  let res = "";
  for (let i = 1; i <= 6; i++) {
    if (i % 2 === 0) {
      for (let j = 0; j < i; j++) res += i;
    } else {
      for (let j = 1; j <= i; j++) res += j;
    }
  }
  return res;
}

function polaSoal10() {
  let res = "";
  for (let i = 6; i >= 1; i--) {
    if (i % 2 !== 0) {
      for (let j = 0; j < i; j++) res += i;
    } else {
      for (let j = i; j >= 1; j--) res += j;
    }
  }
  return res;
}

function polaSoal11() {
  let res = "";
  for (let i = 6; i >= 1; i--) {
    if (i % 2 === 0) {
      for (let j = 0; j < i; j++) res += i;
    } else {
      for (let j = 1; j <= i; j++) res += j;
    }
  }
  return res;
}

function polaSoal12() {
  let res = "";
  for (let i = 1; i <= 9; i++) {
    if (i % 2 === 0) {
      for (let j = 0; j < i; j++) res += i;
    } else {
      for (let j = 1; j <= i; j++) res += j;
    }
  }
  return res;
}

function polaSoal13() {
  let res = "";
  for (let i = 1; i <= 9; i++) {
    if (i % 2 !== 0) {
      for (let j = 0; j < i; j++) res += i;
    } else {
      for (let j = 1; j <= i; j++) res += j;
    }
  }
  return res;
}

function polaSoal14() {
  let res = "";
  for (let i = 8; i >= 1; i--) {
    if (i % 2 === 0) {
      for (let j = 0; j < i; j++) res += i;
    } else {
      for (let j = i; j >= 1; j--) res += j;
    }
  }
  return res;
}

function polaSoal15() {
  let res = "";
  for (let i = 8; i >= 1; i--) {
    if (i % 2 !== 0) {
      for (let j = 0; j < i; j++) res += i;
    } else {
      for (let j = i; j >= 1; j--) res += j;
    }
  }
  return res;
}

// ==========================================
// KUMPULAN FUNGSI SOAL 16 - 21 (DERET ANGKA)
// ==========================================

function deretSoal16(jumlahSuku = 12) {
  let val = 1;
  let arr = [val];
  for (let i = 1; i < jumlahSuku; i++) {
    if (i % 2 !== 0) val += 4;
    else val -= 2;
    arr.push(val);
  }
  return arr.join(" ");
}

function deretSoal17(jumlahSuku = 10) {
  let val = 2;
  let arr = [val];
  for (let i = 1; i < jumlahSuku; i++) {
    if (i % 2 !== 0) val += 10;
    else val -= 5;
    arr.push(val);
  }
  return arr.join(" ");
}

function deretSoal18(jumlahSuku = 12) {
  let val = 5;
  let arr = [val];
  for (let i = 1; i < jumlahSuku; i++) {
    if (i % 2 !== 0) val -= 3;
    else val += 5;
    arr.push(val);
  }
  return arr.join(" ");
}

function deretSoal19(jumlahSuku = 10) {
  let val = 3;
  let arr = [val];
  for (let i = 1; i < jumlahSuku; i++) {
    if (i % 2 !== 0) val *= 3;
    else val -= 5;
    arr.push(val);
  }
  return arr.join(" ");
}

function deretSoal20(jumlahSuku = 13) {
  let val = 1;
  let arr = [val];
  let pola = [1, 2, 3];
  for (let i = 0; i < jumlahSuku - 1; i++) {
    val += pola[i % 3];
    arr.push(val);
  }
  return arr.join(" ");
}

function deretSoal21(n = 10) {
  let arr = [];
  for (let i = 0; i < n; i++) {
    arr.push(Math.pow(2, i));
  }
  return arr.join(" ");
}

// ==========================================
// KUMPULAN FUNGSI SOAL 22 - 33 (FAKTORIAL, FIBONACCI, KABISAT, KELIPATAN)
// ==========================================

function hitungFaktorial(n) {
  let proses = [];
  let hasil = 1;
  for (let i = n; i >= 1; i--) {
    proses.push(i);
    hasil *= i;
  }
  return `${n}! = ${proses.join(" x ")} = ${hasil}`;
}

function deretFibonacci(maksimal) {
  let arr = [0, 1];
  while (true) {
    let next = arr[arr.length - 1] + arr[arr.length - 2];
    if (next > maksimal) break;
    arr.push(next);
  }
  return arr.join(",");
}

function cekKabisat(tahun) {
  return (tahun % 4 === 0 && tahun % 100 !== 0) || tahun % 400 === 0;
}

function tahunKabisatAkhiran(nAwal, nAkhir, digitAkhir) {
  let hasil = [];
  for (let t = nAwal; t <= nAkhir; t++) {
    if (cekKabisat(t) && t % 10 === digitAkhir) {
      hasil.push(t);
    }
  }
  return hasil.join(", ") || "Tidak ditemukan";
}

function kelipatanPembagi(nAwal, nAkhir, pembagi) {
  let hasil = [];
  for (let i = nAwal; i <= nAkhir; i++) {
    if (i % pembagi === 0) {
      hasil.push(i);
    }
  }
  return hasil.join(", ");
}

// ==========================================
// KUMPULAN FUNGSI SOAL 34 - 41 (SIMULASI ANIMASI LOG)
// ==========================================

function simulasiAnimasi(noSoal, deskripsi) {
  return `[Simulasi Soal ${noSoal}]: ${deskripsi} -> Pergerakan posisi angka '0' pada matriks/layar secara berulang.`;
}

// ==========================================
// KUMPULAN FUNGSI SOAL 42 - 50 (ARRAY & PRIMA)
// ==========================================

function cariTerbesar(arrData) {
  let max = arrData[0];
  for (let i = 1; i < arrData.length; i++) {
    if (arrData[i] > max) max = arrData[i];
  }
  return max;
}

function cariTerkecil(arrData) {
  let min = arrData[0];
  for (let i = 1; i < arrData.length; i++) {
    if (arrData[i] < min) min = arrData[i];
  }
  return min;
}

function hitungJumlahGenap(arrData) {
  let sum = 0;
  for (let num of arrData) {
    if (num % 2 === 0) sum += num;
  }
  return sum;
}

function hitungJumlahGanjil(arrData) {
  let sum = 0;
  for (let num of arrData) {
    if (num % 2 !== 0) sum += num;
  }
  return sum;
}

function totalPositif(nAwal, nAkhir) {
  let sum = 0;
  for (let i = nAwal; i <= nAkhir; i++) {
    if (i > 0) sum += i;
  }
  return sum;
}

function totalGenapRentang(nAwal, nAkhir) {
  let sum = 0;
  for (let i = nAwal; i <= nAkhir; i++) {
    if (i % 2 === 0) sum += i;
  }
  return sum;
}

function totalGanjilRentang(nAwal, nAkhir) {
  let sum = 0;
  for (let i = nAwal; i <= nAkhir; i++) {
    if (i % 2 !== 0) sum += i;
  }
  return sum;
}

function isPrima(n) {
  if (n <= 1) return false;
  for (let i = 2; i <= Math.sqrt(n); i++) {
    if (n % i === 0) return false;
  }
  return true;
}

function getDaftarPrima(nAwal, nAkhir) {
  let list = [];
  for (let i = nAwal; i <= nAkhir; i++) {
    if (isPrima(i)) list.push(i);
  }
  return list;
}

function getTotalJumlahPrima(nAwal, nAkhir) {
  let list = getDaftarPrima(nAwal, nAkhir);
  let total = 0;
  for (let p of list) total += p;
  return total;
}

// ==========================================
// KUMPULAN FUNGSI BINTANG (FORMASI 1 - 20)
// ==========================================

function cetakBintang1(n = 6) {
  let str = "";
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n - i; j++) str += "*";
    for (let j = 0; j < i * 2; j++) str += " ";
    for (let j = 0; j < n - i; j++) str += "*";
    str += "\n";
  }
  return str;
}

function cetakBintang2(n = 6) {
  let str = "";
  for (let i = 1; i <= n; i++) {
    for (let s = 0; s < n - i; s++) str += " ";
    for (let b = 0; b < 2 * i - 1; b++) str += "*";
    str += "\n";
  }
  return str;
}

function cetakBintang3(n = 3) {
  let str = "";
  for (let i = 1; i <= n; i++) {
    for (let j = 0; j < i; j++) str += "*";
    str += "\n";
  }
  for (let i = 1; i <= n; i++) {
    for (let s = 0; s < n - i; s++) str += " ";
    for (let j = 0; j < i; j++) str += "*";
    str += "\n";
  }
  return str;
}

function cetakBintang4(n = 6) {
  let str = "";
  for (let i = 1; i <= n; i++) {
    for (let j = 0; j < i; j++) str += "*";
    for (let j = 0; j < (n - i) * 2; j++) str += " ";
    for (let j = 0; j < i; j++) str += "*";
    str += "\n";
  }
  return str;
}

function cetakBintang5(n = 6) {
  let str = "";
  for (let i = n; i >= 1; i--) {
    for (let s = 0; s < n - i; s++) str += " ";
    for (let b = 0; b < 2 * i - 1; b++) str += "*";
    str += "\n";
  }
  return str;
}

function cetakBintang6(n = 3) {
  let str = "";
  for (let i = 1; i <= n; i++) {
    for (let s = 0; s < n - i; s++) str += " ";
    for (let j = 0; j < i; j++) str += "*";
    str += "\n";
  }
  for (let i = 1; i <= n; i++) {
    for (let j = 0; j < i; j++) str += "*";
    str += "\n";
  }
  return str;
}

function cetakBintang7(n = 5) {
  let str = "";
  for (let i = n; i >= 1; i--) {
    for (let s = 0; s < n - i; s++) str += " ";
    for (let j = 0; j < i; j++) str += "*";
    str += "\n";
  }
  for (let i = 2; i <= n; i++) {
    for (let s = 0; s < n - i; s++) str += " ";
    for (let j = 0; j < i; j++) str += "*";
    str += "\n";
  }
  return str;
}

function cetakBintang8(n = 6) {
  let str = "";
  for (let i = 0; i < n - 1; i++) {
    str += "0" + "*".repeat(n - 1) + "\n";
  }
  str += "0".repeat(n) + "\n";
  return str;
}

function cetakBintang9(n = 6) {
  let str = "";
  for (let i = 0; i < n - 1; i++) {
    str += "*".repeat(n - 1) + "0\n";
  }
  str += "0".repeat(n) + "\n";
  return str;
}

function cetakBintang10(n = 5) {
  let str = "";
  for (let i = n; i >= 1; i--) {
    for (let j = 0; j < i; j++) str += "*";
    str += "\n";
  }
  for (let i = 2; i <= n; i++) {
    for (let j = 0; j < i; j++) str += "*";
    str += "\n";
  }
  return str;
}

function cetakBintang11(n = 6) {
  let str = "0".repeat(n) + "\n";
  for (let i = 0; i < n - 1; i++) {
    str += "0" + "*".repeat(n - 1) + "\n";
  }
  return str;
}

function cetakBintang12(n = 6) {
  let str = "0".repeat(n) + "\n";
  for (let i = 0; i < n - 1; i++) {
    str += "*".repeat(n - 1) + "0\n";
  }
  return str;
}

function cetakBintang13(n = 6) {
  let str = "";
  for (let i = 1; i <= n; i++) {
    str += "0".repeat(i) + "*".repeat(n - i + 1) + "\n";
  }
  return str;
}

function cetakBintang14(n = 6) {
  let str = "";
  for (let i = 1; i <= n; i++) {
    str += "*".repeat(i) + "0".repeat(n - i + 1) + "\n";
  }
  return str;
}

function cetakBintang15(n = 6) {
  let str = "";
  for (let i = n; i >= 1; i--) {
    str += "0".repeat(i) + "*".repeat(n - i + 1) + "\n";
  }
  return str;
}

function cetakBintang16(n = 6) {
  let str = "";
  for (let i = n; i >= 1; i--) {
    str += "0".repeat(n - i + 1) + "*".repeat(i) + "\n";
  }
  return str;
}

function cetakBintang17(n = 6) {
  let str = "";
  for (let i = 1; i <= n; i++) {
    str += "0".repeat(n - i + 1) + "*" + "0".repeat(i - 1) + "\n";
  }
  return str;
}

function cetakBintang18(n = 6) {
  let str = "";
  for (let i = 1; i <= n; i++) {
    str += "0".repeat(i - 1) + "*" + "0".repeat(n - i + 1) + "\n";
  }
  return str;
}

function cetakBintang19(rows = 6, cols = 7) {
  let str = "";
  for (let r = 0; r < rows; r++) {
    if (r === 0 || r === rows - 1) {
      str += "0".repeat(cols) + "\n";
    } else {
      str += "0" + "*".repeat(cols - 2) + "0\n";
    }
  }
  return str;
}

function cetakBintang20(rows = 6, cols = 7) {
  let str = "";
  let pola = ["0", "*", "="];
  for (let r = 0; r < rows; r++) {
    let char = pola[Math.floor(r / 2) % 3];
    str += char.repeat(cols) + "\n";
  }
  return str;
}

// ==========================================
// DRIVER UTAMA (RUNNER)
// ==========================================

function main() {
  console.log("==========================================");
  console.log("HASIL EKSEKUSI TUGAS PRAKTIKUM (SOAL 1 - 50)");
  console.log("==========================================");

  // Testing Soal 1 - 3
  console.log("1. Balik Kalimat (Hallo)      =>", balikKalimat("Hallo"));
  console.log("2. Hitung Huruf 'l' (Hallo)   =>", hitungJumlahHuruf("Hallo", "l"));
  console.log("3. Hitung Karakter (Hallo)    =>", hitungJumlahKarakter("Hallo"));

  // Testing Pola Angka Soal 4 - 15
  console.log("4.  Pola =>", polaSoal4());
  console.log("5.  Pola =>", polaSoal5());
  console.log("6.  Pola =>", polaSoal6());
  console.log("7.  Pola =>", polaSoal7());
  console.log("8.  Pola =>", polaSoal8());
  console.log("9.  Pola =>", polaSoal9());
  console.log("10. Pola =>", polaSoal10());
  console.log("11. Pola =>", polaSoal11());
  console.log("12. Pola =>", polaSoal12());
  console.log("13. Pola =>", polaSoal13());
  console.log("14. Pola =>", polaSoal14());
  console.log("15. Pola =>", polaSoal15());

  // Testing Deret Soal 16 - 21
  console.log("16. Deret =>", deretSoal16());
  console.log("17. Deret =>", deretSoal17());
  console.log("18. Deret =>", deretSoal18());
  console.log("19. Deret =>", deretSoal19());
  console.log("20. Deret =>", deretSoal20());
  console.log("21. Deret =>", deretSoal21());

  // Testing Soal 22 - 33
  console.log("22. Faktorial 3!             =>", hitungFaktorial(3));
  console.log("23. Fibonacci Max 34         =>", deretFibonacci(34));
  console.log("24. Kabisat Akhir 0 (1900-2025) =>", tahunKabisatAkhiran(1900, 2025, 0));
  console.log("25. Kabisat Akhir 2 (1900-2025) =>", tahunKabisatAkhiran(1900, 2025, 2));
  console.log("26. Kabisat Akhir 4 (1900-2025) =>", tahunKabisatAkhiran(1900, 2025, 4));
  console.log("27. Kabisat Akhir 6 (1900-2025) =>", tahunKabisatAkhiran(1900, 2025, 6));
  console.log("28. Kabisat Akhir 8 (1900-2025) =>", tahunKabisatAkhiran(1900, 2025, 8));

  console.log("29. Habis Dibagi 3 (1-30)    =>", kelipatanPembagi(1, 30, 3));
  console.log("30. Habis Dibagi 4 (1-30)    =>", kelipatanPembagi(1, 30, 4));
  console.log("31. Habis Dibagi 5 (1-30)    =>", kelipatanPembagi(1, 30, 5));
  console.log("32. Habis Dibagi 6 (1-30)    =>", kelipatanPembagi(1, 30, 6));
  console.log("33. Habis Dibagi 7 (1-30)    =>", kelipatanPembagi(1, 30, 7));

  // Animasi Soal 34 - 41
  for (let i = 34; i <= 41; i++) {
    console.log(simulasiAnimasi(i, "Animasi pergerakan angka 0"));
  }

  // Testing Array Data (Soal 42 - 45)
  let sampleInput = [12, 5, 23, 8, 45, 67, 2, 89, 34, 10, 15];
  console.log("Data Input (Min 10) =>", sampleInput);
  console.log("42. Bilangan Terbesar        =>", cariTerbesar(sampleInput));
  console.log("43. Bilangan Terkecil        =>", cariTerkecil(sampleInput));
  console.log("44. Jumlah Bilangan Genap    =>", hitungJumlahGenap(sampleInput));
  console.log("45. Jumlah Bilangan Ganjil   =>", hitungJumlahGanjil(sampleInput));

  // Testing Penjumlahan & Prima (Soal 46 - 50)
  console.log("46. Total Bulat Positif (1-10) =>", totalPositif(1, 10));
  console.log("47. Total Genap (1-10)         =>", totalGenapRentang(1, 10));
  console.log("48. Total Ganjil (1-10)        =>", totalGanjilRentang(1, 10));
  console.log("49. Daftar Prima (1-30)        =>", getDaftarPrima(1, 30).join(", "));
  console.log("50. Total Jumlah Prima (1-30)  =>", getTotalJumlahPrima(1, 30));

  console.log("\n==========================================");
  console.log("FORMASI BINTANG DAN KARAKTER (1 - 20)");
  console.log("==========================================");

  console.log("Formasi 1:\n" + cetakBintang1());
  console.log("Formasi 2:\n" + cetakBintang2());
  console.log("Formasi 3:\n" + cetakBintang3());
  console.log("Formasi 4:\n" + cetakBintang4());
  console.log("Formasi 5:\n" + cetakBintang5());
  console.log("Formasi 6:\n" + cetakBintang6());
  console.log("Formasi 7:\n" + cetakBintang7());
  console.log("Formasi 8:\n" + cetakBintang8());
  console.log("Formasi 9:\n" + cetakBintang9());
  console.log("Formasi 10:\n" + cetakBintang10());
  console.log("Formasi 11:\n" + cetakBintang11());
  console.log("Formasi 12:\n" + cetakBintang12());
  console.log("Formasi 13:\n" + cetakBintang13());
  console.log("Formasi 14:\n" + cetakBintang14());
  console.log("Formasi 15:\n" + cetakBintang15());
  console.log("Formasi 16:\n" + cetakBintang16());
  console.log("Formasi 17:\n" + cetakBintang17());
  console.log("Formasi 18:\n" + cetakBintang18());
  console.log("Formasi 19:\n" + cetakBintang19());
  console.log("Formasi 20:\n" + cetakBintang20());
}

// Jalankan Program
main();