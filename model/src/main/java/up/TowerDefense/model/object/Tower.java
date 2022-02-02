package up.TowerDefense.model.object;

public class Tower extends DestructibleObstacle{

    public enum towerType{
        //type de tour
        //selon les types de tours size peut être prédéfini
    }
    protected double range;
    protected double power;
    protected int level = 1;
    protected static int maxLevel = 5 ; //à déterminer
    protected int upgradeCost;
    protected static int maxHealth = 5 ; //à voir
    protected double reloadTime;
    protected towerType towerType;


    public Tower(double x, double y, int size, int currentHealth) {
        super(x, y, size, maxHealth, currentHealth, obsType.TOWER);
    }





}
