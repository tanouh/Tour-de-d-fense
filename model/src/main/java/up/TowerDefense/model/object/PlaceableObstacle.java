package up.TowerDefense.model.object;

import up.TowerDefense.model.game.Player;

public class PlaceableObstacle extends DestructibleObstacle{
    protected double buyingCost;
    protected double refundValue;

    public PlaceableObstacle(double x, double y, int [] size, int maxHealth, int currentHealth, ObsType obstacleType, double buyingCost) {
        super(x, y, size, maxHealth, currentHealth,obstacleType);
        this.buyingCost = buyingCost;
        this.refundValue = buyingCost; // augmente à chaque fois que l'obstacle augmente en niveau
    }
    public void refundTower(){
        Player.getPlayer().setCredits(refundValue);
    }

    public double getBuyingCost() {
        return  buyingCost;
    }
    public void setRefundValue(double refundValue) {
        this.refundValue = refundValue;
    }
}
