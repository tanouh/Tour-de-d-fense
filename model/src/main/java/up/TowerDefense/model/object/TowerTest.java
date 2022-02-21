package up.TowerDefense.model.object;

import java.awt.image.BufferedImage;

public class TowerTest extends Tower{

    private static final int newSize = 3;
    // variables à déterminer
    private static double newBuyingCost=100;
    private static double startingRange = 5.0;
    private static double startingPower = 2.0;
    private static int startingUpgradeCost = 200;
    private static double startingReloadTime = 1000;
    private static long newLastAttackTime = 0;
    private static Type towerType = Tower.Type.TOWERTEST;
    private static String image;

    public TowerTest(double x, double y) {
        super(x, y, newSize, newBuyingCost,startingRange, startingPower, startingUpgradeCost, startingReloadTime, newLastAttackTime, towerType,image);
    }



}
