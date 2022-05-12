package up.TowerDefense.view.mainComponent;

import up.TowerDefense.model.character.Enemy;
import up.TowerDefense.model.game.Game;
import up.TowerDefense.model.game.Wave;
import up.TowerDefense.view.componentHandler.Camera;
import up.TowerDefense.view.componentHandler.KeyAction;
import up.TowerDefense.view.componentHandler.MapGenerator;
import up.TowerDefense.view.componentHandler.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static up.TowerDefense.view.componentHandler.KeyAction.Action.*;
import static up.TowerDefense.view.componentHandler.KeyAction.*;


/**
 * Ce composant contient la carte de jeu
 */
public class ScreenPanel extends JPanel implements Runnable{
    //Paramètrages de l'écran
    public static int originalTileSize = GameWindow.widthScreen*95/12000;
    public static int scale;
    public static int tileSize;


    public static int nbCol;
    public static int nbRow;

    public static int windowWidth;
    public static int windowHeight;

    //Paramètrage du monde de jeu
    public static final int MAX_WORLD_COL = 100;
    public static final int MAX_WORLD_ROW= 64;

    public JLabel title = new JLabel("project Covid Defense");
    protected GameWindow gameWindow;
    protected GamePanel gamePanel;
    private Thread gameThread = null;
    public boolean paused;
    private long startPause;

    int FPS = 40; //Frame per second


    public MapGenerator mapGen; // pour générer la carte
    public MouseHandler mouseHandler; // pour contrôler les informations reçues à partir de la souris
    public Camera camera; // pour pouvoir déplacer le champs de vision
    public Wave waves; // les vagues d'ennemis


    //Outils pour gérer les interactions à partir des touches
    public InputMap inputMap;
    public ActionMap actionMap;


    public ScreenPanel(GameWindow gameWindow, GamePanel gamePanel, int level, int zoom){
        this.gameWindow = gameWindow;
        this.gamePanel = gamePanel;
        KeyAction.setScreenPanel(this);
        scale = zoom;
        tileSize = originalTileSize*scale;
        nbCol = GameWindow.widthScreen*4/(5*originalTileSize);
        nbRow = GameWindow.heightScreen*9/(10*originalTileSize);
        windowWidth = originalTileSize*nbCol;
        windowHeight = originalTileSize*nbRow;

        mapGen= new MapGenerator(this, loadMap_(level)); /*A modifier : ajouter un paramètrage pour l'image*/

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

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    /**
     * Choix des maps par niveau
     * @param level
     * @return
     */
    private String loadMap_(int level) {
        switch(level){
            case 1 : return "/mapL1V1.png";
            case 2 : return "/mapL2V3.png";
            case 3 : return "/mapL3V1.png";
            default: return "/map4_V2.png";
        }
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
        if (paused){
            startPause = System.currentTimeMillis();
        }else{
            for (Enemy en : Game.getBoard().getListEnemy()){
                en.addToTotalTimeDelay(System.currentTimeMillis() - startPause);
            }
        }
    }

    public boolean isPaused(){ return paused;}

    public void changedSpeed(int gameSpeed){
        if (Game.getOldGameSpeed() != gameSpeed){
            Game.setOldGameSpeed(gameSpeed);
            mapGen.updateCharactersPaths();
        }
    }

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
        Game.getBoard().launchAllAttacks();
        mapGen.updateProjectilesPos();
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
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0,false),UP);
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