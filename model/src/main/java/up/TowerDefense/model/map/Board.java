package up.TowerDefense.model.map;

import up.TowerDefense.view.MapGenerator;

public class Board {
    private Tile [][]  tiles;
    //private Charater [];

    public Board(MapGenerator mapG){
        this.tiles = new Tile [mapG.nbRow][mapG.nbCol];
    }

    public Tile getTile(int row, int col){
        return tiles[row][col];
    }

}
