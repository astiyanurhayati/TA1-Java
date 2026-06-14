# Travel Booking System — README

Ringkasan singkat

Aplikasi ini adalah sistem pemesanan perjalanan sederhana (konsol) yang mendukung pencarian dan pemesanan penerbangan serta hotel, melihat dan membatalkan reservasi. Implementasi menggunakan Java dan dirancang untuk tugas penilaian tim.

Desain & Struktur (kelas utama)

- `Main` — entry point, memulai aplikasi.
- `TravelBookingSystem` — kelas utama yang menyediakan menu, input, logika pemrosesan (seed data, pencarian, pemesanan, pembatalan, dan tampilan data).
- `Flight` (implements `Bookable`) — model untuk data penerbangan (nomor, asal, tujuan, harga) dan method `displayDetails()`.
- `Hotel` (implements `Bookable`) — model untuk data hotel (id, nama, lokasi, harga) dan method `displayDetails()`.
- `Bookable` — interface sederhana untuk objek yang bisa ditampilkan/dipesan (`displayDetails`).
- `Reservation` — kelas abstrak yang disegel (`sealed`) dengan subkelas `FlightReservation` dan `HotelReservation`.
- `FlightReservation`, `HotelReservation` — menyimpan referensi ke item yang dipesan dan menampilkan detail reservasi.
- `ConfirmationGenerator` — generator konfirmasi sederhana (counter angka yang meningkat).

Catatan desain

- Menggunakan interface `Bookable` agar `Flight` dan `Hotel` dapat diperlakukan sama saat menampilkan detail.
- `Reservation` disegel untuk membatasi jenis reservasi yang valid (hanya flight/hotel).
- `TravelBookingSystem` bertanggung jawab atas I/O konsol dan list in-memory untuk flights/hotels/reservations. Data tidak persisten.
- Generator konfirmasi sederhana (integer incremental) untuk kemudahan pengujian.

Diagram UML

File diagram UML tersedia di workspace: `uml_diagram.svg` (lokasi: e:\web kulyah\TeamAssesment1\uml_diagram.svg). Buka file tersebut di image viewer atau editor (mis. Windows Explorer, browser) untuk melihat hubungan antar kelas.

Cara membangun dan menjalankan

1) Menggunakan baris perintah Java (PowerShell):

  - Buka PowerShell dan pindah ke folder proyek:
    cd "e:\web kulyah\TeamAssesment1"

  - Kompilasi semua file Java:
    javac *.java

  - Jalankan aplikasi:
    java Main

2) Menjalankan skenario pengujian otomatis (PowerShell script disediakan):

  - Jalankan script pengujian:
    .\run_tests.ps1

  Script tersebut akan mengkompilasi dan menjalankan beberapa skenario; output masing-masing skenario disimpan ke file `* -output.txt` di folder proyek (lihat pesan yang dicetak oleh script).

Kasus uji (ringkasan skenario di `run_tests.ps1`)

- scenario_buildrun: jalankan menu utama lalu pilih 9 (Exit). Tujuan: memastikan program mulai tanpa error.
  Expected: Menu utama tercetak dan program keluar setelah memilih 9.

- scenario_search_flight_success: pilih menu 1, masukkan Origin `Jakarta` dan Destination `Bali`, lalu exit.
  Expected: Ditampilkan flight yang sesuai (contoh output bagian relevan):

  Flight Number : GA101
  Origin        : Jakarta
  Destination   : Bali
  Price         : 1200000

- scenario_search_flight_notfound: cari rute tidak ada (mis. X -> Y). Expected: `No flights found.`

- scenario_book_flight_view: pilih Book Flight (5), pilih flight 1, lalu View Reservations (6). Expected: Reservasi tersimpan dan detail reservasi ditampilkan dengan nomor konfirmasi (mis. 1000).

- scenario_book_hotel_cancel: book hotel (7) pilihan 1, lalu Cancel Reservation (8) dengan nomor konfirmasi 1000, lalu exit. Expected: pesan `Reservation cancelled successfully!` ketika nomor ditemukan.

Contoh pengujian manual singkat

1) Pencarian penerbangan (manual):
  - Input: menu 1, Origin `Jakarta`, Destination `Bali`
  - Expected: tampil GA101 seperti di atas.

2) Pemesanan dan lihat reservasi:
  - Input: 5 (Book Flight) -> pilih 1 -> 6 (View Reservations)
  - Expected: terlihat `Flight Reservation #1000` (atau nomor konfirmasi yang dihasilkan) dan detail flight.

Catatan implementasi & keterbatasan

- Data disimpan hanya di memori (ArrayList). Menutup program menghapus semua data.
- Tidak ada validasi tanggal, jumlah penumpang/kamar, atau fitur pemesanan tingkat lanjut.
- Konfirmasi bersifat sederhana (incremental integer) dan tidak aman untuk produksi.
- Input dikendalikan lewat Scanner; skrip pengujian menggunakan input redirection sehingga skenario otomatis dapat dijalankan.

Lokasi file penting

- Kode sumber: semua file `.java` di folder proyek (contoh: `TravelBookingSystem.java`, `Flight.java`, `Hotel.java`, dll.)
- Script pengujian otomatis: `run_tests.ps1` (lihat isi untuk skenario contoh)
- Diagram UML: `uml_diagram.svg`

Komentar kode

Sumber telah berisi komentar pendek pada beberapa metode (mis. bagian seed data, validasi input). Untuk menjelaskan logika tambahan, lihat `TravelBookingSystem.java` (method-level comments dan struktur menu) dan masing-masing kelas model yang sederhana.

Instruksi penilaian / bukti pengujian

- Untuk bukti pengujian otomatis, jalankan `.\run_tests.ps1` dari PowerShell; periksa file output yang dihasilkan untuk tiap scenario.
- Untuk bukti manual, rekam konsol saat menjalankan langkah pada bagian "Contoh pengujian manual".

Jika butuh README diperluas (mis. menambahkan diagram UML embed atau contoh output lengkap), beri tahu agar saya tambahkan versi yang lebih lengkap atau file contoh hasil eksekusi.
