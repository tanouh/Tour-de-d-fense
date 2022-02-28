package up.TowerDefense.view;


import up.TowerDefense.model.map.Board;
import up.TowerDefense.model.map.Tile;
import up.TowerDefense.model.object.Obstacle;
import up.TowerDefense.model.object.PlaceableObstacle;
import up.TowerDefense.model.object.Tower;
import up.TowerDefense.model.object.TowerTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static up.TowerDefense.view.TileDisplayManager.*;
import static up.TowerDefense.model.object.Obstacle.*;

public class MapGenerator {
    public Board gameBoard;
    private ScreenPanel screenPanel;
    private BufferedImage mapImage;
    private int widthTile;
    private int heightTile;


    private int [][] mapTileNum;

    public int nbCol;
    public int nbRow;

    public static ArrayList<PlaceableObstacle> obstaclesList = new ArrayList<PlaceableObstacle>();
    public static ArrayList<Character> charactersList = new ArrayList<Character>();


    public MapGenerator(ScreenPanel screenPanel, String imagePath){

        this.screenPanel = screenPanel;
        loadImage(imagePath);

        this.nbCol = mapImage.getWidth();
        this.nbRow = mapImage.getHeight();


        mapTileNum = new int [nbRow][nbCol];

        this.gameBoard = new Board();
        gameBoard.setTile(nbRow,nbCol);

        widthTile = screenPanel.widthCase;
        heightTile = screenPanel.heightCase;

        loadMap();
    }


    private void loadImage(String path){
        try{
            mapImage= ImageIO.read(getClass().getResourceAsStream(path));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Crée la carte à partir de l'image référencée par imagePath
     */
    public void loadMap(){
        try {
            int col = 0 ;
            int row = 0;
            setNumTile();
            while(col < screenPanel.nbCol && row < screenPanel.nbRow){
                while (col < screenPanel.nbCol){
                    Tile t = new Tile();
                    setUpTile(t,mapTileNum[row][col]);
                    gameBoard.initTile(row,col,t);
                    col++;
                }
                if (col == screenPanel.nbCol){

                    col = 0;
                    row++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
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
                    mapTileNum[i][j]=0;
                }else if (rgb[i][j] == JAUNE){
                    mapTileNum[i][j]=2;
                }else if (rgb[i][j] == BLEU){
                    mapTileNum[i][j]=3;
                }else if (rgb[i][j] == VERT){
                    mapTileNum[i][j]=4;
                }else if (rgb[i][j]== BLANC){
                    mapTileNum[i][j]=1;
                }
            }
        }
    }

    public void draw(Graphics2D g){
        int col = 0 ;
        int row = 0;
        while (col < screenPanel.nbCol && row < screenPanel.nbRow){
            g.drawImage(gameBoard.getTile(row,col).getImageTile(), col*widthTile, row*heightTile, widthTile, heightTile, null);
            col++;
            if(col == screenPanel.nbCol){
                col = 0 ;
                row++;
            }
        }
    }

    public void drawComponents(Graphics g){
        for (PlaceableObstacle ob : obstaclesList ){
            g.drawImage(ob.getImage(),(int) ob.position.x*heightTile, (int) ob.position.y*widthTile, widthTile*2, heightTile*2, null);
            //if(gameBoard.Empty((int) ob.position.x, (int) ob.position.y))
                //g.drawImage(ob.getImage(),(int) ob.position.x*widthTile, (int) ob.position.y*heightTile, null);
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
        PlaceableObstacle obstacle = new TowerTest(posX, posY);

        if(gameBoard.addObstacle(obstacle, posX, posY)){
            obstaclesList.add(obstacle);
        }
    }
}

