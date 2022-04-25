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
        if (!target.isAlive()){
            MapGenerator.enemyProjectilesList.remove(this);
            return;
        }
        if (Math.abs(xLoc - xDest) < speed/2 || Math.abs(yLoc - yDest)< speed/2){
            arrivedAtTarget = true;
            target.takeDamage(power);
            MapGenerator.enemyProjectilesList.remove(this);
        }
        else{
            xLoc += speed*Math.cos(angleOfProjectileInRadians());
            yLoc += speed*Math.sin(angleOfProjectileInRadians());
        }
    }

    @Override
    public BufferedImage getImage(){
        return StaticFunctions.loadImage("/projectV1.png");
    }
}
