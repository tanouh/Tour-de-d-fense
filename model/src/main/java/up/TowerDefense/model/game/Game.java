package up.TowerDefense.model.game;

import up.TowerDefense.model.map.Board;

public class Game {

    private final static double STARTING_CREDITS= 500;
    private final static int STARTING_LIVES = 16;
    private static double credits = STARTING_CREDITS;
    private static int lives = STARTING_LIVES;
    private static Board board = new Board();
//    private static int wavesLeft;

    private static Game instance = null;

    public static Game getPlayer(){
        if( instance == null){
            instance = new Game();
        }
        return instance;
    }


    public static double getCredits() {
        return credits;
    }
    public static int getLives() { return lives; }
    public static Board getBoard(){ return board; }
//    public static int getWavesLeft(){ return wavesLeft;}

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
}
