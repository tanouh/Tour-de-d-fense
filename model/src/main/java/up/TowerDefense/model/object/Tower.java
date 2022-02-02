package up.TowerDefense.model.object;

public class Tower extends PlaceableObstacle{
    private int scope;  //portée de l'attaque
    private int dommage; //dommages que l'attaque fait
    private int fireRate; //delai entre les attaques

    public Tower(int x, int y, int size, int cost, int lifeMax, int lifeCurrent) {  // à modifier
        super(x, y, size, cost, lifeMax, lifeCurrent);
    }
}
