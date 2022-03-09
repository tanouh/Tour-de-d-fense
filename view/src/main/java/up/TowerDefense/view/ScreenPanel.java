package up.TowerDefense.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static up.TowerDefense.view.KeyAction.*;
import static up.TowerDefense.view.KeyAction.Action.*;

public class ScreenPanel extends JPanel implements Runnable, MouseListener {
    //Paramètrages de l'écran
    public int nbCol = 25;
    public int nbRow = 16;
    public int sizeCase = 40;

    public int windowWidth = sizeCase*nbCol;
    public int windowHeight = sizeCase*nbRow;


    public int originalTileSize = 8;
    public int scale = 3;

    public int tileSize = originalTileSize*scale;

    //Paramètrage du monde de jeu
    public static final int MAX_WORLD_COL = 100;
    public static final int MAX_WORLD_ROW= 64;


    private Color background = new Color(173,175,192);
    private Color foreground = new Color(30,35,71);
    private JLabel title = new JLabel("project Covid Defense");

    GameWindow gameWindow;
    Thread gameThread;
    public MapGenerator mapGen;
    int FPS = 60; //Frame per second


    public int mouseX;
    public int mouseY;
    public boolean mousePressed;

    public Camera camera;

    public InputMap inputMap;
    public ActionMap actionMap;


    public ScreenPanel(GameWindow gameWindow){
        this.gameWindow = gameWindow;

        mapGen= new MapGenerator(this, "/map3.png"); /*A modifier : ajouter un paramètrage pour l'image*/

        startThread();
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setBackground(Color.GRAY);

        camera = new Camera(this);
        setKeyBinding();

        this.setDoubleBuffered(true);
        this.addMouseListener(this);

        this.setFocusable(true);
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
        camera.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        mapGen.draw(g2D);
        mapGen.drawComponents(g2D);
        //camera.draw(g2D);
        g2D.dispose();
    }

    /**
     * Identitifie les touches qui pourront être reconnues par le composant
     */
    public void setKeyBinding(){
        inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap= this.getActionMap();

        //Equivalent à keyPressed
        inputMap.put(KeyStroke.getKeyStroke( KeyEvent.VK_UP,0,false),UP);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0,false),DOWN);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0,false),LEFT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0,false),RIGHT);

        //Equivalent à keyReleased
        inputMap.put(KeyStroke.getKeyStroke( KeyEvent.VK_UP,0,true),STOP);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0,true),STOP);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0,true),STOP);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0,true),STOP);


        actionMap.put(UP,new KeyAction(MOVE_UP));
        actionMap.put(DOWN,new KeyAction(MOVE_DOWN));
        actionMap.put(LEFT,new KeyAction(MOVE_LEFT));
        actionMap.put(RIGHT,new KeyAction(MOVE_RIGHT));
        actionMap.put(STOP,new KeyAction(STAY_STILL));


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX()/sizeCase;
        mouseY = e.getY()/sizeCase ;
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