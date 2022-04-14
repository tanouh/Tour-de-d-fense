package up.TowerDefense.view.componentHandler;

import up.TowerDefense.model.game.Game;
import up.TowerDefense.view.mainComponent.ScreenPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static up.TowerDefense.view.mainComponent.ScreenPanel.*;

public class MouseHandler implements MouseListener {

    public int mouseX;
    public int mouseY;
    public boolean mousePressed;
    private ScreenPanel screenPanel;

    public MouseHandler(ScreenPanel screenPanel){
        this.screenPanel = screenPanel;
    }


    private void update(int x , int y){
        mouseX = (x + screenPanel.camera.worldX - screenPanel.camera.screenX)/tileSize;
        mouseY = (y + screenPanel.camera.worldY - screenPanel.camera.screenY)/tileSize;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (screenPanel.isPaused()) return;
        mousePressed = true;
        int x  = e.getX();
        int y = e.getY();
        update(x,y);
        /* Ceci est encore provisoire
        todo : dès qu'on clique sur une position, les possibilités de construction s'affichent sur le menu latéral
         */

        try {
//            screenPanel.mapGen.addObstacle(mouseX, mouseY);
            for (int i = 0; i < Game.getBoard().getTiles().length; i++){
                for (int j = 0; j < Game.getBoard().getTiles()[i].length; j++){
                    if (Game.getBoard().getTile(j,i).isEmpty()) System.out.print("0");
                    else System.out.print("1");
                }
                System.out.println();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed =false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mousePressed = false;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mousePressed = false;
    }

}
