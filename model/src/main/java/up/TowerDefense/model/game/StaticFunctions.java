package up.TowerDefense.model.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Cette classe regroupe les fonctions qui pourront être utiliser par les différents objets
 * dans le modèle
 * */

public class StaticFunctions {

    /**
     * @param image chemin de l'image
     * @return l'image chargés s'il n'y a pas d'erreur sinon
     * renvoie null
     */
    public static BufferedImage loadImage(String image){
        BufferedImage img = null;
        try{
            img = ImageIO.read(StaticFunctions.class.getResourceAsStream(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
