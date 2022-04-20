package up.TowerDefense.model.object;

import up.TowerDefense.model.character.Enemy;
import up.TowerDefense.view.componentHandler.MapGenerator;

public class TowerProjectile extends Projectile{
    private Enemy targetEnemy;
    private boolean freeze;

    public TowerProjectile(Position initPos, Position destPos, double pPower, int level, Enemy targetEnemy, boolean freeze) {
        super(initPos,destPos, pPower, level);
        this.targetEnemy = targetEnemy;
        this.freeze=freeze;
    }

    @Override
    public void move() {
        if (!targetEnemy.isAlive()){
            MapGenerator.projectilesList.remove(this);
            return;
        }
        if (Math.abs(xLoc - xDest)< speed/2 || Math.abs(yLoc - yDest)< speed/2){
            arrivedAtTarget = true;
            targetEnemy.takeDamage(power);
            if(freeze){
                targetEnemy.setFreezeDuration(4000 +(sourceLevel-1)*1000);
                targetEnemy.freeze();

            }
        }
        else{
            xLoc += speed*Math.cos(angleOfProjectileInRadians());

            yLoc += speed*Math.sin(angleOfProjectileInRadians());
        }
    }

    public enum projectileType{
        GENERIC,FREEZE,SNIPER
    }

}
