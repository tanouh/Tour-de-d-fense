package up.TowerDefense.view.mainComponent;

import up.TowerDefense.model.game.Game;
import up.TowerDefense.view.secondaryComponent.Button;

import javax.swing.*;
import java.awt.*;

public class EndPanel extends JPanel {
    JPanel body = new JPanel();

    public EndPanel(boolean victoire, GameWindow gameWindow){
        this.setLayout(new BorderLayout());
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        this.setBackground(GameWindow.background);
        body.setBackground(GameWindow.background);

        JLabel title = new JLabel("project Covid Defense");
        this.add(title, BorderLayout.NORTH);
        title.setPreferredSize(new Dimension(gameWindow.getWidth(), gameWindow.getHeight()/5));
        title.setFont(new Font(GameWindow.font,Font.BOLD, GameWindow.widthScreen/20));
        title.setForeground(GameWindow.foreground);
        title.setBackground(GameWindow.background);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);

        JLabel endMessage;
        if (victoire){
            if (Game.getLevel() == Game.getNbLevelTotal())
                endMessage = new JLabel("Vous avez battu tous les niveaux !");
            else
                endMessage = new JLabel("Vous avez battu le niveau " + Game.getLevel() + " !");
        }else{
            endMessage = new JLabel("Vous avez perdu... Voulez-vous retenter le niveau " + Game.getLevel() + " ?");
        }
        endMessage.setFont(new Font(GameWindow.font, Font.PLAIN, GameWindow.widthScreen/60));
        endMessage.setForeground(GameWindow.foreground);
        endMessage.setHorizontalTextPosition(JLabel.CENTER);

        body.add(endMessage);
        body.add(Box.createVerticalGlue());
        body.add(endMessage);
        body.add(Box.createVerticalGlue());

        if (victoire && Game.getLevel() == Game.getNbLevelTotal()){
            Button quitter = new Button();
            quitter.leaveButton(gameWindow);
            quitter.setPreferredSize(new Dimension(GameWindow.widthScreen / 2, GameWindow.heightScreen / 8));
            body.add(quitter);
        }else{
            Button tryAgain = new Button();
            tryAgain.tryAgainButton(victoire, gameWindow);
            tryAgain.setPreferredSize(new Dimension(GameWindow.widthScreen / 2, GameWindow.heightScreen / 8));
            body.add(tryAgain);
        }

        body.add(Box.createVerticalGlue());
        this.add(body, BorderLayout.CENTER);
    }
}
