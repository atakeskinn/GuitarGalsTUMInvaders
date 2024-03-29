package controller;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class GameInternal {

    private static final String wave1 = "4, 6, 3, 20, 0," +
                                        "S S S S S S" +
                                        "S S S S S S" +
                                        "S S S S S S" +
                                        "S S S S S S";


    private Dimension2D screenSize;

    private boolean isRunning;
    private Boolean gameWon;

    private int playerScore;
    private List<Projectile> projectiles;
    private List<Projectile> projectileBuffer;
    private PlayerShip playerShip;
    private Wave currentWave;

//    public SoundManagerInterface getSoundManager() {
//        return soundManager;
//    }

    private SoundManagerInterface soundManager;
    private DataRecorder dataRecorder;


    public GameInternal(Dimension2D size) {
        this.screenSize = size;
        playerShip = new PlayerShip(screenSize); //250 ,30
        projectiles = new ArrayList<>();
        projectileBuffer = new ArrayList<>();
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

    public void setSoundManager(SoundManagerInterface soundManager) {
        this.soundManager = soundManager;
    }

    private void setWave() {
        currentWave = Wave.parseWaveInfo(wave1, screenSize);
    }

    public void update() {
        //TODO: Game Logic Here
		//TODO: Move entities, check for collisions, shoot
        playerShip.update();

        //update projectiles:
        for(Projectile p : projectiles) {
            p.update();

            //test for collisions
            for (EnemyShip enemyShip : currentWave.getListOfEnemies()) {
                boolean collided = testCollision(p.getPos(), p.getDimension(), enemyShip.getPos(), enemyShip.getDimension());
                if(collided) {
                    //System.out.println("Collision!");
                    soundManager.playEnemyDeathSound();
                    enemyShip.setAlive(false);
                    p.setAlive(false);
                    dataRecorder.recordData("Enemy killed!");
                }
            }
        }
        currentWave.update();

        currentWave.getListOfEnemies().removeIf(e -> !e.isAlive());
        projectiles.removeIf(p -> !p.isAlive());

        projectiles.addAll(projectileBuffer);
        projectileBuffer = new ArrayList<>();



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
        dataRecorder.recordData("Game started!");
    }

    /**
     * Stops the game. Entities stop moving and background music stops playing.
     */
    public void stopGame() {
        stopMusic();
        this.isRunning = false;
        dataRecorder.recordData("Game stopped!");
        dataRecorder.saveData();
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

    /**
     * Create projectile at position, with speedY
     * @param position position
     * @param speedY speedY
     */
    public void createProjectile(Point2D position, double speedY) {
        Projectile projectile = new SimpleProjectile(position, speedY);
        projectileBuffer.add(projectile);
        this.soundManager.playPlayerShootSound();
    }

    public boolean testCollision(Point2D pos1, Dimension2D dim1, Point2D pos2, Dimension2D dim2) {

        //p1s sides have no gap between them and p2s sides (better collision detection)
        return pos1.getX() < pos2.getX() + dim2.getWidth() &&
                pos1.getX() + dim1.getWidth() > pos2.getX() &&
                screenSize.getHeight() - pos1.getY() < screenSize.getHeight() - pos2.getY() + dim2.getHeight() &&
                screenSize.getHeight() - pos1.getY() + dim1.getHeight() > screenSize.getHeight() - pos2.getY();
    }

}
