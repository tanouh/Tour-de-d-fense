package up.TowerDefense.model.object;

import up.TowerDefense.model.game.Game;
import up.TowerDefense.model.map.Board;

public class Position {
    public double x;
    public double y;

    public Position(double _x, double _y){
        this.x = _x;
        this.y = _y;
    }
    public double Norm(){
        return Math.sqrt(x*x+y*y);
    }
    public void Normalize(){
        double norm = Norm();
        x/= norm;
        y/= norm;
    }
    public boolean Legal(){
        return  (x >= 0 && x < Board.map.sizeX()  && y >= 0 && y < Board.map.sizeY()) && Game.getBoard().getTile(this).isEmpty();
    }

    public double Distance(Position pos2){
        return Math.sqrt((x - pos2.x) * (x - pos2.x) + (y - pos2.y) * (y - pos2.y));
    }
    public static Position Lerp(Position a, Position b, double t){
        double x = a.x * (1.0 - t) + b.x * t;
        double y = a.y * (1.0 - t) + b.y * t;
        return new Position(x,y);
    }
    public static double Dot(Position a, Position b){
        return a.x * b.x + a.y * b.y;
    }
    public double AngleRad(Position a, Position b){
        Position toA = new Position(a.x - x, a.y - y);
        Position toB = new Position(b.x - x, b.y - y);
        toA.Normalize();
        toB.Normalize();
        return Math.acos(Dot(toA,toB));
    }
}
