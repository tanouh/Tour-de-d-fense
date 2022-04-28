package up.TowerDefense.model.object;


import up.TowerDefense.model.game.Game;
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
            arrivedAtTarget = true;
            return;
        }
        if (Math.abs(xLoc - xDest) < speed* Game.getGameSpeed()/2 || Math.abs(yLoc - yDest)< speed*Game.getGameSpeed()/2){
            arrivedAtTarget = true;
            target.takeDamage(power);
        }
        else{
            xLoc += speed*Game.getGameSpeed()*Math.cos(angleOfProjectileInRadians());
            yLoc += speed*Game.getGameSpeed()*Math.sin(angleOfProjectileInRadians());
        }
    }

    @Override
    public BufferedImage getImage(){
        return StaticFunctions.loadImage("/projectV1.png");
    }
}
