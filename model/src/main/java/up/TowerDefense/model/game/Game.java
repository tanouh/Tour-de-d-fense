package up.TowerDefense.model.game;

import up.TowerDefense.model.map.Board;
import up.TowerDefense.model.object.Obstacle;

public class Game {

    private final static double STARTING_CREDITS= 500;
    private final static int STARTING_LIVES = 16;
    private static double credits = STARTING_CREDITS;
    private static int lives = STARTING_LIVES;
    private static Board board = new Board();
    private static int numberWavesTotal;
    private static int wavesLeft;
    private static int bgMusic;
    private static int soundLevel;
    private static int gameSpeed;
    private static int currentlyPlacing = 4; //Type d'obstacle sélectionné (Mur par défaut)

    private static String[] listTowerTypes = {
            "TourTest",
            "Tour anti-champi",
            "Tour Leucocyte T",
            "Anticorps",
            "Mur"
    };

    /**
     * inutile ?
     */
//    private static Game instance = null;
//
//    public static Game getPlayer(){
//        if( instance == null){
//            instance = new Game();
//        }
//        return instance;
//    }

    public Game(int numberWaves, int backgroundMusic, int gameSound, int speed){
        numberWavesTotal = numberWaves;
        wavesLeft = numberWaves;
        bgMusic = backgroundMusic;
        soundLevel = gameSound;
        gameSpeed = speed;

    }

    public static double getCredits() {
        return credits;
    }
    public static int getLives() { return lives; }
    public static Board getBoard(){ return board; }
    public static int getWavesLeft(){ return wavesLeft;}
    public static String[] getListTowerTypes(){ return listTowerTypes; }
    public static int getCurrentlyPlacing(){ return currentlyPlacing; }

    public static void reset(){
        credits=STARTING_CREDITS;
        lives=STARTING_LIVES;
    }

    public static void setCredits(double deltaCredits) {
        Game.credits += credits;
    }

    public static void setLives(int deltaLives) {
        Game.lives += lives;
    }

    public static void setCurrentlyPlacing(int typeObstacle){
        currentlyPlacing = typeObstacle;
    }

    public void applyOptions(int backgroundMusic, int gameSound, int speed){
        bgMusic = backgroundMusic;
        soundLevel = gameSound;
        gameSpeed = speed;
    }
}
