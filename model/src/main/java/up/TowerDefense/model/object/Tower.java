package up.TowerDefense.model.object;

import up.TowerDefense.model.game.Player;

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
    private double modifierIncrease = 1.5; // le coefficient d'augmentation de portée et de puissance par upgrade
    protected final static int MAX_LEVEL = 5 ; //à déterminer
    protected int upgradeCost;
    protected final static int STARTING_HEALTH = 5 ; //à voir
    protected double reloadTime; // temps de charge avant de pouvoir attaquer de nouveau
    protected long lastAttackTime;
    protected Type towerType;

    public Tower(float x, float y, float size, int buyingCost, double range, double power, int upgradeCost, double reloadTime, long lastAttackTime, Type twType, String image) {
        super(x, y, size, STARTING_HEALTH, STARTING_HEALTH, ObsType.TOWER, buyingCost,image);
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
    public void setLastAttackTime() {
        this.lastAttackTime = System.currentTimeMillis();
    }
    public double getModifier(){
        return (modifierIncrease*(level -1));
    }

    /**
     * @return true if the tower upgraded successfully
     */
    public boolean upgrade(){
        if (level < MAX_LEVEL){
            //augmentation de la taille ?
            level++;
            power*=getModifier();
            range*=getModifier();
            upgradeCost*=getModifier();
            setRefundValue(upgradeCost);
            Player.getPlayer().setCredits(-1*upgradeCost);

            return true;
        }
        return false;
    }

}
