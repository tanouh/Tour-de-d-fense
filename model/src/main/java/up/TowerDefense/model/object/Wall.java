package up.TowerDefense.model.object;

public class Wall extends PlaceableObstacle{

    private static int newHealthScale = 5;
    private static double newBuyingCost = 100;
    private static int[] newSize = new int []{64,64};

    public Wall(double x, double y) {
        super(x, y, newSize, newHealthScale, newHealthScale, ObsType.WALL, newBuyingCost);
    }
}
