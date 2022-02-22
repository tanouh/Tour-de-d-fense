package up.TowerDefense.model.map;

import up.TowerDefense.model.object.Obstacle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {
    protected boolean isEmpty;
    protected Obstacle occupier;
    protected BufferedImage imageTile;


    public Tile (){
        setDefault();
    }
    public void setDefault(){
        this.isEmpty = true;
        this.occupier= null;
        this.imageTile=null;
    }

    public void setOccupier(Obstacle occupier) {
        this.occupier = occupier;
    }

    public void setImageTile(BufferedImage imageTile) {
        this.imageTile = imageTile;
    }

    public boolean isOccupied(){
        return !this.isEmpty;
    }
    public void placeObstacle(Obstacle obs){
        setOccupier(obs);
        isEmpty = false;
        setImageTile(obs.getImage());
    }

    public void placeRoad(){
        occupier = null;
        isEmpty = true;

        try{
            imageTile= ImageIO.read(getClass().getResourceAsStream("/road00.png"));
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public BufferedImage getImageTile() {
        return imageTile;
    }
}
