package up.TowerDefense.view;

import javax.swing.*;
import java.awt.*;

public class ScreenPanel extends JPanel implements Runnable {
    final int sizeCase = 48;
    final int nbCol = 10;
    final int nbRow = 8;
    final int windowWidth = sizeCase*nbCol;
    final int windowHeight = sizeCase*nbRow;
    final int widthCase = 10;
    final int heightCase = 10;
    private JPanel header = new JPanel(new GridLayout(1,5));
    private Color background = new Color(173,175,192);
    private Color foreground = new Color(30,35,71);
    private JLabel title = new JLabel("project Covid Defense");
    private GameWindow gameWindow;
    int[] c = {100, 100};

    Thread gameThread;

    public ScreenPanel(){
        this.setLayout(new BorderLayout());
        this.add(header, BorderLayout.NORTH);


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
        //while (gameThread != null){
            update();
            repaint();
        //}
    }

    public void update(){
        c[0] += 10;
        c[1] += 10;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.BLUE);
        g2D.fillRect(c[0],c[1], 100, 100);
        g2D.dispose();
    }

}