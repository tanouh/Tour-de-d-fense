package up.TowerDefense.view.componentHandler;


import up.TowerDefense.model.character.Enemy;
import up.TowerDefense.model.character.Personnage;
import up.TowerDefense.model.game.Game;
import up.TowerDefense.model.game.StaticFunctions;
import up.TowerDefense.model.map.Board;
import up.TowerDefense.model.map.Tile;
import up.TowerDefense.model.object.PlaceableObstacle;
import up.TowerDefense.model.object.TowerTest;
import up.TowerDefense.model.object.Wall;
import up.TowerDefense.view.mainComponent.ScreenPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static up.TowerDefense.view.mainComponent.ScreenPanel.*;
import static up.TowerDefense.view.componentHandler.TileDisplayManager.*;
import static up.TowerDefense.model.object.Obstacle.*;

/**
 * Classe qui édite la carte suivant un modèle
 * Elle englobe également les fonctions qui permettent de dessiner les composants du jeu sur l'interface graphique
 * */

public class MapGenerator {
    public Board gameBoard;
    private ScreenPanel screenPanel;
    private BufferedImage mapImage;
    private int tileSize;
    private int [][] mapTileNum;

    private int nbCol;
    private int nbRow;

    public static ArrayList<PlaceableObstacle> obstaclesList = new ArrayList<PlaceableObstacle>();
    public static ArrayList<Personnage> charactersList = new ArrayList<>();


    public MapGenerator(ScreenPanel screenPanel, String imagePath){

        this.screenPanel = screenPanel;

        mapImage = StaticFunctions.loadImage(imagePath);
        this.nbCol = mapImage.getWidth();
        this.nbRow = mapImage.getHeight();


        mapTileNum = new int [nbRow][nbCol];

        this.gameBoard = new Board();
        gameBoard.setTile(nbRow,nbCol);

        tileSize = screenPanel.tileSize;

        loadMap();
    }

    /**
     * Crée la carte à partir de l'image référencée par imagePath
     */
    public void loadMap(){
        try {
            int col = 0 ;
            int row = 0;
            setNumTile();
            while(col < MAX_WORLD_COL && row < MAX_WORLD_ROW){
                while (col < MAX_WORLD_COL){
                    Tile t = new Tile();
                    setUpTile(t,mapTileNum[row][col]);
                    gameBoard.initTile(row,col,t,(mapTileNum[row][col]==2)); //le dernier argument indique si la case est parmi celle
                                                                            // qui stocke la cible principale
                    col++;
                }
                if (col == MAX_WORLD_COL){

                    col = 0;
                    row++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        gameBoard.getTile(30,30).setOccupier(new TowerTest(30,30));
    }

    /**
     * Complète le contenu des tuiles
     * (à compléter au fur et à mesure où on créer des tuiles)
    * */
    private void setUpTile(Tile t, int num){
        switch (num){
            case 0 :
            case 4 : t.placeObstacle(FOREST); break;
            case 2 : t.placeObstacle(WALL); break;
            case 3 : t.placeObstacle(WATER); break;
            case 1 :
            default: t.placeRoad(); break;
        }
    }

    /**
     * Fait correspondre les pixels avec des numéros afin de
     * dessiner les tuiles adéquates
     */
    private void setNumTile(){
        int [][] rgb = convertTo2D(mapImage);
        for (int i = 0 ; i < rgb.length ; i++){
            for (int j = 0 ; j < rgb[0].length ; j++){
                if ( rgb[i][j] == NOIR){
                    mapTileNum[i][j]=0; //Obstacles indestructibles (décor)
                }else if (rgb[i][j] == JAUNE){
                    mapTileNum[i][j]=2; // Objectif final
                }else if (rgb[i][j] == BLEU){
                    mapTileNum[i][j]=3; // Décor aussi
                }else if (rgb[i][j] == VERT){
                    mapTileNum[i][j]=4; // Décor
                }else if (rgb[i][j]== BLANC){
                    mapTileNum[i][j]=1; //Routes véhiculables
                }
            }
        }
    }


    /**
     * Dessine la carte au fur et à mesure où on se déplace sur la carte
     * @param g
     */
    public void draw(Graphics2D g){
        int worldCol = 0;
        int worldRow = 0;


        while (worldCol < MAX_WORLD_COL && worldRow < MAX_WORLD_ROW){


            int worldX = worldCol * tileSize;
            int worldY = worldRow * tileSize;

            /*
            Pour déterminer la position sur l'écran @param screenX on doit veiller à ce qu'il y ait toujours
            une marge par rapport aux bords de la carte d'où les valeurs qui ont été soustraits
             */
            int screenX = worldX - screenPanel.camera.worldX + screenPanel.camera.screenX;
            int screenY = worldY - screenPanel.camera.worldY + screenPanel.camera.screenY;


            if(worldX + screenPanel.tileSize > screenPanel.camera.worldX - screenPanel.camera.screenX &&
                    worldX - screenPanel.tileSize < screenPanel.camera.worldX + screenPanel.camera.screenX &&
                    worldY + screenPanel.tileSize > screenPanel.camera.worldY - screenPanel.camera.screenY &&
                    worldY - screenPanel.tileSize < screenPanel.camera.worldY + screenPanel.camera.screenY
            ){
                Tile t = gameBoard.getTile(worldRow,worldCol);
                g.drawImage(t.getImageTile(),screenX,screenY,tileSize,tileSize,null);
            }
            worldCol ++;
            if(worldCol == MAX_WORLD_COL){
                worldCol = 0;
                worldRow++;
            }
        }

    }


    /**
     * Dessine les objets sur la carte au fur et à mesure où ils sont créés
     * @param g
     */
    public void drawComponents(Graphics2D g){
        for (PlaceableObstacle ob : obstaclesList ){
            int posX = (int) ob.position.x*tileSize;
            int posY = (int) ob.position.y*tileSize;

            int screenX = posX - screenPanel.camera.worldX + screenPanel.camera.screenX;
            int screenY = posY - screenPanel.camera.worldY + screenPanel.camera.screenY;

            /*
            padding  = screenX et screenY
            posX compris dans [worldX-padding, worldX+ padding]
            posY compris dans [worldY-padding, worldY+ padding]
             */
            if (
                    posX > screenPanel.camera.worldX - screenPanel.camera.screenX &&
                    posX < screenPanel.camera.worldX + screenPanel.camera.screenX &&
                    posY > screenPanel.camera.worldY - screenPanel.camera.screenY &&
                    posY < screenPanel.camera.worldY + screenPanel.camera.screenY
            )
                g.drawImage(ob.getImage(),screenX, screenY, tileSize*2, tileSize*2, null);
        }
    }
    /*
     * Pour les test: vérifier la disponibilité des cases en position
     * (x,y) , (x,y+1), (x+1,y) , (x+1,y)
     * ATTENTION : la position x et y est l'inverse de MouseX et MouseY
     */
    public void addObstacle(int posX, int posY) throws Exception {

        /*Ce qui est à retravailler :
        * ajouter un paramètrage pour que l'obstacle à placer dépend
        * de ce que demande l'utilisateur
        * */
        PlaceableObstacle obstacle = null;
        switch(Game.getCurrentlyPlacing()) {
            case 0:
                obstacle = new TowerTest(posX, posY);
                break;
//            case 1 :
//                obstacle = new TowerAntiMushroom(posX, posY);
//                break;
//            case 2 :
//                obstacle = new Leucocyte(posX, posY);
//                break;
//            case 3 :
//                obstacle = new Anticorps(posX, posY);
//                break;
            case 4:
                obstacle = new Wall(posX, posY);
                break;
            default :
                return;
        }
        if(gameBoard.addObstacle(obstacle, posX, posY)){
            obstaclesList.add(obstacle);
        }
    }


    public void updateCharacters() {
        for (Personnage c : charactersList){
            if(c instanceof Enemy){
                ((Enemy)c).update_position(this.gameBoard);
            }
        }

    }
}

