package up.TowerDefense.view.mainComponent;

import up.TowerDefense.model.game.Game;
import up.TowerDefense.model.game.Wave;
import up.TowerDefense.view.componentHandler.Camera;
import up.TowerDefense.view.componentHandler.KeyAction;
import up.TowerDefense.view.componentHandler.MapGenerator;
import up.TowerDefense.view.componentHandler.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Timer;

import static up.TowerDefense.view.componentHandler.KeyAction.Action.*;
import static up.TowerDefense.view.componentHandler.KeyAction.*;

public class ScreenPanel extends JPanel implements Runnable{
    //Paramètrages de l'écran
    public static int originalTileSize = GameWindow.widthScreen*95/12000;
    public static int scale = 2;
    public static int tileSize = originalTileSize*scale;

    public static int sizeCase = 20;
    public static int nbCol = GameWindow.widthScreen*4/(5*originalTileSize);
    public static int nbRow = GameWindow.heightScreen*9/(10*originalTileSize);

    public static int windowWidth = originalTileSize*nbCol;
    public static int windowHeight = originalTileSize*nbRow;

    //Paramètrage du monde de jeu
    public static final int MAX_WORLD_COL = 75;
    public static final int MAX_WORLD_ROW= 50;

    public JLabel title = new JLabel("project Covid Defense");
    protected GameWindow gameWindow;
    protected GamePanel gamePanel;
    private Thread gameThread = null;
    public boolean paused = false;
    public MapGenerator mapGen;
    int FPS = 40; //Frame per second



    public MouseHandler mouseHandler; // pour contrôler les informations reçues à partir de la souris
    public Camera camera; // pour pouvoir déplacer le champs de vision
    public static Timer timer; // un chronomètre pour gérer le lancement des vagues d'ennemis
    public Wave waves;

    public long TIME;

    public InputMap inputMap;
    public ActionMap actionMap;


    public ScreenPanel(GameWindow gameWindow, GamePanel gamePanel){
        this.gameWindow = gameWindow;
        this.gamePanel = gamePanel;
        KeyAction.setScreenPanel(this);
        mapGen= new MapGenerator(this, "/map6.png"); /*A modifier : ajouter un paramètrage pour l'image*/

        startThread();
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setBackground(Color.GRAY);

        camera = new Camera(this);
        setKeyBinding();
        mouseHandler = new MouseHandler(this);

        this.setDoubleBuffered(true);
        this.addMouseListener(mouseHandler);
        waves = new Wave();

        this.setFocusable(true);
        paused = false;
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
        gamePanel.updateSideMenu();
        waves.run();
        mapGen.updateCharactersPositions();
//        mapGen.updateProjectilesPos();
//        System.out.println("test");
        Game.getBoard().launchAllAttacks();
        testVictory();
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

    public void testVictory(){
        if (Game.gameWon()){
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(new EndPanel(true, gameWindow));
            gameWindow.getContentPane().revalidate();
            gameWindow.getContentPane().repaint();
            paused = true;
        }else if (Game.gameLost()){
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(new EndPanel(false, gameWindow));
            gameWindow.getContentPane().revalidate();
            gameWindow.getContentPane().repaint();
            paused = true;
        }
    }

}