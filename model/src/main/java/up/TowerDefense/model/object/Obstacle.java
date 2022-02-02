package up.TowerDefense.model.object;

public class Obstacle {

    protected Location position;
    protected int [] size; // supposé carré ?

    public Obstacle(double posX , double posY, int [] size){
        this.position  = new Location( posX, posY );
        this.size = size;
    }
    public Obstacle(Location pos, int[] size){
        this.position  = pos;
        this.size = size;
    }
}
