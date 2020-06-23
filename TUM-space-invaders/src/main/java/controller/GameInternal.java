package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Dimension2D;
import model.PlayerShip;
import model.Projectile;
import model.Wave;

public class GameInternal {

    private static final String wave1 = "4, 6, 5, 20, 0," +
                                        "S S S S S S" +
                                        "S S S S S S" +
                                        "S S S S S S" +
                                        "S S S S S S";


    private Dimension2D size;

    private boolean isRunning;
    private Boolean gameWon;

    private int playerScore;
    private List<Projectile> projectiles;
    private PlayerShip playerShip;
    private Wave currentWave;
    private SoundManager soundManager;
    private DataRecorder dataRecorder;

    public GameInternal(Dimension2D size) {
        playerShip = new PlayerShip(); //250 ,30
        projectiles = new ArrayList<>();
        this.size = size;
        this.setWave();
        this.dataRecorder = new DataRecorder();
        //You use this by adding:
        //"this.gameUI.getGameInternal().getDataRecorder().recordData(Data.recordData(String Info));"
        //to your Moment/Event in Code that you want to record
    }

    public DataRecorder getDataRecorder() { return dataRecorder; }

    public int getPlayerScore() {
        return playerScore;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public Wave getCurrentWave() {
        return currentWave;
    }

    public PlayerShip getPlayerShip() {
        return playerShip;
    }

    public void setSoundManager(SoundManager soundManager) {
        this.soundManager = soundManager;
    }

    private void setWave() {
        currentWave = Wave.parseWaveInfo(wave1, size);
    }

    public void update() {
        //TODO: Game Logic Here
		//TODO: Move entities, check for collisions, shoot
        currentWave.update();
    }


    public boolean isRunning() {
        return isRunning;
    }

    public Boolean hasWon() {
        return gameWon;
    }

    /**
     * Starts the game. Entities start to move and background music starts to play.
     */
    public void startGame() {
        playMusic();
        this.isRunning = true;
    }

    /**
     * Stops the game. Entities stop moving and background music stops playing.
     */
    public void stopGame() {
        stopMusic();
        this.isRunning = false;
    }

    /**
     * Starts the background music
     */
    public void playMusic() {
        this.soundManager.playBackgroundMusic();
    }

    /**
     * Stops the background music
     */
    public void stopMusic() {
        this.soundManager.stopBackgroundMusic();
    }
}
