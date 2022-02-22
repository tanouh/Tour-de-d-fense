package up.TowerDefense.model.object;

import up.TowerDefense.model.map.Board;

public class Position {
    public double x;
    public double y;

    public Position(double _x, double _y){
        this.x = _x;
        this.y = _y;
    }

    public boolean Legal(){
        return  x >= 0 && x < Board.map.sizeX()  && y >= 0 && y < Board.map.sizeY();
    }

    public double Distance(Position pos2){
        return Math.sqrt((x - pos2.x) * (x - pos2.x) + (y - pos2.y) * (y - pos2.y));
    }
}
