package up.TowerDefense.view.componentHandler;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;


/**
 * Classe responsable de la modélisation des premières tuiles pour la carte
 */

public class TileDisplayManager {

    public static final int BLEU =-14575885; // resultat de getCode("/bleu.png")
    public static final int NOIR = -16777216;
    public static final int VERT = -14983648;
    public static final int JAUNE = -5317;
    public static final int BLANC = -1;


    public static BufferedImage loadImage(String image){
        BufferedImage img = null;
        try{
            img = ImageIO.read(TileDisplayManager.class.getResourceAsStream(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }


    /***
     * Accéde aux pixels d'une image
     * @return les valeurs rouge , vert et bleu
     * d'un pixel et rajoutera le canal alpha s'il y en a
     *
     * Ressources trouvées sur un forum sur internet
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

    public static  int getCode (String image){
        int [][] t = convertTo2D(loadImage(image));
        return t[0][0];
    }

}
