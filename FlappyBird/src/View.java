import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// kelas View bertanggung jawab untuk menampilkan elemen game ke layar
public class View extends JPanel {

    int width = 360;
    int height = 640;
    private Logic logic; // referensi ke logika game

    public View(Logic logic) {
        this.logic = logic;
        setPreferredSize(new Dimension(width, height)); // ukuran panel
        setBackground(Color.cyan); // warna latar
        setFocusable(true); // agar bisa menerima input keyboard
        addKeyListener(logic); // hubungkan key listener ke logika
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g); // panggil fungsi gambar
    }

    public void draw(Graphics g) {
        // gambar pemain
        Player player = logic.getPlayer();
        if (player != null) {
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(),
                    player.getWidth(), player.getHeight(), null);
        }

        // gambar pipa-pipa
        ArrayList<Pipe> pipes = logic.getPipes();
        if (pipes != null) {
            for (Pipe pipe : pipes) {
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(),
                        pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        // tampilkan teks saat game over
        if (logic.gameOver) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("GAME OVER!", 80, 300);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Press 'R' to Restart", 100, 340);
        }
    }
}
