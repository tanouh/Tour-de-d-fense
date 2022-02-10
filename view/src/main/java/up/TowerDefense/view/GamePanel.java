package up.TowerDefense.view;

import up.TowerDefense.view.shift.UserShift;
import up.TowerDefense.view.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Paramètrages de l'écran
    public final int sizeCase = 48;
    public final int nbCol = 16;
    public final int nbRow = 12;
    public final int windowWidth = sizeCase*nbCol;
    public final int windowHeight = sizeCase*nbRow;

    public final int originalTileSize = 8;
    public final int scale = 3;

    public final int tileSize = originalTileSize*scale;

    //Paramètrages du monde
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth =tileSize*maxWorldCol ;
    public final int worldHeight = tileSize*maxWorldRow;


    KeyHandler kh= new KeyHandler();

    int FPS = 60; //Frame per second

    public UserShift screenShift = new UserShift(this, kh);
    TileManager tileM = new TileManager(this);
    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);

        startThread();
    }

    public void startThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        /*while (gameThread != null){
            update();
            repaint();
        }*/
        double drawInterval =1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) /drawInterval;
            lastTime=currentTime;
            if(delta>=1){
                update();
                repaint();
                delta --;
            }
        }
    }

    public void update(){
        screenShift.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        /*g2D.setColor(Color.BLUE);
        g2D.fillRect(100,100, 100, 100);*/
        tileM.draw(g2D);
        screenShift.draw(g2D);
        g2D.dispose();
    }


}