package up.TowerDefense.view.mainComponent;

import up.TowerDefense.model.game.Game;
import up.TowerDefense.model.game.Subwave;
import up.TowerDefense.model.game.Wave;
import up.TowerDefense.view.componentHandler.Camera;
import up.TowerDefense.view.componentHandler.KeyAction;
import up.TowerDefense.view.componentHandler.MapGenerator;
import up.TowerDefense.view.componentHandler.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Timer;

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
    protected GamePanel gamePanel;
    private Thread gameThread = null;
    public boolean paused = false;
    public MapGenerator mapGen;
    int FPS = 60; //Frame per second



    public MouseHandler mouseHandler;
    public Camera camera;
    public Timer timer;
    public long TIME;

    public InputMap inputMap;
    public ActionMap actionMap;


    public ScreenPanel(GameWindow gameWindow, GamePanel gamePanel){
        this.gameWindow = gameWindow;
        this.gamePanel = gamePanel;
        KeyAction.setScreenPanel(this);

        mapGen= new MapGenerator(this, "/map3.png"); /*A modifier : ajouter un paramètrage pour l'image*/

        startThread();
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setBackground(Color.GRAY);

        camera = new Camera(this);
        setKeyBinding();
        mouseHandler = new MouseHandler(this);

        this.setDoubleBuffered(true);
        this.addMouseListener(mouseHandler);

        timer = new Timer();
        timer.schedule(new Subwave(),10000,2000);

        this.setFocusable(true);
    }

    /**
     * Lance le Thread
     */
    public void startThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Met en pause/relance le jeu
     */
    public void setPaused(boolean paused){
        this.paused = paused;
    }

    public boolean isPaused(){ return paused;}

    /**
     * Definit l'intervalle entre deux update du jeu
     * Reaffiche le jeu a chaque fois que cet intervalle est
     * atteint à condition que le jeu ne soit pas en pause
     */
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            if (paused) continue;
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

    /**
     * Met a jour tout l'affichage
     */
    public void update(){
        camera.update();
        gamePanel.updateHeader();
        mapGen.updateCharactersPositions();
    }

    /**
     * "Dessine" la map sur le ScreenPanel
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        mapGen.draw(g2D);
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
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_P,0,false),PAUSE);


        //Equivalent à keyReleased
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0,true),STOP);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0,true),STOP);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0,true),STOP);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0,true),STOP);


        actionMap.put(UP,new KeyAction(MOVE_UP));
        actionMap.put(DOWN,new KeyAction(MOVE_DOWN));
        actionMap.put(LEFT,new KeyAction(MOVE_LEFT));
        actionMap.put(RIGHT,new KeyAction(MOVE_RIGHT));
        actionMap.put(STOP,new KeyAction(STAY_STILL));
        actionMap.put(PAUSE,new KeyAction(PAUSE_GAME));


    }

}