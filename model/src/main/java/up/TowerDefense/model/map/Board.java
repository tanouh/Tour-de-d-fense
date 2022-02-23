package up.TowerDefense.model.map;
import up.TowerDefense.model.object.Position;
import up.TowerDefense.model.object.TowerTest;

public class Board {
    private Tile [][]  tiles;

    public Board(){
        map = this;
    }

    public Tile getTile(Position pos){ return tiles[(int)pos.x][(int)pos.y];}

    public int sizeX(){ return tiles.length; }
    public int sizeY(){ return tiles[0].length; }

    public boolean isEmpty(int x, int y) {return tiles[x][y].isEmpty();}

    public boolean isEmpty(Position pos) {return isEmpty((int)pos.x, (int)pos.y);}

    public static Board map;

    public void setTile(int row, int col){
        tiles = new Tile[row][col];
    }

    public Tile getTile(int row, int col){
        return tiles[row][col];
    }

    public void initTile(int row, int col, Tile tile ){
        tiles[row][col]= tile;
    }


    /**
     * Place les tours sur les tuiles
     * @param posX
     * @param posY
     * @return
     */
    public boolean placeTower(int posX, int posY){
        if (getTile(posX,posY).isEmpty)
            getTile(posX,posY).setOccupier(new TowerTest(posX,posY));

        return true;
    }

}
