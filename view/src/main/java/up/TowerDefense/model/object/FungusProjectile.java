package up.TowerDefense.model.object;

import up.TowerDefense.model.game.Game;
import up.TowerDefense.model.game.StaticFunctions;
import up.TowerDefense.model.map.Tile;

import java.awt.image.BufferedImage;

public class FungusProjectile extends Projectile{
    private Tile target;
    private int attackRange = 4;

    public FungusProjectile(Position initPos, Position destPos, double pPower, int level, Tile target) {
        super(initPos, destPos, pPower, level);
        this.target = target;
    }


    @Override
    public void move() {
        //projectile has hit
        if (!target.isBooster()){
            arrivedAtTarget = true;
            return;
        }
        if (Math.abs(xLoc - xDest) < speed* Game.getGameSpeed()/2 || Math.abs(yLoc - yDest)< speed*Game.getGameSpeed()/2){
            arrivedAtTarget = true;
            target.destroySurrounding(attackRange);
        }
        else{
            xLoc += speed*Game.getGameSpeed()*Math.cos(angleOfProjectileInRadians());
            yLoc += speed*Game.getGameSpeed()*Math.sin(angleOfProjectileInRadians());
        }
    }

    @Override
    public BufferedImage getImage(){
        return StaticFunctions.loadImage("/fungusBall.png");
    }
}
