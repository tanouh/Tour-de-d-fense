package up.TowerDefense.model.map;
import up.TowerDefense.model.object.Obstacle;
import up.TowerDefense.model.object.PlaceableObstacle;
import up.TowerDefense.model.object.Position;
import up.TowerDefense.model.object.TowerTest;

public class Board {
    private Tile [][]  tiles;
    public static Board map;

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
        return tiles[x][y];
    }

    public void initTile(int x, int y, Tile tile ){
        tiles[x][y]= tile;
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

        System.out.println(posX+" "+ posY);
        if (getTile(posY, posX).isEmpty ){
            setOccupier(obstacle, posY, posX);
            return true;
        }else{
            throw new Exception("Action denied");
        }
    }
}
