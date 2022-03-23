package up.TowerDefense.model.map;
import up.TowerDefense.model.game.Game;
import up.TowerDefense.model.game.StaticFunctions;
import up.TowerDefense.model.object.Obstacle;
import up.TowerDefense.model.object.PlaceableObstacle;
import up.TowerDefense.model.object.Position;

import static  up.TowerDefense.model.game.StaticFunctions.*;


import java.util.ArrayList;

public class Board {
    private Tile [][]  tiles;
    public static Board map;
    private int count = 0;

    /* Stocke les positions des cases qu'occupent la cible principale*/
    private ArrayList<Position> targetZone = new ArrayList<>();

    public Board(){
        map = this;
    }

    public Tile getTile(Position pos){ return tiles[(int)pos.x][(int)pos.y];}

    public int sizeX(){ return tiles.length; }
    public int sizeY(){ return tiles[0].length; }

    public boolean isEmpty(int x, int y) {return tiles[x][y].isEmpty();}

    public boolean isEmpty(Position pos) {return isEmpty((int)pos.x, (int)pos.y);}

    public void setTile(int x, int y){
        tiles = new Tile[x][y];
    }

    public Tile getTile(int x, int y){
        if(x < 0 || x > tiles.length || y < 0 || y > tiles[0].length) return null;

        return tiles[x][y];

    }

    public void initTile(int x, int y, Tile tile,boolean isATargetZone ){
        tiles[x][y]= tile;
        if (isATargetZone){
            targetZone.add(new Position(x,y));
            count++;
        }

    }

    public void setOccupier(Obstacle obstacle, int x, int y){
        Tile t = getTile(x, y);
        t.setOccupier(obstacle);
    }

    /**
     * Ajoute les obstacles dans la grille de map
     * @param obstacle
     * @param posX
     * @param posY
     */
    public boolean addObstacle (PlaceableObstacle obstacle, int posX, int posY) throws Exception {
        /*
         fixme : voir s'il n'y a pas moyen d'éviter le switch entre posX et posY ici au cas
          où on rencontre d'autres problèmes liés à ça après
         */

        if (getTile(posY, posX).isEmpty && obstacle.getBuyingCost() <= Game.getCredits()){
            setOccupier(obstacle, posY, posX);
            Game.setCredits(-obstacle.getBuyingCost());
            return true;
        }else{
//            throw new Exception("Action denied");
            return false;
        }
    }

    /**
     * Retourne la position dans la zone ciblée qui est la plus proche de
     * @param startPos
     * @return
     */
    public Position  getNearestTargetPosition(Position startPos) {
        double distMin = 100;
        Position res = null;
        for (Position pos : targetZone){
        //todo : fonction de comparaison et calcul de distance
            if (distMin > getDistance(pos,startPos)){
                distMin =  getDistance(pos,startPos);
                res = pos;
            }
        }
        return res;
    }

}
