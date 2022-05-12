package up.TowerDefense.model.object;

import up.TowerDefense.model.character.Enemy;
import up.TowerDefense.model.game.Game;
import up.TowerDefense.model.game.StaticFunctions;
import up.TowerDefense.model.map.Path;

import java.awt.image.BufferedImage;

public class TowerProjectile extends Projectile{
    private Enemy targetEnemy;
    private boolean freeze;
    private Position initPos;
    private Position destPos;

    public TowerProjectile(Position initPos, Position destPos, double pPower, int level, Enemy targetEnemy, boolean freeze) {
        super(initPos,destPos, pPower, level);
        this.targetEnemy = targetEnemy;
        this.freeze=freeze;
        this.initPos = initPos;
        this.destPos = destPos;
        //setTargetPos(getFutureTargetPosition());
    }

    @Override
    public void move() {
        if (!targetEnemy.isAlive()){
            this.arrivedAtTarget = true;
            return;
        }
        if (Math.abs(xLoc - xDest)< speed*Game.getGameSpeed()/2 ||
                Math.abs(yLoc - yDest)< speed*Game.getGameSpeed()/2){
            arrivedAtTarget = true;
            targetEnemy.takeDamage(power);
            if(freeze){
                targetEnemy.setFreezeDuration(4000 + (sourceLevel-1)*1000);
                targetEnemy.freeze();
            }
        }
        else{
            xLoc += speed*Game.getGameSpeed()*Math.cos(angleOfProjectileInRadians());
            yLoc += speed*Game.getGameSpeed()*Math.sin(angleOfProjectileInRadians());
        }
    }

    @Override
    public BufferedImage getImage(){
        if (freeze) return StaticFunctions.loadImage("/freezingBall.png");
        return StaticFunctions.loadImage("/towerBall.png");
    }


    public Position getFutureTargetPosition(){
        double distance = Path.getCartesianDistance(initPos, destPos);
        return targetEnemy.getPath().GetPos(targetEnemy.getTravelTime() + (distance/(speed*Game.getGameSpeed())),
                targetEnemy.getSpeed());
    }
}
