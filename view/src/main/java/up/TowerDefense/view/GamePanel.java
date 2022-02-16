package up.TowerDefense.view;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int sizeCase = 16;
    final int nbCol = 64;
    final int nbRow = 64;
    final int windowWidth = sizeCase*nbCol;
    final int windowHeight = sizeCase*nbRow;

    Thread gameThread;
    MapGenerator mapGen;

    public GamePanel(String imgPath){
        startThread();
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setBackground(Color.GRAY);
        mapGen = new MapGenerator(this,imgPath);

        this.setFocusable(true);

    }


    public void startThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        while (gameThread != null){
            update();
            repaint();
        }
    }

    public void update(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        mapGen.draw(g2D);
        g2D.dispose();
    }


}