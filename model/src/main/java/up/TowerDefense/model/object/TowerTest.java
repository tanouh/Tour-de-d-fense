package up.TowerDefense.model.object;

public class TowerTest extends Tower{

    private static final int [] newSize = new int []{64,64};
    private static double newBuyingCost=100;
    private static double newRange = 5.0;
    private static double newPower = 2.0;
    private static int newUpgradeCost = 200;
    private static double newReloadTime = 1000;
    private static long newLastAttackTime = 0;
    private static Type towerType = Tower.Type.TOWERTEST;

    public TowerTest(double x, double y) {
        super(x, y, newSize, newBuyingCost,newRange, newPower, newUpgradeCost, newReloadTime, newLastAttackTime, towerType);
    }


}
