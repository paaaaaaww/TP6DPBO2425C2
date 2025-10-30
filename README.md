# TP6DPBO2425C2
Saya Fauzia Rahma Nisa mengerjakan Tugas Praktikum 6 dalam mata kuliah Desain dan Pemrograman Berdasarkan Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

**1. Desain program**

**2. Alur Program**

  Program dimulai dari kelas Menu.java. Saat dijalankan, akan muncul jendela berisi judul dan dua tombol yaitu Play Game dan Exit. Jika pemain menekan tombol Play Game, maka jendela menu ditutup dan game utama dijalankan melalui kelas App. Di dalam kelas ini dibuat jendela permainan berukuran 360x640 piksel, label skor, serta dua objek penting yaitu Logic dan View yang saling terhubung.

  Setelah game dimulai, kelas Logic.java mengatur seluruh jalannya permainan menggunakan dua buah timer. Timer pertama berfungsi untuk mengupdate posisi objek sekitar 60 kali per detik, sedangkan timer kedua menambahkan pipa baru setiap 1,5 detik. Saat pemain menekan tombol spasi, burung akan melompat ke atas, dan jika tidak, gravitasi akan menariknya turun. Pipa-pipa bergerak ke kiri secara terus-menerus dan pemain harus menjaga agar burung tidak menabrak.

  Ketika burung berhasil melewati pipa bawah, skor akan +1 dan label skor diperbarui secara otomatis. Namun, jika burung menabrak pipa atau jatuh ke bawah layar, permainan berakhir dan status gameOver akan menjadi benar. Pada saat itu, layar menampilkan tulisan “GAME OVER” serta instruksi untuk menekan tombol R agar dapat memulai ulang permainan. Saat tombol tersebut ditekan, game di-reset: posisi burung kembali ke awal, skor diatur ulang ke nol, dan semua pipa dihapus dari layar.

**3. Dokumentasi saat program dijalankan**
