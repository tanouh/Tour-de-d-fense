package up.TowerDefense.model.object;


import up.TowerDefense.model.character.Enemy;
import up.TowerDefense.model.game.StaticFunctions;

import java.awt.image.BufferedImage;

public abstract class  Projectile  {
    //current coordinates
    protected double xLoc;
    protected double yLoc;



    protected double xDest;
    protected double yDest;
    //starting coordinates
    protected double xInit;
    protected double yInit;

    protected double power;
    protected double speed = 10;
    protected boolean arrivedAtTarget = false;
    protected int sourceLevel;

    protected BufferedImage img;

    public Projectile(Position initPos, Position destPos , double pPower, int level){


        xInit = initPos.x;
        xDest = destPos.x;
        yInit= initPos.y;
        yDest = destPos.y;
        power = pPower;

        xLoc = xInit +12*Math.cos(angleOfProjectileInRadians());
        yLoc = yInit +12*Math.sin(angleOfProjectileInRadians());


        arrivedAtTarget = false;

        sourceLevel = level;

        img= StaticFunctions.loadImage("/miniprojecttV1.png");
    }

    public double angleOfProjectileInDegrees(){
         return (180/Math.PI)*Math.atan2(yDest-yInit, xDest-xInit);
    }

    public double angleOfProjectileInRadians(){
        return Math.atan2(yDest-yInit, xDest-xInit);
    }

    public abstract void move();

    public boolean hasArrived(){
        return arrivedAtTarget;
    }

    public double getX(){
        return this.xLoc;
    }
    public double getY(){
        return this.yLoc;
    }

    public double getSpeed(){
        return this.speed;
    }
    public Position getPos(){
        return new Position(xLoc,yLoc);
    }

    public BufferedImage getImg() {
        return img;
    }
}
