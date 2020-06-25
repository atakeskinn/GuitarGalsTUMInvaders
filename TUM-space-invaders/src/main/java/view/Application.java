package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    public GameUI gameUI; // the user interface object, where cars drive
    private Toolbar toolbar;

    /**
     * Starts the game window by setting up a new user
     * interface and adding them to the stage.
     *
     * @see javafx.application.Application#start(javafx.stage.Stage)
     * @param primaryStage
     *            the primary stage for this application, onto which the
     *            application scene can be set.
     */
    @Override
    public void start(Stage primaryStage) {
        this.gameUI = new GameUI(this);
        this.toolbar = new Toolbar(this);

        // initialize GridPane and format
        // GridPanes are divided into columns and rows, like a table
        GridPane gridLayout = new GridPane();
        gridLayout.setPrefSize(400, 600);

        //gridLayout.setPadding(new Insets(1, 1, 1, 1));

        // add all components to the gridLayout
        // second parameter is column index, second parameter is row index of grid
        gridLayout.add(this.gameUI, 0, 0);
        gridLayout.add(this.toolbar, 0, 1);

        // scene and stages
        Scene scene = new Scene(gridLayout);
        primaryStage.setTitle("TUM Space Invaders");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if(gameUI!=null)
            gameUI.stopGame();
    }

    /**
     * The whole game will be executed through the launch() method
     */
    public static void startApp(String[] args) {
        launch(args);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * RUN THIS GAME USING: mvn javafx:run
     * otherwise dependencies do not work correctly!
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Started Application");
        Application.startApp(args);
    }
}
