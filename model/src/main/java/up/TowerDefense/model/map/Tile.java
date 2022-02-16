package up.TowerDefense.model.map;

import up.TowerDefense.model.object.Obstacle;

import java.awt.image.BufferedImage;

public class Tile {
    protected boolean isEmpty;
    protected Obstacle occupier;
    protected BufferedImage imageTile;

    public enum TileType{
        OBSTACLE_TILE, ROAD_TILE
    }


}
