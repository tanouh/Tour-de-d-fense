package up.TowerDefense.model.game;

/**
 * la classe est utilisé en tant que gestionnaire de vagues
 */
public class Wave {
    public static boolean endOfLevel; // Annonce la fin de la vague

    private Subwave currentWave ;
    private boolean running = false; // si une vague est en cours actuellement



    public static int waveOrder ;
    public static long TIME_SINCE_LAST_SPAWN;
    public static final int MAX_NB_WAVES = Game.getNbWavesTotal();
    public static long TIME_INTERVAL = 20000;  // Intervalle de temps entre deux séries de vagues
    public static long DELAY = 5000;

    public Wave() {
        resetTimeSinceLastSpawn();
        waveOrder = 1;
        currentWave = Subwave.subwavesInOrder();
    }

    /**
     * The action to be performed by this timer task.
     */
    public void run() {
        if (endOfLevel) return;
        if (!running &&  System.currentTimeMillis() - TIME_SINCE_LAST_SPAWN > DELAY/Game.getGameSpeed()){
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

    public static void resetWave(){
        endOfLevel = false;
    }

    public static void resetTimeSinceLastSpawn() {
        TIME_SINCE_LAST_SPAWN = System.currentTimeMillis();
    }
}