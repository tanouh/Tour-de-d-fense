package up.TowerDefense.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ScreenPanel extends JPanel implements Runnable, MouseListener {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (screenSize.width*4)/5;
    int screenHeight = (screenSize.height*4)/5;

    public int nbCol = 100;
    public int nbRow = 64;
    public int widthCase = 8;
    public int heightCase =8;

    public int windowWidth = widthCase*nbCol;
    public int windowHeight = heightCase*nbRow;

    private Color background = new Color(173,175,192);
    private Color foreground = new Color(30,35,71);
    private JLabel title = new JLabel("project Covid Defense");

    Thread gameThread;
    public MapGenerator mapGen;
    int FPS = 60; //Frame per second


    public int mouseX;
    public int mouseY;
    public boolean mousePressed;



    public ScreenPanel(){
        mapGen= new MapGenerator(this, "/map3.png"); /*A modifier : ajouter un paramètrage pour l'image*/


        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setBackground(Color.GRAY);

        this.addMouseListener(this);
        this.setFocusable(true);
        startThread();


    }

    public void startThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }

    }

    public void update(){
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        mapGen.draw(g2D);
        mapGen.drawComponents(g2D);
        g2D.dispose();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX()/heightCase;
        mouseY = e.getY()/widthCase ;
        mousePressed = true;
        try {
            mapGen.addObstacle(mouseX, mouseY);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed =false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mousePressed = false;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mousePressed = false;
    }
}