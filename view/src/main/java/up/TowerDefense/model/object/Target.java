package up.TowerDefense.model.object;

public class Target extends DestructibleObstacle{

    private final static int STARTING_LIVES = 16;
/*
Todo : image à mettre directement dans la classe
    revoir les attributs
    size dépendamment du schéma donné
 */
    public Target(double x, double y, int size, String image) {
        super(x, y, size, STARTING_LIVES, STARTING_LIVES, ObsType.TARGET,image);
    }

}
