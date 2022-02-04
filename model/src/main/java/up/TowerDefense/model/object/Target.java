package up.TowerDefense.model.object;

public class Target extends DestructibleObstacle{

    private final static int STARTING_LIVES = 16;
    private final static String image = "";

    public Target(float x, float y, float size) {
        super(x, y, size, STARTING_LIVES, STARTING_LIVES, ObsType.TARGET,image);
    }

}
