package up.TowerDefense.model.game;

import up.TowerDefense.model.map.Board;

public class Game {

    private final static double STARTING_CREDITS= 1000;
    private final static int STARTING_LIVES = 16;
    private static double credits = STARTING_CREDITS;
    private static int lives = STARTING_LIVES;
    private static Board board = new Board();
    private static int numberWavesTotal;
    private static int level;
    private static int wavesLeft;
    private static int nbEnemyLeft = 0;
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

    public Game(int numberWaves, int backgroundMusic, int gameSound, int speed, int lvl){
        numberWavesTotal = numberWaves;
        wavesLeft = numberWaves;
        bgMusic = backgroundMusic;
        soundLevel = gameSound;
        gameSpeed = speed;
        level = lvl;
        reset();
    }

    public static double getCredits() {
        return credits;
    }
    public static int getLives() { return lives; }
    public static Board getBoard(){ return board; }
    public static int getLevel(){ return level; }
    public static int getWavesLeft(){ return wavesLeft;}
    public static int getNbWavesTotal(){ return numberWavesTotal; }
    public static int getNbEnemyLeft(){ return nbEnemyLeft;}
    public static String[] getListTowerTypes(){ return listTowerTypes; }
    public static int getCurrentlyPlacing(){ return currentlyPlacing; }

    public static void reset(){
        credits=STARTING_CREDITS;
        lives=STARTING_LIVES;
    }

    public static void setCredits(double deltaCredits) {
        Game.credits += deltaCredits;
    }

    public static void setLives(int deltaLives) {
        Game.lives += deltaLives;
    }

    public static void setWavesLeft(int nbWavesLeft) { wavesLeft = nbWavesLeft; }

    public static void setCurrentlyPlacing(int typeObstacle){
        currentlyPlacing = typeObstacle;
    }

    public void applyOptions(int backgroundMusic, int gameSound, int speed){
        bgMusic = backgroundMusic;
        soundLevel = gameSound;
        gameSpeed = speed;
    }

    public static boolean gameWon(){
        return (wavesLeft == 0 && nbEnemyLeft == 0 && lives > 0);
    }

    public static boolean gameLost(){
        return ((wavesLeft > 0 || nbEnemyLeft > 0) && lives == 0);
    }
}
