package up.TowerDefense.model.object;


public class Target extends DestructibleObstacle{

    private final static int STARTING_LIVES = 16;

    public Target(double x, double y, int [] size) {
        super(x, y, size, STARTING_LIVES,STARTING_LIVES, ObsType.TARGET);
    }
}
