package up.TowerDefense.model.object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Obstacle {

    protected Position position;
    protected int [] size; // supposé carré ?
    protected BufferedImage image;

    public Obstacle(double posX , double posY, int [] size, String imgName){
        this.position  = new Position( posX, posY );
        this.size = size;
    }
    public Obstacle(Position pos, int[] size){
        this.position  = pos;
        this.size = size;
    }

    private void loadImage(String imgName){
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imgName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
