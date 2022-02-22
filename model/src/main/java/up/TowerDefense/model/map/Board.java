package up.TowerDefense.model.map;

public class Board {
    private Tile [][]  tiles;
    //private Charater [];

    public Board(){
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

