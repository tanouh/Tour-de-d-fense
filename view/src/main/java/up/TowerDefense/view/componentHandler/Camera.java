package up.TowerDefense.view.componentHandler;


import up.TowerDefense.view.mainComponent.ScreenPanel;

import java.awt.*;
import static up.TowerDefense.view.mainComponent.ScreenPanel.*;

public class Camera {
    private ScreenPanel screenPanel;

    /**
     * @param worldX
     * @param worldY modélisent la position de la caméra sur la carte (aka le monde)
     * @param screenX
     * @param screenY modélisent la position de la caméra sur l'écran
     *
     */
    public int worldX, worldY;
    public int speed;
    public int screenX;
    public int screenY;

    public static boolean upPressed, downPressed, leftPressed, rightPressed;


    public Camera(ScreenPanel screenPanel){
        this.screenPanel= screenPanel;
        setDefaultValues();
    }

    private void setDefaultValues(){
        // On décide de diviser par deux pour que la caméra soit toujours centrée
        worldX = (MAX_WORLD_COL/2)* screenPanel.tileSize;
        worldY = (MAX_WORLD_ROW/2)* screenPanel.tileSize;
        screenX = windowWidth/2;
        screenY= windowHeight/2;
        speed = tileSize;
    }


    /**
     * Fonction qui modélise le déplacement de la caméra
     */
    public void update(){
        if(upPressed){
            if (worldY-(speed+windowHeight/2) >=0)
                worldY-=speed;
        }
        else if(downPressed) {
            if (worldY+ (speed+windowHeight/2) <= MAX_WORLD_ROW*tileSize)
                worldY += speed;
        }else if(leftPressed){
            if (worldX-(speed+windowWidth/2) >=0)
                worldX-=speed;
        }
        else if(rightPressed){
            if (worldX+(speed+windowWidth /2) <= MAX_WORLD_COL* tileSize)
                worldX+=speed;
        }
    }

    /*
    Dessin(provisoire) pas vraiment important, mais qui aidera juste à visualiser
    le déplacement de la caméra
     */
    public void draw (Graphics2D g){
        g.setColor(screenPanel.background);
        g.fillRect(screenX,screenY, screenPanel.tileSize, screenPanel.tileSize);
    }


}

