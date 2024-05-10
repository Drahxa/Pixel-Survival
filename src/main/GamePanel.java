package main;

import java.awt.*;

import javax.swing.JPanel;



public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 tile ( so we can actually see the game )
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels


    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //Set Player's default position

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;



    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        while(gameThread != null){

//           System.out.println("The game loop is running");

             // 1 UPDATE: update information such as character positions
            update();
             // 2 DRAW: draw the screen with the updated information
            repaint();
        }
    }


    public void update(){
        if(keyH.upPressed == true){
            playerY -= playerSpeed;
        }
        if(keyH.downPressed == true){
            playerY += playerSpeed;
        }
        if(keyH.leftPressed == true){
            playerX -= playerSpeed;
        }
        if(keyH.rightPressed == true){
            playerX += playerSpeed;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);

        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();
    }
}
