package up.TowerDefense.view;

import up.TowerDefense.model.map.Board;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MapGenerator {
    private Board gameBoard;
    private GamePanel gp;
    private BufferedImage mapImage;

    private int [][] mapTileNum;

    public int nbCol;
    public int nbRow;






    public MapGenerator(GamePanel gp, String imagePath){
        loadImage(imagePath);
        this.nbCol = mapImage.getWidth();
        this.nbRow = mapImage.getHeight();
    }

    private void loadImage(String path){
        try{
            mapImage= ImageIO.read(getClass().getResourceAsStream(path));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void loadMap(){
        try{

        }catch(Exception e ){
            e.printStackTrace();
        }
    }


    public Board getBoard() {
        return this.gameBoard;
    }
}
