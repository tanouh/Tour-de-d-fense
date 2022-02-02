package up.TowerDefense.model.object;

public class Tower extends DestructibleObstacle{


    //mettre en abstract ?
    public enum Type{
        TOWERTEST
        //type de tour
        //selon les types de tours size peut être prédéfini
    }

    protected double range;
    protected double power;
    protected int level = 1;
    protected static int maxLevel = 5 ; //à déterminer
    protected int upgradeCost;
    protected static int maxHealth = 5 ; //à voir
    protected double reloadTime; // temps de charge avant de pouvoir attaquer de nouveau
    protected Type towerType;

    public Tower(double x, double y, int[] size, double range, double power, int upgradeCost, double reloadTime, Type twType) {
        super(x, y, size, maxHealth, maxHealth, ObsType.TOWER);
        this.range=range;
        this.power=power;
        this.upgradeCost=upgradeCost;
        this.reloadTime=reloadTime;
        this.towerType=twType;

    }






}
