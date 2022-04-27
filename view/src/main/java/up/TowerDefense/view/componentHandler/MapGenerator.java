package up.TowerDefense.view.componentHandler;


import up.TowerDefense.model.character.Enemy;
import up.TowerDefense.model.character.Personnage;
import up.TowerDefense.model.game.Game;
import up.TowerDefense.model.map.Board;
import up.TowerDefense.model.map.Tile;
import up.TowerDefense.model.object.*;
import up.TowerDefense.view.mainComponent.ScreenPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.concurrent.CopyOnWriteArrayList;

import static up.TowerDefense.model.object.Obstacle.*;
import static up.TowerDefense.view.componentHandler.TileDisplayManager.*;
import static up.TowerDefense.view.mainComponent.ScreenPanel.MAX_WORLD_COL;
import static up.TowerDefense.view.mainComponent.ScreenPanel.MAX_WORLD_ROW;

/**
 * Classe qui édite la carte suivant un modèle
 * Elle englobe également les fonctions qui permettent de dessiner les composants du jeu sur l'interface graphique
 * */

public class MapGenerator {

    public Board gameBoard;
    private final ScreenPanel screenPanel;
    private final BufferedImage mapImage;
    private final int tileSize;
    private final int [][] mapTileNum;

    private final int nbCol;
    private final int nbRow;

    public static ArrayList<PlaceableObstacle> obstaclesList;
    public static ArrayList<Personnage> charactersList;
    public static ArrayList<Personnage> toRemoveCharacters;
    public static ArrayList<Projectile> towerProjectilesList;
    public static ArrayList<Projectile> enemyProjectilesList;
    public static ArrayList<Projectile> toRemoveEnemyProjectiles;
    public static ArrayList<Projectile> toRemoveTowerProjectiles;



    public MapGenerator(ScreenPanel screenPanel, String imagePath){
        obstaclesList = new ArrayList<>();
        charactersList = new ArrayList<>();
        toRemoveCharacters = new ArrayList<>();
        towerProjectilesList = new ArrayList<>();
        enemyProjectilesList = new ArrayList<>();
        toRemoveEnemyProjectiles = new ArrayList<>();
        toRemoveTowerProjectiles = new ArrayList<>();

        this.screenPanel = screenPanel;

        mapImage = this.loadImage(imagePath);
        this.nbCol = MAX_WORLD_COL;
        this.nbRow = MAX_WORLD_ROW;


        mapTileNum = new int [nbRow][nbCol];

        this.gameBoard = Game.getBoard();
        gameBoard.setTile(nbRow,nbCol);

        tileSize = ScreenPanel.tileSize;

        loadMap();

    }

    /**
     * Crée la carte à partir de l'image référencée par imagePath
     */
    public void loadMap(){
        try {
            int col = 0 ;
            int row = 0;
            setNumTile(); // initialise le tableau qui contient les différents types de tuiles
            while(col < MAX_WORLD_COL && row < MAX_WORLD_ROW){
                while (col < MAX_WORLD_COL){
                    // on crée la tuile et l'introduit dans le tableau de tuiles dans le board associé
                    Tile t = new Tile(new Position(col, row));
                    setUpTile(t,mapTileNum[row][col]);
                    gameBoard.initTile(row,col,t,(mapTileNum[row][col]==2)); //le dernier argument indique si la case est parmi celle
                                                                            // qui stocke la cible principale
                    col++;
                }
                if (col == MAX_WORLD_COL){
                    col = 0;
                    row++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        // on initialise les positions depuis lesquelles les ennemis peuvent sortir
        Game.getBoard().setSpawnablePoint();
    }

    public  BufferedImage loadImage(String image){
        BufferedImage img = null;
        try{
            img = ImageIO.read(this.getClass().getResourceAsStream(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * Complète le contenu des tuiles
     * (à compléter au fur et à mesure où on créer des tuiles)
    * */
    private void setUpTile(Tile t, int num){
        switch (num){
            case 0 :
            case 5 :
            case 4 :
                t.placeObstacle(VEIN);
//                System.out.println(t.getPos().x + " " + t.getPos().y);
                break;
            case 2 :
                t.placeObstacle(SKIN);
                t.setEmpty(true);
                break;
            case 3 : t.placeObstacle(WATER); break;
            case 1 :

            default: t.placeRoad(); break;
        }
    }

    /**
     * Fait correspondre les pixels avec des numéros afin de
     * dessiner les tuiles adéquates
     */
    private void setNumTile(){
        int [][] rgb = convertTo2D(mapImage);
        for (int i = 0 ; i < rgb.length ; i++){
            for (int j = 0 ; j < rgb[0].length ; j++){
                if ( rgb[i][j] == NOIR){
                    mapTileNum[i][j]=0; //Obstacles indestructibles (décor)
                }else if (rgb[i][j] == JAUNE){
                    mapTileNum[i][j]=2; // Objectif final
                }else if (rgb[i][j] == BLEU){
                    mapTileNum[i][j]=3; // Décor aussi
                }else if (rgb[i][j] == VERT){
                    mapTileNum[i][j]=4; // Décor
                }else if (rgb[i][j]== BLANC){
                    mapTileNum[i][j]=1; //Routes véhiculables
                }
            }
        }
    }


    /**
     * Dessine la carte au fur et à mesure où on se déplace sur la carte
     * @param g
     */
    public void draw(Graphics2D g){
        int worldCol = 0;
        int worldRow = 0;


        while (worldCol < MAX_WORLD_COL && worldRow < MAX_WORLD_ROW){


            int worldX = worldCol * tileSize;
            int worldY = worldRow * tileSize;

            /*
            Pour déterminer la position sur l'écran @param screenX on doit veiller à ce qu'il y ait toujours
            une marge par rapport aux bords de la carte d'où les valeurs qui ont été soustraits
             */
            int screenX = worldX - screenPanel.camera.worldX + screenPanel.camera.screenX;
            int screenY = worldY - screenPanel.camera.worldY + screenPanel.camera.screenY;


            if(worldX + ScreenPanel.tileSize > screenPanel.camera.worldX - screenPanel.camera.screenX &&
                    worldX - ScreenPanel.tileSize < screenPanel.camera.worldX + screenPanel.camera.screenX &&
                    worldY + ScreenPanel.tileSize > screenPanel.camera.worldY - screenPanel.camera.screenY &&
                    worldY - ScreenPanel.tileSize < screenPanel.camera.worldY + screenPanel.camera.screenY
            )
            {
                Tile t = gameBoard.getTile(worldCol,worldRow);
                BufferedImage image = null;

                g.drawImage(gameBoard.getTile(worldCol, worldRow).getImageTile(),screenX,screenY,tileSize,tileSize,null);
            }
            worldCol ++;
            if(worldCol == MAX_WORLD_COL){
                worldCol = 0;
                worldRow++;
            }
        }

    }


    /**
     * Dessine les objets sur la carte au fur et à mesure où ils sont créés
     * @param g
     */
    public void drawComponents(Graphics2D g){
        for (PlaceableObstacle ob : new CopyOnWriteArrayList<>(obstaclesList) ){
            if (ob.tookHit()){
                drawElementaryComponent(g,ob.position, ob.getReloadImage(),ob.getSize());
            }else{
                drawElementaryComponent(g,ob.position, ob.getImage(),ob.getSize());
            }
        }
        for (Personnage perso : new CopyOnWriteArrayList<>(charactersList)){
            if (perso.tookHit()) {
                drawElementaryComponent(g, perso.position, perso.getReloadImage(), perso.getSize());
            }else{
                drawElementaryComponent(g, perso.position, perso.getImage(), perso.getSize());
            }
        }
        for (Projectile towerProj : new CopyOnWriteArrayList<>(towerProjectilesList)){
            drawElementaryComponent(g, towerProj.getPos(), towerProj.getImage(), towerProj.getSize());
        }
        for (Projectile enemyProj : new CopyOnWriteArrayList<>(enemyProjectilesList)){
            drawElementaryComponent(g, enemyProj.getPos(), enemyProj.getImage(), enemyProj.getSize());
        }
    }

    private void drawElementaryComponent(Graphics2D g,Position pos, BufferedImage img,double size){

        int posX = (int)Math.round(pos.x*tileSize);
        int posY = (int)Math.round(pos.y*tileSize);

        int screenX = posX - screenPanel.camera.worldX + screenPanel.camera.screenX;
        int screenY = posY - screenPanel.camera.worldY + screenPanel.camera.screenY;

            /*
            padding  = screenX et screenY
            posX compris dans [worldX-padding, worldX+ padding]
            posY compris dans [worldY-padding, worldY+ padding]
             */
        if (
                posX > screenPanel.camera.worldX - screenPanel.camera.screenX &&
                        posX < screenPanel.camera.worldX + screenPanel.camera.screenX &&
                        posY > screenPanel.camera.worldY - screenPanel.camera.screenY &&
                        posY < screenPanel.camera.worldY + screenPanel.camera.screenY
        )
            g.drawImage(img,screenX, screenY, (int)(tileSize*size), (int)(tileSize*size), null);
    }


    /*
     * Pour les test: vérifier la disponibilité des cases en position
     * (x,y) , (x,y+1), (x+1,y) , (x+1,y)
     * ATTENTION : la position x et y est l'inverse de MouseX et MouseY
     */
    public void action(int posX, int posY) {
        PlaceableObstacle obstacle = null;
        switch(Game.getCurrentlyPlacing()) {
            case 0:
                gameBoard.directAttack(posX, posY);
                break;
            case 1:
                gameBoard.upgradeTower(posX, posY);
                break;
            case 2 :
                obstacle = new Tower(PresetTower.Anti_champis(), new Position(posX, posY));
                break;
            case 3 :
                obstacle = new Tower(PresetTower.Leucocyte_T(), new Position(posX, posY));
                break;
            case 4 :
                obstacle = new Tower(PresetTower.Anticorps(), new Position(posX, posY));
                break;
            case 5:
                obstacle = new Wall(posX, posY);
                break;
            default :
                return;
        }if (obstacle != null) {
            if (gameBoard.addObstacle(obstacle, posX, posY)) {
                obstaclesList.add(obstacle);
                updateCharactersPaths();
            }
        }
    }

    /**
     * Mets à jour le chemin suivi par l'enemi, cette fonction est appelée à chaque fois qu'une tour a été placée ou supprimée de la carte
     */
    private void updateCharactersPaths() {
        try{
            for (Personnage c : charactersList){
                if(c instanceof Enemy){
                    if (((Enemy) c).isAlive()) ((Enemy)c).update_paths();
                    else {
                        toRemoveCharacters.add(c);
                        ((Enemy) c).die();
                    }
                }
            }
            for (Enemy en : gameBoard.getListEnemy()){
                en.setGotNewPath(true);
            }
            charactersList.removeAll(toRemoveCharacters);
        }catch(ConcurrentModificationException exc){
            System.out.println("Attempt to modify characterList while iterating on it.");

        }

    }

    /**
     * Actualise la position de l'enemi suivant le chemin qu'il est entrain de suivre
     */
    public void updateCharactersPositions() {
        try{
            for (Personnage c : charactersList){
                if(c instanceof Enemy){
                    if (((Enemy) c).isAlive()) ((Enemy)c).update_position();
                    else{
                        toRemoveCharacters.add(c);
                        ((Enemy) c).die();
                    }
                }
            }
            charactersList.removeAll(toRemoveCharacters);
        }catch(ConcurrentModificationException exc){
            System.out.println("Attempt to modify characterList while iterating on it.");

        }
    }

    public void updateProjectilesPos(){
        try{
            for (Projectile p : enemyProjectilesList){
                if(p.hasArrived()) toRemoveEnemyProjectiles.add(p);
                else p.move();
            }
            for (Projectile p : towerProjectilesList){
                if(p.hasArrived()) toRemoveTowerProjectiles.add(p);
                else p.move();
            }
            enemyProjectilesList.removeAll(toRemoveEnemyProjectiles);
            towerProjectilesList.removeAll(toRemoveTowerProjectiles);
        }catch (ConcurrentModificationException ex){
            System.out.println("Attempt to modify projectileList while iterating on it.");
        }
    }

}

