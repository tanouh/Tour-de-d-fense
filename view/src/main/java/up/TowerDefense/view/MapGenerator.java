package up.TowerDefense.view;

import up.TowerDefense.model.map.Board;
import up.TowerDefense.model.object.Obstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

public class MapGenerator {
    private Board gameBoard;
    private GamePanel gp;
    private BufferedImage mapImage;

    //private int [][] mapTileNum;

    public int nbCol;
    public int nbRow;





    public MapGenerator(GamePanel gp, String imagePath){
        this.gp = gp;
        loadImage(imagePath);
        this.nbCol = mapImage.getWidth();
        this.nbRow = mapImage.getHeight();

        this.gameBoard = new Board(this);


        loadMap();
    }

    private void loadImage(String path){
        try{
            mapImage= ImageIO.read(getClass().getResourceAsStream(path));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /***
     * Accéde aux pixels d'une image
     * @return les valeurs rouge , vert et bleu
     * d'un pixel et rajoutera le canal alpha s'il y en a
     */

    public static int[][] convertTo2D(BufferedImage image) {

        byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        int width = image.getWidth();
        int height = image.getHeight();
        boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[height][width];

        if (hasAlphaChannel) {
            int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel + 3 < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += -16777216; // 255 alpha
                argb += ((int) pixels[pixel] & 0xff); // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }
        return result;
    }


    public void loadMap(){
        try {
            int col = 0 ;
            int row = 0;
            int [][] rgb = convertTo2D(mapImage);

            while(col < gp.nbCol && row < gp.nbRow){

                while (col < gp.nbCol){
                    // Dans la génération de la map nous n'avons besoin
                    // de connaitre que si la couleur du pixel est blanche ou pas blanche
                    int num = (rgb[row][col] != -1 ? 1 : 0) ;

                    //mapTileNum[row][col]=num;

                    if(num != 0){
                        gameBoard.getTile(row,col).placeObstacle(new Obstacle(row,col,new int[]{1,1},"/tree.png"));
                    }else{
                        gameBoard.getTile(row,col).placeRoad();
                    }
                    col++;
                }
                if (col == gp.nbCol){
                    col = 0;
                    row++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g){
        int col = 0 ;
        int row = 0;

        while (col < gp.nbCol && row < gp.nbRow){
            //int tileNum = mapTileNum[col][row];
            g.drawImage(gameBoard.getTile(row,col).getImageTile(), col , row, gp.sizeCase, gp.sizeCase, null);
            col++;
            if(col == gp.nbCol){
                col = 0 ;
                row++;
            }
        }

    }

    public Board getBoard() {
        return this.gameBoard;
    }
}
