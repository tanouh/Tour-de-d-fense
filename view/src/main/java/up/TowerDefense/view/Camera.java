package up.TowerDefense.view;

import static up.TowerDefense.view.Camera.Direction.*;


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
    public Direction dir;

    public static boolean upPressed, downPressed, leftPressed, rightPressed;


    public enum Direction{
        UP,DOWN,LEFT,RIGHT;
    }


    public Camera(ScreenPanel screenPanel){
        this.screenPanel= screenPanel;
        setDefaultValues();
    }

    private void setDefaultValues(){
        // On décide de diviser par deux pour que la caméra soit toujours centrée
        screenX = screenPanel.windowWidth/2;
        screenY= screenPanel.windowHeight/2;
        speed = 5;
        dir = Direction.UP;
    }


    /**
     * Fonction qui modélise le déplacement de la caméra
     */
    public void update(){
        if(upPressed){
            worldY-=speed;
            dir = UP;
        }
        else if(downPressed) {
            worldY += speed;
            dir = DOWN;

        }else if(leftPressed){
            worldX-=speed;
            dir = LEFT;
        }
        else if(rightPressed){
            worldX+=speed;
            dir = RIGHT;
        }
    }
}

