package up.TowerDefense.model.object;

import up.TowerDefense.model.game.Game;

public class PlaceableObstacle extends DestructibleObstacle{
    protected double buyingCost;
    protected double refundValue;

    public PlaceableObstacle(double x, double y, int size, int maxHealth, int currentHealth, ObsType obstacleType, double buyingCost, String image) {
        super(x, y, size, maxHealth, currentHealth,obstacleType, image);
        this.buyingCost = buyingCost;
        this.refundValue = buyingCost; // augmente à chaque fois que l'obstacle augmente en niveau
    }

    /**
     * Remboursement de la valeur de l'obstacle après sa destruction
     */
    public void refundTower(){
        Game.setCredits(refundValue);
    }

    /**
     * @return le prix de construction de l'obstacle
     */
    public double getBuyingCost() {
        return  buyingCost;
    }

    /**
     * @return la valeur de l'obstacle (augmente au fur et à mesure où la tour évolue)
     */
    public double getRefundValue() {
        return refundValue;
    }

    /** Modifie la valeur d'un obstacle
     * @param refundValue sa nouvelle valeur
     */
    public void setRefundValue(double refundValue) {
        this.refundValue = refundValue;
    }
}
