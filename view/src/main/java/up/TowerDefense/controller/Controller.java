package up.TowerDefense.controller;

import up.TowerDefense.view.mainComponent.GamePanel;
import up.TowerDefense.view.mainComponent.ScreenPanel;

public class Controller {
    ScreenPanel screenPanel;
    GamePanel gamePanel;

    int posX;
    int posY;

    public Controller(GamePanel gamePanel, ScreenPanel screenPanel){
       this.gamePanel = gamePanel;
       this.screenPanel= screenPanel;
        posX = screenPanel.mouseHandler.mouseY;
        posY = screenPanel.mouseHandler.mouseX;
    }

    /*public boolean placeTower() {
        return screenPanel.mapGen.gameBoard.placeTower(posX,posY);
    }*/
}
