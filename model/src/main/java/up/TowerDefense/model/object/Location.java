package up.TowerDefense.model.object;

public class Location {
    private int posX;
    private int posY;

    public Location(int x, int y){
        this.posX = x ;
        this.posY = y ;
    }

    public int getX(){
        return this.posX;
    }
    public int getY(){
        return this.posY;
    }

}
