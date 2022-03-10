package up.TowerDefense.model.game;


import java.util.ArrayList;
/**
 * cette classe génère les sous vagues du jeu
 */
public class Subwave{
    private long spawnInterval; // temps entre l'apparition des enemy
    //private String name; 
    public ArrayList<EnemySpawn> enemies;

    public Subwave(ArrayList<EnemySpawn> list) {
        this.enemies = list;
    }

    public long getInterval(){
        return this.spawnInterval;
    }

}
