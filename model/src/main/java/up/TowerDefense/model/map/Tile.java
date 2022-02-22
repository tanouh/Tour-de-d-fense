package up.TowerDefense.model.map;

import up.TowerDefense.model.object.Obstacle;
import up.TowerDefense.model.object.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {
    protected boolean isEmpty;
    protected Obstacle obstacle;
    protected BufferedImage imageTile;
    protected Position pos;


    public Tile (){
        setDefault();
    }

    public Tile(Position _pos, Obstacle _obstacle){
        pos = _pos;
        obstacle = _obstacle;
    }

    public void setDefault(){
        this.isEmpty = true;
        this.obstacle= null;
        this.imageTile=null;
    }

    public void setOccupier(Obstacle occupier) {
        this.obstacle = occupier;
    }

    public void setImageTile(BufferedImage imageTile) {
        this.imageTile = imageTile;
    }

    public boolean isEmpty(){
        return this.isEmpty;
    }
    public void placeObstacle(Obstacle obs){
        setOccupier(obs);
        isEmpty = false;
        setImageTile(obs.getImage());
    }

    public void placeRoad(){
        obstacle = null;
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
    public Position getPos(){return pos;}
}


