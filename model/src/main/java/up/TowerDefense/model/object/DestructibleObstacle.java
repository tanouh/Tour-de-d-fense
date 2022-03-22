package up.TowerDefense.model.object;



public class DestructibleObstacle extends Obstacle{

    protected final int maxHealth;
    protected int currentHealth;
    protected ObsType obstacleType;



    public enum ObsType {
        WALL, TARGET, TOWER
    }

    public DestructibleObstacle(double x, double y, int size, int maxHealth, int currentHealth, ObsType obstacleType, String image) {
        super(x, y, size , image);
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.obstacleType = obstacleType;

    }

    /**
     * @return l'état maximal de l'obstacle , à priori ça va être le point de vie de départ
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * @return l'état actuel de l'obstacle
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /***
     * @return le type de l'obstacle
     */
    public ObsType getObstacleType() {
        return obstacleType;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public boolean isATower(){
        return this.obstacleType == ObsType.TOWER;
    }
}
