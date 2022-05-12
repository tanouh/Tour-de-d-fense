package up.TowerDefense.model.map;

import up.TowerDefense.model.character.Enemy;
import up.TowerDefense.model.object.DestructibleObstacle;
import up.TowerDefense.model.object.Obstacle;
import up.TowerDefense.model.object.PlaceableObstacle;
import up.TowerDefense.model.object.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {
    protected boolean isEmpty = true;
    protected Obstacle obstacle;
    protected Enemy enemy;
    protected BufferedImage imageTile;
    protected BufferedImage imageTileAttacked;
    protected Position pos;
    protected boolean isTarget = false;
    protected boolean isAttacked = false;
    protected long startAttack;
    protected long durationAttack = 300;
    protected boolean booster = false;
    public long boostingStartTime;
    public long boostingDelay = 5000;



    public Tile(Position _pos){
        pos = _pos;
        setDefault();
    }

    public void setDefault(){
        this.isEmpty = true;
        this.obstacle= null;
        this.imageTile=null;
        try{
            imageTileAttacked = ImageIO.read(getClass().getResourceAsStream("/imageTileAttacked.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setOccupier(Obstacle occupier) {
        this.obstacle = occupier;
        this.isEmpty=false;
    }

    public void setEmpty (boolean empty){
        this.isEmpty = empty;
    }
    public void setEnemy(Enemy enemy){
        if (enemy != null) {
            this.enemy = enemy;
            this.isEmpty = false;
        }else{
            this.enemy = null;
            this.isEmpty = true;
        }
    }


    public Obstacle getOccupier(){
        return this.obstacle;
    }

    public boolean isTarget(){
        return isTarget;
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
        if (isAttacked){
            if (System.currentTimeMillis() - startAttack < durationAttack){
                return imageTileAttacked;
            }
            isAttacked = false;
        }
        return imageTile;
    }
    public Position getPos(){return pos;}

    public Enemy getEnemy(){ return enemy;}

    public boolean hasATower() {
        return !isEmpty() && (obstacle instanceof DestructibleObstacle) && ((DestructibleObstacle) obstacle).isATower();
    }

    public boolean hasAnEnnemy() {
        return (!isEmpty()) && (enemy instanceof Enemy);
    }

    public void isAttacked(){
        if (obstacle != null) return;
        isAttacked = true;
        startAttack = System.currentTimeMillis();
    }


    public void setBooster(boolean b) {
        this.booster = b ;
        boostingStartTime = System.currentTimeMillis();
    }

    public boolean isBooster() {
        return booster;
    }

    public BufferedImage getBoosterImage(){
        BufferedImage img = null;
        try{
            img = ImageIO.read(getClass().getResourceAsStream("/depot_champi.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}


