package up.TowerDefense.model.map;

import com.sun.security.jgss.GSSUtil;
import up.TowerDefense.model.object.DestructibleObstacle;
import up.TowerDefense.model.object.Obstacle;
import up.TowerDefense.model.object.PlaceableObstacle;
import up.TowerDefense.model.object.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLOutput;

public class Tile {
    protected boolean isEmpty = true;
    protected Obstacle obstacle;
    protected BufferedImage imageTile;
    protected Position pos;


    public Tile(Position _pos){
        pos = _pos;
        setDefault();
    }

    public void setDefault(){
        this.isEmpty = true;
        this.obstacle= null;
        this.imageTile=null;
    }

    public void setOccupier(Obstacle occupier) {
        this.obstacle = occupier;
        this.isEmpty=false;
    }

    public Obstacle getOccupier(){
        return this.obstacle;
    }

    public PlaceableObstacle getPlaceableObstacle(){
        if (obstacle instanceof PlaceableObstacle) return (PlaceableObstacle) obstacle;
        return null;
    }

    public void setImageTile(BufferedImage imageTile) {
        this.imageTile = imageTile;
    }

    public boolean isEmpty(){
        return this.isEmpty;
    }

    public void setTarget(boolean isTarget){ isEmpty = true; }

    public void placeObstacle(Obstacle obs){
        setOccupier(obs);
        isEmpty = false;
        setImageTile(obs.getImage());
    }

    public void placeRoad(){
        obstacle = null;
        isEmpty = true;

        try{
            imageTile= ImageIO.read(getClass().getResourceAsStream("/sol_V4.png"));
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public BufferedImage getImageTile() {
        return imageTile;
    }
    public Position getPos(){return pos;}


    public boolean hasATower() {
        return !isEmpty() && (obstacle instanceof DestructibleObstacle) && ((DestructibleObstacle) obstacle).isATower();
    }
}


