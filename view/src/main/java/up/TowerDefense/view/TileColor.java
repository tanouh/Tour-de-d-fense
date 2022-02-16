package up.TowerDefense.view;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static up.TowerDefense.view.MapGenerator.convertTo2D;

public class TileColor {

    public static final int BLEU =-14575885; // resultat de getCode("/bleu.png")
    public static final int NOIR = -16777216;
    public static final int VERT = -14983648;
    public static final int JAUNE = -5317;


    public static BufferedImage loadImage(String image){
        BufferedImage imagee = null;
        try{
            imagee= ImageIO.read(TileColor.class.getResourceAsStream(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagee;
    }

    public static  int getCode (String image){
        int [][] t = convertTo2D(loadImage(image));
        return t[0][0];
    }


}
