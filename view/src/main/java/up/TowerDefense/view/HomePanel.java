package up.TowerDefense.view;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel implements Runnable {

    protected JButton start, exit, settings;
    protected KeyHandler keyH = new KeyHandler();
    public Thread homeThread;

    public HomePanel(GameWindow gameWindow){

        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(159, 159, 159));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        /*insets définit la marge entre les composants*/
        gbc.insets = new Insets(5,5,5,5);
        /*ipady permet de savoir où on place le composant s'il n'occupe pas la totalité de l'espace disponible*/
        gbc.ipady = gbc.anchor = GridBagConstraints.CENTER;

        initButton(gameWindow,gbc);
        JLabel text = new JLabel("TOWER DEFENSE");
        text.setFont(Font.getFont(Font.MONOSPACED));
        gbc.gridx=2;
        gbc.gridy=0;
        this.add(text,gbc);

        this.addKeyListener(keyH);
        this.setFocusable(true);
    }


    public void initButton(GameWindow gameWindow,GridBagConstraints gbc){

        gbc.gridx = 2;
        gbc.gridy= 30;

        start = new JButton("New Game");
        this.add(start,gbc);
        start.addActionListener(event -> {
            GamePanel gamePanel = new GamePanel();
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(gamePanel);
            gameWindow.getContentPane().revalidate();
            gameWindow.getContentPane().repaint();
        });

        gbc.gridx = 2;
        gbc.gridy=50;
        settings = new JButton("Settings");
        this.add(settings,gbc);
        settings.addActionListener(event -> {
            SettingPanel setPane = new SettingPanel();
            gameWindow.getContentPane().removeAll();
            gameWindow.getContentPane().add(setPane);
            gameWindow.getContentPane().revalidate();
            gameWindow.getContentPane().repaint();
        });

        gbc.gridx = 2;
        gbc.gridy=70;
        exit = new JButton("Exit");
        this.add(exit,gbc);
        exit.addActionListener(event -> {
            gameWindow.dispose();
        });

    }


    @Override
    public void run() {

    }
}
