package up.TowerDefense.model.object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Obstacle {

    protected Position position;
    protected float size; // supposé carré ?
    protected BufferedImage image;

    public Obstacle(float posX , float posY, float size, String imgName){
        this.position  = new Position( posX, posY );
        this.size = size;
        loadImage(imgName);
    }
    public Obstacle(Position pos, float size, String imgName){
        this.position  = pos;
        this.size = size;
        loadImage(imgName);
    }

    private void loadImage(String imgName){
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imgName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
