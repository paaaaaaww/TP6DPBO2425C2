import javax.swing.*;

public class App {
    public App() {

        // membuat frame utama untuk game
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // menutup program saat jendela ditutup
        frame.setSize(360, 640); // ukuran jendela
        frame.setLocationRelativeTo(null); // posisi jendela di tengah layar
        frame.setResizable(false); // mencegah jendela diubah ukurannya

        // label untuk menampilkan skor
        JLabel scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20)); // atur font
        scoreLabel.setForeground(java.awt.Color.BLACK); // warna teks hitam
        scoreLabel.setBounds(10, 10, 150, 30); // posisi dan ukuran label skor

        // membuat objek logika permainan dan tampilan
        Logic logika = new Logic(scoreLabel); // logika menerima label skor
        View tampilan = new View(logika); // tampilan menerima objek logika

        // menghubungkan logika dan tampilan supaya bisa saling berinteraksi
        logika.setView(tampilan);

        // menambahkan label skor ke panel tampilan
        tampilan.setLayout(null);
        tampilan.add(scoreLabel);

        // memberi fokus ke panel agar bisa menerima input dari keyboard
        tampilan.requestFocus();

        // menambahkan panel tampilan ke frame utama
        frame.add(tampilan);
        frame.pack(); // menyesuaikan ukuran komponen di frame
        frame.setVisible(true); // menampilkan jendela game
    }
}
