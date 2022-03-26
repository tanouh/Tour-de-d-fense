package up.TowerDefense.model.game;


import java.util.ArrayList;
/**
 * cette classe génère les sous vagues du jeu
 */
public class Subwave{
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
        int i = 0;
        if(System.currentTimeMillis() - timeSinceLastSpawn > spawnInterval)
            if (!enemies.isEmpty())
                enemies.get(0).spawnEnemy();
            timeSinceLastSpawn = System.currentTimeMillis();
    }

}
