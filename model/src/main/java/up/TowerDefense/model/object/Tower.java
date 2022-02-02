package up.TowerDefense.model.object;

public class Tower extends PlaceableObstacle{


    //mettre en abstract ?
    public enum Type{
        TOWERTEST
        //type de tour
        //selon les types de tours size peut être prédéfini
    }

    protected double range;
    protected double power;
    protected int level = 1;
    private double modifierIncrease = 1.2; // le coefficient d'augmentation de portée et de puissance par upgrade
    protected static int maxLevel = 5 ; //à déterminer
    protected double upgradeCost;
    protected static int maxHealth = 5 ; //à voir
    protected double reloadTime; // temps de charge avant de pouvoir attaquer de nouveau
    protected long lastAttackTime;
    protected Type towerType;

    public Tower(double x, double y, int[] size, double buyingCost, double range, double power, int upgradeCost, double reloadTime, long lastAttackTime, Type twType) {
        super(x, y, size, maxHealth, maxHealth, ObsType.TOWER, buyingCost);
        this.range=range;
        this.power=power;
        this.upgradeCost=upgradeCost;
        this.reloadTime=reloadTime;
        this.lastAttackTime = lastAttackTime;
        this.towerType=twType;
    }

    public boolean canAttack(){
        return (System.currentTimeMillis()-lastAttackTime>=reloadTime);
    }

    public double getModifier(){
        return (modifierIncrease*(level -1));
    }

    /**
     * @return true if the tower upgraded successfully
     */
    public boolean upgrade(){
        if (level < maxLevel){

            //augmentation de la taille ?
            level++;
            power*=getModifier();
            range*=getModifier();
            upgradeCost*=getModifier();
            setRefundValue(upgradeCost);
            //todo modifier la caisse du joueur
            return true;
        }
        return false;
    }








}
