package up.TowerDefense.view.componentHandler;


import up.TowerDefense.model.character.Enemy;
import up.TowerDefense.model.character.Personnage;
import up.TowerDefense.model.game.Game;
import up.TowerDefense.model.map.Board;
import up.TowerDefense.model.map.Tile;
import up.TowerDefense.model.object.*;
import up.TowerDefense.view.mainComponent.ScreenPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static up.TowerDefense.view.mainComponent.ScreenPanel.*;
import static up.TowerDefense.view.componentHandler.TileDisplayManager.*;
import static up.TowerDefense.model.object.Obstacle.*;

/**
 * Classe qui édite la carte suivant un modèle
 * Elle englobe également les fonctions qui permettent de dessiner les composants du jeu sur l'interface graphique
 * */

public class MapGenerator {

//    public static MapGenerator REF;
    public Board gameBoard;
    private ScreenPanel screenPanel;
    private BufferedImage mapImage;
    private int tileSize;
    private int [][] mapTileNum;
    private Tile[]  tileType;

    private int nbCol;
    private int nbRow;

    public static ArrayList<PlaceableObstacle> obstaclesList;
    public static ArrayList<Personnage> charactersList;



    public MapGenerator(ScreenPanel screenPanel, String imagePath){
        obstaclesList = new ArrayList<PlaceableObstacle>();
        charactersList = new ArrayList<>();
        this.screenPanel = screenPanel;

        mapImage = this.loadImage(imagePath);
        this.nbCol = MAX_WORLD_COL;
        this.nbRow = MAX_WORLD_ROW;


        mapTileNum = new int [nbRow][nbCol];

        this.gameBoard = Game.getBoard();
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
                    Tile t = new Tile(new Position(row, col));
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
        /*long a = System.currentTimeMillis();
        try{
            InputStream is = getClass().getResourceAsStream("/world01.txt");
            BufferedReader br= new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < MAX_WORLD_COL && row < MAX_WORLD_ROW){
                String line = br.readLine();
                while (col < MAX_WORLD_COL){
                    String [] numbers = line.split("\t");
                    int num = Integer.parseInt(numbers[col]);

                    Tile t = new Tile(new Position(row, col));
                    setUpTile(t, num);

                    gameBoard.initTile(row, col, t,(num == 2));
                    System.out.println(System.currentTimeMillis() -a);
                    a = System.currentTimeMillis();

                    col++;
                }
                if(col == MAX_WORLD_COL){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public  BufferedImage loadImage(String image){
        BufferedImage img = null;
        try{
            img = ImageIO.read(this.getClass().getResourceAsStream(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * Complète le contenu des tuiles
     * (à compléter au fur et à mesure où on créer des tuiles)
    * */
    private void setUpTile(Tile t, int num){
        switch (num){
            case 0 :
            case 5 :
            case 4 : t.placeObstacle(VEIN); break;
            case 2 : t.placeObstacle(SKIN); break;
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
            )
            {
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
            drawElementaryComponent(g,ob.position,ob.getImage(),ob.getSize());
        }
        for (Personnage perso : new CopyOnWriteArrayList<>(charactersList)){
            drawElementaryComponent(g,perso.position, perso.getImage(),perso.getSize());
        }
    }

    private void drawElementaryComponent(Graphics2D g,Position pos, BufferedImage img,int size){

        int posX = (int) pos.x*tileSize;
        int posY = (int) pos.y*tileSize;

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
            g.drawImage(img,screenX, screenY, tileSize*size, tileSize*size, null);
    }


    /*
     * Pour les test: vérifier la disponibilité des cases en position
     * (x,y) , (x,y+1), (x+1,y) , (x+1,y)
     * ATTENTION : la position x et y est l'inverse de MouseX et MouseY
     */
    public void addObstacle(int posX, int posY) {

        /*Ce qui est à retravailler :
        * ajouter un paramètrage pour que l'obstacle à placer dépend
        * de ce que demande l'utilisateur
        * */
        PlaceableObstacle obstacle = null;
        switch(Game.getCurrentlyPlacing()) {
            case 0:
                obstacle = new TowerTest(posX, posY);
                break;
            case 1 :
                obstacle = new Tower(PresetTower.Anti_champis(), new Position(posX, posY));
                break;
            case 2 :
                obstacle = new Tower(PresetTower.Leucocyte_T(), new Position(posX, posY));
                break;
            case 3 :
                obstacle = new Tower(PresetTower.Anticorps(), new Position(posX, posY));
                break;
            case 4:
                obstacle = new Wall(posX, posY);
                break;
            default :
                return;
        }
        if(gameBoard.addObstacle(obstacle, posX, posY)){
            updateCharactersPaths();
            obstaclesList.add(obstacle);
        }

    }

    /**
     * Mets à jour le chemin suivi par l'enemi, cette fonction est appelée à chaque fois qu'une tour a été placée ou supprimée de la carte
     */
    private void updateCharactersPaths() {
        for (Personnage c : new CopyOnWriteArrayList<>(charactersList)){
            if(c instanceof Enemy){
                ((Enemy)c).update_paths();
            }
        }
    }

    /**
     * Actualise la position de l'enemi suivant le chemin qu'il est entrain de suivre
     */
    public synchronized void updateCharactersPositions() {
        for (Personnage c : new CopyOnWriteArrayList<>(charactersList)){
            if(c instanceof Enemy){
                ((Enemy)c).update_position();
            }
        }
    }

}

