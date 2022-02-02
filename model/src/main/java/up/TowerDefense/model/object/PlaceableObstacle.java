package up.TowerDefense.model.object;


public class PlaceableObstacle extends DestructibleObstacle{
    protected int buyingCost;
    protected int refundValue;

    public PlaceableObstacle(double x, double y, int [] size, int maxHealth, int currentHealth, ObsType obstacleType, int buyingCost) {
        super(x, y, size, maxHealth, currentHealth,obstacleType);
        this.buyingCost = buyingCost;
        this.refundValue = buyingCost; // augmente à chaque fois que l'obstacle augmente en niveau
    }
}
