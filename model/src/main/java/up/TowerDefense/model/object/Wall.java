package up.TowerDefense.model.object;

import java.awt.image.BufferedImage;

public class Wall extends PlaceableObstacle{

    private static int newHealthScale = 5;
    private static double newBuyingCost = 100;
    private static float newSize = 1.0f;
    private static String image;

    public Wall(double x, double y) {
        super(x, y, newSize, newHealthScale, newHealthScale, ObsType.WALL, newBuyingCost,image);

    }
}
