package up.TowerDefense.model.object;


import java.awt.image.BufferedImage;

import static up.TowerDefense.model.game.StaticFunctions.*;


public class Obstacle {

    public Position position;
    private int size; // supposé carré ?
    public BufferedImage image;

    public Obstacle(double posX , double posY, int size, String imgName){
        this.position  = new Position( posX, posY );
        this.size = size;
        image = loadImage(imgName + ".png");
    }
    public Obstacle(Position pos, int size){
        this.position  = pos;
        this.size = size;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getSize() {
        return size;
    }

    /*
    * Des instances statiques pour le décor
    * */
    public static Obstacle FOREST = new Obstacle(0,0,1, "/tree");
    public static Obstacle WATER = new Obstacle(0,0,1, "/water01");
    public static Obstacle WALL = new Obstacle(0,0,1, "/wall");
    public static Obstacle SKIN = new Obstacle(0,0,1, "/sol_V2");
    public static Obstacle VEIN = new Obstacle(0,0,1, "/veine");

}
