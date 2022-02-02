package up.TowerDefense.model.object;

public class Target extends DestructibleObstacle{
    //classe qui pourrait ne pas exister?

    public Target(double x, double y, int size, int maxHealth, int currentHealth) {
        super(x, y, size, maxHealth, currentHealth, ObsType.TARGET);
    }
}
