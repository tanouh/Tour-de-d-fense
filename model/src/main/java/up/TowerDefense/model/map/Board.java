package up.TowerDefense.model.map;
import up.TowerDefense.model.object.Position;
import java.util.List;

public class Board {
    private Tile [][]  tiles;

    public Tile getTile(Position pos){ return tiles[(int)pos.x][(int)pos.y];}

    public int sizeX(){ return tiles.length; }
    public int sizeY(){ return tiles[0].length; }

    public boolean Empty(int x, int y) {return tiles[x][y].isEmpty();}

    public boolean Empty(Position pos) {return Empty((int)pos.x, (int)pos.y);}

    public static Board map;

    public Board(){
        map = this;
    }

    public void setTile(int row, int col){
        tiles = new Tile[row][col];
    }

    public Tile getTile(int row, int col){
        return tiles[row][col];
    }

    public void initTile(int row, int col, Tile tile ){
        tiles[row][col]= tile;
    }


    }
