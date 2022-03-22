package up.TowerDefense.model.game;

import java.util.ArrayList;

import up.TowerDefense.model.character.Enemy;
import up.TowerDefense.model.character.PresetEnemy;
import up.TowerDefense.model.character.SuicideBomber;
import up.TowerDefense.model.object.Position;
/**
 * la classe est utilisé en tant que gestionnaire de vagues
 */
public class Wave{
    private long timeSinceLastSpawn = 0; // compteur de temps, permettant de savoir quand lancer le prochain enemy de la
                                 // file
    private boolean running = false; // si une vague est en cours actuellement
    public ArrayList<Enemy> EnemyList;
    public ArrayList<Subwave> waves; // Liste des sous-vagues
    private Subwave currentWave = null;
    public boolean finished = false; // si toutes les vagues sont finis
    private static int countWave = 0;
    private int waveNumber = 0;

    public Wave(ArrayList<Subwave> subwave) {
        //waves = new ArrayList<Subwave>();
        this.waves = subwave;
        this.waveNumber = countWave;
        countWave++;
        
        // test de création de vagues:
        /*Position pos = new Position(15, 50);
        ArrayList<EnemySpawn> enemies = new ArrayList<EnemySpawn>();
        enemies.add(new EnemySpawn(50, new SuicideBomber(PresetEnemy.Bacterium(), pos), 5));
        Subwave subwaves = new Subwave(enemies);
        waves.add(subwaves);*/
    }

    /**
     * Lance la prochaine vague lorque c'est possible
     */
    public void startNextWave() {
        if (!finished){
            if (currentWave == null){
                if (waves != null && waves.size() > 0) {
                    currentWave = waves.get(0);
                    running = true;
                }
            }
            else {
                int nextIndex = 1 + waves.indexOf(currentWave);
                if (nextIndex < waves.size()) {
                    currentWave = waves.get(1 + waves.indexOf(currentWave));
                    running = true;
                }
            }
        }
    }

    /**
     * Actualise le gestionnaire de vague
     */
    public void update() {
        if (currentWave != null) {
            if (!running && (waves.indexOf(currentWave) == waves.size() - 1))
                finished = true;
            if (currentWave.enemies.isEmpty()) {
                running = false;
                return;
            } 
            timeSinceLastSpawn = System.nanoTime();
                if (timeSinceLastSpawn >= currentWave.enemies.get(0).getInterval()) {
                    // Fait spawn le premier enemy de la liste et le supprime de la liste des enemy à spawner
                    // puis relance le compteur de spawn
                    EnemySpawn nextEnemySpawn = currentWave.enemies.get(0);
                    if (nextEnemySpawn.quantity-- <= 1)
                        EnemyList.add(currentWave.enemies.remove(0).enemy); // on ajoute dans la liste des enemy en cours
                                                                                 // et on le retire de la sous vague en cours
                    else
                        EnemyList.add(nextEnemySpawn.getEnemy());
                    timeSinceLastSpawn = 0;
                }
        }
    }

    /**
     * Verifie si la vague a ete lancé
     */
    public boolean hasStarted() {
        return currentWave != null;
    }
    
    /**
     * vérifie si une vague est en cours
     */
    public boolean isRunning() {
        return running;
    }
    

}