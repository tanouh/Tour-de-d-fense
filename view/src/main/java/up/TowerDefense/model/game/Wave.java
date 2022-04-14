package up.TowerDefense.model.game;

/**
 * la classe est utilisé en tant que gestionnaire de vagues
 */
public class Wave {
    public static boolean endOfLevel = false; // Annonce la fin de la vague

    private Subwave currentWave ;
    private boolean running = false; // si une vague est en cours actuellement



    public static int waveOrder ;

    public static int level = 1;
    public static long TIME_SINCE_LAST_SPAWN;
    public static final int MAX_NB_WAVES = 5;
    public static long TIME_INTERVAL = 10000;  // Intervalle de temps entre deux séries de vagues
    public static long DELAY = 1000;

    public Wave() {
        resetTimeSinceLastSpawn();
        waveOrder = 1;
        Game.setWavesLeft(Game.getWavesLeft()-1);
        currentWave = Subwave.subwavesInOrder();
    }

    /**
     * The action to be performed by this timer task.
     */
    public void run() {
        if (endOfLevel) return;
        if (!running &&  System.currentTimeMillis() - TIME_SINCE_LAST_SPAWN > DELAY){
            TIME_SINCE_LAST_SPAWN = System.currentTimeMillis();
            running = true;
        }
        if(running){
            if(waveOrder > MAX_NB_WAVES){
                endOfLevel = true;
                running = false;
            }else {
                if (!currentWave.isFinished()) {
                    currentWave.run();
                } else {
                    if(System.currentTimeMillis() - TIME_SINCE_LAST_SPAWN > TIME_INTERVAL){
                        currentWave = Subwave.subwavesInOrder();
                        resetTimeSinceLastSpawn();
                    }
                }
            }
        }
    }

    private void upgrade(){
        for (int i = 1 ; i < level ; i++){
            currentWave.upgrade();
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

    public static void resetTimeSinceLastSpawn() {
        TIME_SINCE_LAST_SPAWN = System.currentTimeMillis();
    }


    /**
     * Lance la prochaine vague lorsque c'est possible
     */
   /* public void startNextWave() {
        if (!finished){
            if (currentWave == null){
                if (waves != null && waves.size() > 0) {
                    currentWave = waves.get(0);
                    running = true;
                }
            }
        } else {
                int nextIndex = 1 + waves.indexOf(currentWave);
                if (nextIndex < waves.size()) {
                    currentWave = waves.get(1 + waves.indexOf(currentWave));
                    running = true;
                }else{
                    running = false;
                }
            }
    }*/


    /**
     * Actualise le gestionnaire de vague
     */
    /*public void update() {
        if (currentWave != null) {
            if (!running && (waves.indexOf(currentWave) == waves.size() - 1))
                finished = true;
            if (currentWave.enemies.isEmpty()) {
                running = false;
                return;
            }
            timeSinceLastSpawn = System.currentTimeMillis() - timeSinceLastSpawn;
                if (timeSinceLastSpawn >= currentWave.enemies.get(0).getInterval()) {
                    // Fait spawn le premier enemy de la liste et le supprime de la liste des enemy à spawner
                    // puis relance le compteur de spawn
                    EnemySpawn nextEnemySpawn = currentWave.enemies.get(0);
                    if (nextEnemySpawn.quantity-- <= 1)
                        EnemyList.add(currentWave.enemies.remove(0).enemy); // on ajoute dans la liste des enemy en cours
                                                                                 // et on le retire de la sous vague en cours
                    else
                        EnemyList.add(nextEnemySpawn.getEnemy());
                    timeSinceLastSpawn = System.currentTimeMillis();
                }
        }
    }*/

}