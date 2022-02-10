package up.TowerDefense.view.tile;

import up.TowerDefense.view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    public static int TILE_TYPE = 6;
    GamePanel gp;
    Tile[] tile;
    int [][] mapTileNum;

    public TileManager(GamePanel gp){
        this.gp=gp;

        tile = new Tile[TILE_TYPE];
        mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap();
    }

    public void getTileImage(){
        try{
            tile[0]=new Tile();
            tile[0].image= ImageIO.read(getClass().getResourceAsStream("/earth.png"));

            tile[1]=new Tile();
            tile[1].image= ImageIO.read(getClass().getResourceAsStream("/wall.png"));


            tile[2]=new Tile();
            tile[2].image= ImageIO.read(getClass().getResourceAsStream("/tree.png"));

            tile[3]=new Tile();
            tile[3].image= ImageIO.read(getClass().getResourceAsStream("/water01.png"));

            tile[4]=new Tile();
            tile[4].image= ImageIO.read(getClass().getResourceAsStream("/grass01.png"));

            tile[5]=new Tile();
            tile[5].image= ImageIO.read(getClass().getResourceAsStream("/road00.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try {
            InputStream is = getClass().getResourceAsStream("/world01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0 ;
            int row = 0;
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                //Regarde la couleur
                //Boucle(parcourir tout les types de tuile)
                    //Si couleur tuile = couleur récupérée
                        //Créer nouvelle tuile à pos x y
                String line = br.readLine();
                while (col < gp.maxWorldCol){
                    String [] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]=num;
                    col++;
                }
                if (col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g){

        int worldCol = 0;
        int worldRow = 0;


        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.screenShift.worldX + gp.screenShift.screenX;
            int screenY = worldY - gp.screenShift.worldY + gp.screenShift.screenY;

            if(worldX + gp.tileSize > gp.screenShift.worldX - gp.screenShift.screenX &&
                    worldX - gp.tileSize < gp.screenShift.worldX + gp.screenShift.screenX &&
                    worldY + gp.tileSize > gp.screenShift.worldY - gp.screenShift.screenY &&
                    worldY - gp.tileSize < gp.screenShift.worldY + gp.screenShift.screenY
            ){
                g.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
            }
            worldCol ++;
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }

    }
}

