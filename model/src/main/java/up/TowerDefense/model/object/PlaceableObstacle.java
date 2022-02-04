package up.TowerDefense.model.object;

import up.TowerDefense.model.game.Player;


public class PlaceableObstacle extends DestructibleObstacle{
    protected int buyingCost;
    protected int refundValue;

    public PlaceableObstacle(float x, float y, float size, int maxHealth, int currentHealth, ObsType obstacleType, int buyingCost, String image) {
        super(x, y, size, maxHealth, currentHealth,obstacleType, image);
        this.buyingCost = buyingCost;
        this.refundValue = buyingCost; // augmente à chaque fois que l'obstacle augmente en niveau
    }
    public void refundObstacle(){
        Player.getPlayer().setCredits(refundValue);
    }

    public double getBuyingCost() {
        return  buyingCost;
    }
    public void setRefundValue(int refundValue) {
        this.refundValue = refundValue;
    }
}
