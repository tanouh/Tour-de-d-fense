package up.TowerDefense.model.object;

public class Obstacle {

    protected Position position;
    protected int [] size; // supposé carré ?

    public Obstacle(double posX , double posY, int [] size){
        this.position  = new Position( posX, posY );
        this.size = size;
    }
    public Obstacle(Position pos, int[] size){
        this.position  = pos;
        this.size = size;
    }
}
