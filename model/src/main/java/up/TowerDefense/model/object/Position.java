package up.TowerDefense.model.object;

public class Position {
    private double posX;
    private double posY;

    public Position(double x, double y){
        this.posX = x ;
        this.posY = y ;
    }

    public double getX(){
        return this.posX;
    }
    public double getY(){
        return this.posY;
    }

}
