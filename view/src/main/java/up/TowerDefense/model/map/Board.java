package up.TowerDefense.model.map;
import up.TowerDefense.model.game.Game;
import up.TowerDefense.model.object.Obstacle;
import up.TowerDefense.model.object.PlaceableObstacle;
import up.TowerDefense.model.object.Position;



import java.util.ArrayList;

public class Board {
    private Tile[][] tiles;
    public static Board map;

    /* Stocke les positions des cases qu'occupent la cible principale*/
    private ArrayList<Position> targetZone = new ArrayList<>();

    public Board() {
        map = this;
    }

    public Tile getTile(Position pos) {
        return tiles[(int) pos.x][(int) pos.y];
    }

    public int sizeX() {
        return tiles.length;
    }

    public int sizeY() {
        return tiles[0].length;
    }

    public boolean isEmpty(int x, int y) {
        return tiles[x][y].isEmpty();
    }

    public boolean isEmpty(Position pos) {
        return isEmpty((int) pos.x, (int) pos.y);
    }

    public void setTile(int x, int y) {
        tiles = new Tile[x][y];
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || x > tiles.length || y < 0 || y > tiles[0].length) return null;

        return tiles[x][y];

    }

    public void initTile(int x, int y, Tile tile, boolean isATargetZone) {
        tiles[x][y] = tile;
        if (isATargetZone) {
            targetZone.add(new Position(x, y));
        }

    }

    public void setOccupier(Obstacle obstacle, int x, int y) {
        for (int i = 0; i < obstacle.getSize(); i++) {
            for (int j = 0; j < obstacle.getSize(); j++) {
                Tile t = getTile(x + i, y + j);
                t.setOccupier(obstacle);
            }
        }
    }

    /**
     * Ajoute les obstacles dans la grille de map
     *
     * @param obstacle
     * @param posX
     * @param posY
     */
    public boolean addObstacle(PlaceableObstacle obstacle, int posX, int posY) {
        /*
         fixme : voir s'il n'y a pas moyen d'éviter le switch entre posX et posY ici au cas
          où on rencontre d'autres problèmes liés à ça après
         */

        if (getTile(posY, posX).isEmpty
                && legalPlacement(obstacle, posY, posX)
                && obstacle.getBuyingCost() <= Game.getCredits()) {

            setOccupier(obstacle, posY, posX);
            Game.setCredits(-obstacle.getBuyingCost());
            return true;
        } else {
            Exception e = new Exception("Action denied");
            e.printStackTrace();
            return false;
        }


    }

    /**
     * Retourne vrai si les cases occupées par l'obstacle sont toutes libres
     */
    private boolean legalPlacement(PlaceableObstacle obstacle, int posX, int posY) {
        for (int i = 1; i <= obstacle.getSize(); i++)
            for (int j = 1; j <= obstacle.getSize(); j++)
                if (!getTile(posX + i, posY + j).isEmpty) return false;
        return true;
    }

    /**
     * Retourne la position dans la zone ciblée qui est la plus proche de
     *
     * @param startPos
     * @return
     */
    public Position getNearestTargetPosition(Position startPos) {
        double distMin = 100;
        Position res = null;
        for (Position pos : targetZone) {
            if (distMin > startPos.Distance(pos)) {
                distMin = startPos.Distance(pos);
                res = pos;
            }
        }
        return res;
    }

    /**
     * Renvoie les points de frai
     */
    public ArrayList<Position> getSpawnablePoint() {
        ArrayList<Position> spawnPoint = new ArrayList<>();

        int i =0;
        for (int j = 0; j < tiles[i].length; j++){
            if (tiles[i][j].isEmpty)
                spawnPoint.add(tiles[i][j].getPos());
        }

        // todo : à modifier quand les fonctions de déplacements auront été réglées

        /*for (int j = 0; j < tiles[0].length; j += tiles[0].length - 1)
            for (int i = 0; i < tiles.length; i++)
                if (tiles[i][j].isEmpty)
                    spawnPoint.add(tiles[i][j].getPos());
*/

        return spawnPoint;
    }/*fixme : les points obtenus ne sont pas tous sur les bords*/
}
