package up.TowerDefense.view.mainComponent;

import up.TowerDefense.view.componentHandler.Camera;
import up.TowerDefense.view.componentHandler.KeyAction;
import up.TowerDefense.view.componentHandler.MapGenerator;
import up.TowerDefense.view.componentHandler.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static up.TowerDefense.view.componentHandler.KeyAction.*;
import static up.TowerDefense.view.componentHandler.KeyAction.Action.*;

public class ScreenPanel extends JPanel implements Runnable{
    //Paramètrages de l'écran
    public static int nbCol = 25;
    public static int nbRow = 16;
    public static int sizeCase = 40;

    public static int windowWidth = sizeCase*nbCol;
    public static int windowHeight = sizeCase*nbRow;


    public static int originalTileSize = 8;
    public static int scale = 3;

    public static int tileSize = originalTileSize*scale;

    //Paramètrage du monde de jeu
    public static final int MAX_WORLD_COL = 100;
    public static final int MAX_WORLD_ROW= 64;


    public Color background = new Color(173,175,192);
    public Color foreground = new Color(30,35,71);
    public JLabel title = new JLabel("project Covid Defense");

    protected GameWindow gameWindow;
    private Thread gameThread;
    public MapGenerator mapGen;
    int FPS = 60; //Frame per second


    public MouseHandler mouseHandler;
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
        mouseHandler = new MouseHandler(this);

        this.setDoubleBuffered(true);
        this.addMouseListener(mouseHandler);

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
        camera.draw(g2D);
        mapGen.drawComponents(g2D);
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

}