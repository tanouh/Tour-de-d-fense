package up.TowerDefense.model.object;

public class Wall extends PlaceableObstacle{

    //Peut ne pas exister non plus

    public Wall(double x, double y, int size, int maxHealth, int currentHealth) {
        super(x, y, size, maxHealth, currentHealth, obsType.WALL);
    }
}
