package view;

import controller.GameInternal;
import controller.PlayerController;
import controller.SoundManager;
import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.EnemyShip;
import model.PlayerShip;
import model.Projectile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

public class GameUI extends Canvas implements Runnable {

    //private static final Color backgroundColor = Color.WHITE;
    //private static final Image backgroundImage = getImage("spacebg.png");
    private static final Image backgroundImage = getImage("swamp.jpg");

    private static final int SLEEP_TIME = 1000 / 25; // this gives us 25fps
    private static final Dimension2D DEFAULT_SIZE = new Dimension2D(400, 600);

    private final Application gameWindow;
    // attribute inherited by the JavaFX Canvas class
    private GraphicsContext graphicsContext = this.getGraphicsContext2D();

    // thread responsible for starting game
    private Thread theThread;

    // user interface objects
    private GameInternal gameInternal;
    private Dimension2D size;

    // user control objects
    private PlayerController playerController;

    private HashMap<String, Image> shipImages;
    private HashMap<String, Image> projectileImages;

    /**
     * Sets up all attributes, sets up all graphics
     */
    public GameUI(Application gameWindow) {
        this.size = getPreferredSize();
        this.gameWindow = gameWindow;
        gameSetup();
    }

    /**
     * Called after starting the game thread
     * Constantly updates the game board and renders graphics
     *
     * @see Runnable#run()
     */
    @Override
    public void run() {
        while (this.gameInternal.isRunning()) {
            // updates positions and re-renders graphics
            this.gameInternal.update();
            // when this.gameBoard.hasWon() is null, do nothing
            if (this.gameInternal.hasWon() != null && this.gameInternal.hasWon() == Boolean.FALSE) {
                showAsyncAlert("You lost!");
                this.stopGame();
            } else if (this.gameInternal.hasWon() == Boolean.TRUE) {
                showAsyncAlert("Congratulations! You won!");
                this.stopGame();
            }
            paint(this.graphicsContext);
            try {
                Thread.sleep(SLEEP_TIME); // milliseconds to sleep
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @return current gameBoard
     */
    public GameInternal getGameInternal() {
        return this.gameInternal;
    }

    /**
     * @return mouse steering control object
     */
    public PlayerController getPlayerController() {
        return this.playerController;
    }

    /**
     * @return preferred gameBoard size
     */
    public static Dimension2D getPreferredSize() {
        return DEFAULT_SIZE;
    }

    /**
     * Removes all existing entities from the game board and re-adds them. Status bar is
     * set to default value. Player ship is reset to default starting position.
     * Renders graphics.
     */
    public void gameSetup() {

        this.gameInternal = new GameInternal(this.size);
        this.gameInternal.setSoundManager(new SoundManager());

        setFocusTraversable(true);
        requestFocus();

        this.widthProperty().set(this.size.getWidth());
        this.heightProperty().set(this.size.getHeight());

        this.size = new Dimension2D(getWidth(), getHeight());
        this.shipImages = new HashMap<>();
        this.projectileImages = new HashMap<>();
        this.playerController = new PlayerController(this, this.gameInternal.getPlayerShip());

        //add image resources
        shipImages.put("simple", getImage("shrek.png"));
        shipImages.put("greenie", getImage("greenie.png"));
        shipImages.put("player", getImage("player.png"));
        //DO IT LIKE THIS ↓↓
        projectileImages.put("projectile", getImage("projectile.png"));

        paint(this.graphicsContext);
    }

    /**
     * Acquires the image specified by the file path
     *
     * @param imageFilePath: an image file path that needs to be available in the resources folder of the project
     */
    private static Image getImage(String imageFilePath) {
        if (imageFilePath.isEmpty()) return null;
        try {
            URL imageUrl = GameUI.class.getClassLoader().getResource(imageFilePath);
            if (imageUrl == null) {
                throw new RuntimeException("Please ensure that your resources folder contains the appropriate files for this exercise. \n\t(Faulty URL: " + imageFilePath + ")");
            }
            InputStream inputStream = imageUrl.openStream();
            return new Image(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Image[] getImage(String[] imageFilePaths) {
        Image[] ret = new Image[imageFilePaths.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = getImage(imageFilePaths[i]);
        }
        return ret;
    }


    /**
     * Starts the GameBoardUI Thread, if it wasn't running. Starts the game board,
     * which causes the fishes to change their positions (i.e. move). Renders graphics
     * and updates tool bar status.
     */
    public void startGame() {
        if (!this.gameInternal.isRunning()) {
            this.gameInternal.startGame();

            this.theThread = new Thread(this);
            this.theThread.start();

            paint(this.graphicsContext);
        }
    }

    /**
     * Render the graphics of the whole game by iterating through the entities of the
     * game board and render each of them individually.
     *
     * @param graphics used to draw changes
     */
    private void paint(GraphicsContext graphics) {
        graphics.drawImage(backgroundImage, 0, 0);
        if(!gameInternal.isRunning())
            return;

        //TODO update score
        //gameWindow.getToolbar().setScore(gameInternal.getPlayerScore());

        //paint enemies
        for (EnemyShip enemyShip : gameInternal.getCurrentWave().getListOfEnemies()) {
            paintEnemyShip(enemyShip, graphics);
        }

        //paint projectiles
        for (Projectile p : gameInternal.getProjectiles()) {
            paintProjectile(p, graphics);
        }

        //paint player ship
        paintPlayerShip(gameInternal.getPlayerShip(), graphics);
    }

    /**
     * Show image of a player ship at the current position of the player ship.
     *
     * @param graphics used to draw changes
     */
    private void paintPlayerShip(PlayerShip playerShip, GraphicsContext graphics) {
        if (playerShip.isAlive())
            graphics.drawImage(shipImages.get(playerShip.getImage()), playerShip.getPos().getX(), playerShip.getPos().getY(), playerShip.getDimension().getWidth(), playerShip.getDimension().getHeight());

    }

    /**
     * Show image of a player ship at the current position of the player ship.
     *
     * @param graphics used to draw changes
     */
    private void paintEnemyShip(EnemyShip ship, GraphicsContext graphics) {
        if (ship.isAlive())
            graphics.drawImage(shipImages.get(ship.getImage()), ship.getPos().getX(), ship.getPos().getY(), ship.getDimension().getWidth(), ship.getDimension().getHeight());
    }

    private void paintProjectile(Projectile projectile, GraphicsContext graphics) {
        if (projectile.isAlive())
            graphics.drawImage(projectileImages.get(projectile.getImage()), projectile.getPos().getX(), projectile.getPos().getY(), projectile.getDimension().getWidth(), projectile.getDimension().getHeight());
    }

    /**
     * Stops the game board and set the tool bar to default values.
     */
    public void stopGame() {
        if (this.gameInternal.isRunning()) {
            this.gameInternal.stopGame();
        }
        playerController.remove();
    }

    /**
     * Method used to display alerts Java 8 Lambda Functions: java
     * 8 lambda function without arguments Platform.runLater Function:
     * https://docs.oracle.com/javase/8/javafx/api/javafx/application/Platform.html
     *
     * @param message you want to display as a String
     */
    public void showAsyncAlert(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(message);
            alert.showAndWait();
            this.gameSetup();
        });
    }

    /**
     * Method used to display alerts in Java 8 Lambda Functions: java
     * 8 lambda function without arguments Platform.runLater Function:
     * https://docs.oracle.com/javase/8/javafx/api/javafx/application/Platform.html
     *
     * @param message you want to display as a String
     */
    public void showAsyncAlert(String message, Image icon) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setGraphic(new ImageView(icon));
            alert.setHeaderText(message);
            alert.showAndWait();
            this.gameSetup();
        });
    }
}
