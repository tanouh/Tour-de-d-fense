package up.TowerDefense.model.object;

public class DestructibleObstacle extends Obstacle{
    protected final int maxHealth;
    protected int currentHealth;
    protected obsType obstacleType;

    public enum obsType{
        WALL, TARGET, TOWER
    }

    public DestructibleObstacle(double x, double y, int size, int maxHealth, int currentHealth, obsType obstacleType) {
        super(x, y, size);
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.obstacleType = obstacleType;
    }
}
