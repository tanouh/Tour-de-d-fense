package up.TowerDefense.model.object;

public class DestructibleObstacle extends Obstacle{

    protected final int maxHealth;
    protected int currentHealth;
    protected ObsType obstacleType;


    public enum ObsType {
        WALL, TARGET, TOWER
    }

    public DestructibleObstacle(float x, float y, float size, int maxHealth, int currentHealth, ObsType obstacleType, String image) {
        super(x, y, size,image);
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.obstacleType = obstacleType;
    }
}
