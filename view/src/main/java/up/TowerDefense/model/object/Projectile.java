package up.TowerDefense.model.object;


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
    protected double size = 0.8;
    protected double speed = 0.2;
    protected boolean arrivedAtTarget;
    protected int sourceLevel;


    public Projectile(Position initPos, Position destPos , double pPower, int level){


        xInit = initPos.x;
        xDest = destPos.x;
        yInit= initPos.y;
        yDest = destPos.y;
        power = pPower;

        xLoc = xInit;
        yLoc = yInit;


        arrivedAtTarget = false;
        sourceLevel = level;

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

    public BufferedImage getImage(){
        return StaticFunctions.loadImage("/projectV1.png");
    }

    public double getSize(){
        return size;
    }

    public String toString(){
        return "projectile " + " : " + this.getPos().x + " - " + this.getPos().y;
    }
}
