package up.TowerDefense.model.object;

public class Position {
    private float posX;
    private float posY;

    public Position(float x, float y){
        this.posX = x ;
        this.posY = y ;
    }

    public float getX(){
        return this.posX;
    }
    public float getY(){
        return this.posY;
    }

}
