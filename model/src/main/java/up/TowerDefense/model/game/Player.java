package up.TowerDefense.model.game;

public class Player {
    private static int credits;
    private static int lives;
    private final static int STARTING_CREDITS= 500;
    private final static int STARTING_LIVES = 16;

    private static Player instance = null;

    public static Player getPlayer(){
        if( instance == null){
            instance = new Player();
        }
        return instance;
    }


    public static int getCredits() {
        return credits;
    }
    public static void reset(){
        credits=STARTING_CREDITS;
        lives=STARTING_LIVES;
    }

    public static void setCredits(int deltaCredits) {
        Player.credits += credits;
    }

    public static void setLives(int deltaLives) {
        Player.lives += lives;
    }
}
