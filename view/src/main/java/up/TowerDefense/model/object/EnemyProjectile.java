package up.TowerDefense.model.object;


import up.TowerDefense.model.game.StaticFunctions;
import up.TowerDefense.view.componentHandler.MapGenerator;

import java.awt.image.BufferedImage;

public class EnemyProjectile extends Projectile{
    private PlaceableObstacle target;

    public EnemyProjectile(Position initPos, Position destPos, double pPower, int level, PlaceableObstacle target) {
        super(initPos, destPos, pPower, level);
        this.target = target;
    }


    @Override
    public void move() {
        //projectile has hit
        if (target.isAlive()){
            MapGenerator.projectilesList.remove(this);
            return;
        }
        if (Math.abs(xLoc - xDest) < speed || Math.abs(yLoc - yDest)< speed){
            arrivedAtTarget = true;
            target.takeDamage(power);
            MapGenerator.projectilesList.remove(this);
        }
        else{
            xLoc += speed;
            yLoc += speed;
        }
    }

    @Override
    public BufferedImage getImage(){
        return StaticFunctions.loadImage("/projectV1.png");
    }
}
