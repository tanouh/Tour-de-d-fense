package up.TowerDefense.model.map;
import up.TowerDefense.model.object.Position;
import up.TowerDefense.model.object.Obstacle;

public class Tile {
    private Position pos;
    private Obstacle obstacle;

    public boolean isEmpty() {return obstacle == null;}
    public Position getPos(){return pos;}
    public Obstacle getObstacle(){ return obstacle; }
    public Tile(Position _pos, Obstacle _obstacle){
        pos = _pos;
        obstacle = _obstacle;
    }
}
