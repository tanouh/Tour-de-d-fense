package up.TowerDefense.controller;

import up.TowerDefense.view.GamePanel;
import up.TowerDefense.view.ScreenPanel;

public class Controller {
    ScreenPanel screenPanel;
    GamePanel gamePanel;

    int posX;
    int posY;

    public Controller(GamePanel gamePanel, ScreenPanel screenPanel){
       this.gamePanel = gamePanel;
       this.screenPanel= screenPanel;
        posX = screenPanel.mouseX;
        posY = screenPanel.mouseY;
    }

    /*public boolean placeTower() {
        return screenPanel.mapGen.gameBoard.placeTower(posX,posY);
    }*/
}
