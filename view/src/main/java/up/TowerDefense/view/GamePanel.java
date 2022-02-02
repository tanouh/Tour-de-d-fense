package up.TowerDefense.view;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int sizeCase = 48;
    final int nbCol = 10;
    final int nbRow = 8;
    final int windowWidth = sizeCase*nbCol;
    final int windowHeight = sizeCase*nbRow;

    Thread gameThread;

    public GamePanel(){
        startThread();
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setBackground(Color.GRAY);
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
        g2D.setColor(Color.BLUE);
        g2D.fillRect(100,100, 100, 100);
        g2D.dispose();
    }

}