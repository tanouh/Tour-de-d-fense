package up.TowerDefense.model.game;

public class Player {
    private static double credits;
    private static int lives;
    private final static double STARTING_CREDITS= 500;
    private final static int STARTING_LIVES = 16;


    public static double getCredits() {
        return credits;
    }
    public void reset(){
        credits=STARTING_CREDITS;
        lives=STARTING_LIVES;
    }
}
