package up.TowerDefense.model.object;


public class Wall extends PlaceableObstacle{

    private static int newHealthScale = 5;
    private static double newBuyingCost = 100;
    private static int newSize = 2;
    private static String image = "/veine.png";


    public Wall(double x, double y) {
        super(x, y, newSize, newHealthScale, newHealthScale, ObsType.WALL, newBuyingCost,image,image);
    }

    public static double getPrice(){ return newBuyingCost; }
}
