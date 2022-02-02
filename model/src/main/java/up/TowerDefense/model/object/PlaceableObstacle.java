package up.TowerDefense.model.object;

public class PlaceableObstacle extends DestructibleObstacle{
    private int cost;

    public PlaceableObstacle(int x, int y, int size, int lifeMax, int lifeCurrent, int cost) {
        super(x, y, size, lifeMax, lifeCurrent);
        this.cost = cost;
    }
}
