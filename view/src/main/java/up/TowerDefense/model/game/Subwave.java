package up.TowerDefense.model.game;


import up.TowerDefense.view.componentHandler.MapGenerator;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 * cette classe génère les sous vagues du jeu
 */
public class Subwave  {
    private long spawnInterval; // temps entre l'apparition des enemy
    //private String name; 
    public ArrayList<EnemySpawn> enemies;
    private long timeSinceLastSpawn;


    public Subwave(ArrayList<EnemySpawn> list) {
        this.enemies = list;
    }

    public long getInterval(){
        return this.spawnInterval;
    }

    public void spawn(){
        enemies.add(EnemySpawn.getBacterium());
    }

}
