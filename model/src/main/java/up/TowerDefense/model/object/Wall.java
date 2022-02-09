package up.TowerDefense.model.object;

public class Wall extends PlaceableObstacle{

    private static int newHealthScale = 5;
    private static int newBuyingCost = 100;
    private static float newSize = 1.0f;
    private static String image;

    public Wall(float x, float y) {
        super(x, y, newSize, newHealthScale, newHealthScale, ObsType.WALL, newBuyingCost,image);

    }
}
