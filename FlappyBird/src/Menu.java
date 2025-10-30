import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    JButton playButton;
    JButton exitButton;

    public Menu() {
        setTitle("Flappy Bird Menu");
        setSize(360, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(new Color(135, 206, 250)); // langit biru

        // Judul game di bagian atas
        JLabel title = new JLabel("Flappy Bird", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setBounds(30, 150, 300, 60);
        title.setForeground(Color.red);
        add(title);

        // Tombol untuk mulai bermain
        playButton = new JButton("Play Game");
        playButton.setBounds(110, 280, 140, 40);
        playButton.setFont(new Font("Arial", Font.BOLD, 16));
        playButton.setFocusPainted(false);
        playButton.addActionListener(this);
        add(playButton);

        // Tombol untuk keluar dari game
        exitButton = new JButton("Exit");
        exitButton.setBounds(110, 340, 140, 40);
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(this);
        add(exitButton);

        setVisible(true);
    }

    // Aksi ketika tombol diklik
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            dispose(); // tutup jendela menu
            new App(); // buka jendela game utama
        } else if (e.getSource() == exitButton) {
            System.exit(0); // keluar dari aplikasi
        }
    }

    public static void main(String[] args) {
        new Menu(); // jalankan menu utama
    }
}
