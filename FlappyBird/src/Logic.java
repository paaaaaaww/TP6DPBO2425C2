import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// kelas Logic berisi logika utama permainan Flappy Bird
public class Logic implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    int playerStartPosX = frameWidth / 2;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;
    int gravity = 1;
    int pipeVelocityX = -2;

    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    Timer gameLoop; // timer untuk update game
    Timer pipesCooldown; // timer untuk spawn pipa
    View view;
    Image birdImage;
    Player player;

    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes; // daftar semua pipa

    boolean gameStarted = false;
    boolean gameOver = false;

    int score = 0;
    JLabel scoreLabel;

    public Logic(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;

        // inisialisasi gambar dan objek player
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);

        // load gambar pipa
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        pipes = new ArrayList<>();

        // timer untuk menambahkan pipa baru tiap 1,5 detik
        pipesCooldown = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (gameStarted && !gameOver) {
                    placePipes();
                }
            }
        });
        pipesCooldown.start();

        // timer utama untuk menjalankan game (60 FPS)
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    // menggerakkan player dan pipa
    public void move() {
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for (Pipe pipe : pipes) {
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);

            // tambah skor saat player melewati pipa bawah
            if (!pipe.passed && pipe.getPosY() > frameHeight / 2 && pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                pipe.passed = true;
                score++;
                updateScoreLabel();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameStarted && !gameOver) {
            move();
            checkCollision();
        }
        if (view != null) {
            view.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    // input keyboard untuk melompat dan restart
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!gameStarted) {
                gameStarted = true;
            }
            if (!gameOver) {
                player.setVelocityY(-10);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            restartGame();
        }
        else if (e.getKeyCode() == KeyEvent.VK_M && gameOver) {
            // Tutup jendela game dan kembali ke menu
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(view);
            topFrame.dispose();
            new Menu(); // buka menu utama lagi
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public void setView(View view) {
        this.view = view;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    // menempatkan dua pipa baru (atas dan bawah)
    public void placePipes() {
        int randomPosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 3;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, randomPosY + openingSpace + pipeHeight, pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    // cek tabrakan dengan pipa atau tanah
    public void checkCollision() {
        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());

        for (Pipe pipe : pipes) {
            Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());
            if (playerRect.intersects(pipeRect)) {
                gameOver = true;
                System.out.println("GAME OVER: Nabrak pipa!");
            }
        }

        if (player.getPosY() > frameHeight - player.getHeight()) {
            gameOver = true;
            System.out.println("GAME OVER: Jatuh!");
        }
    }

    // reset ulang kondisi game
    public void restartGame() {
        System.out.println("Restart game...");
        gameStarted = false;
        gameOver = false;
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);
        pipes.clear();
        score = 0;
        updateScoreLabel();
    }

    // update label skor di layar
    public void updateScoreLabel() {
        if (scoreLabel != null) {
            scoreLabel.setText("Score: " + score);
        }
    }
}
