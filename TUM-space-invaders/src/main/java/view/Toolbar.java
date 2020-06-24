package view;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.text.DecimalFormat;
import java.util.Optional;

public class Toolbar extends ToolBar {
    private Application gameWindow;
    private Button start;
    private Button stop;
    private Label scoreLabel;
    private String score;

    public Toolbar(Application gameWindow) {
        setScore(0);
        this.start = new Button("Start");
        this.stop = new Button("Stop");
        this.scoreLabel = new Label("Score: " + score);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinWidth(Region.USE_PREF_SIZE);

        initActions();
        this.getItems().addAll(start, new Separator(), stop, spacer, scoreLabel, new Separator());
        this.setGameWindow(gameWindow);
    }

    /**
     * Initialises the actions
     */
    private void initActions() {
        this.start.setOnAction(event -> getGameWindow().gameUI.startGame());

        this.stop.setOnAction(event -> {
            Toolbar.this.getGameWindow().gameUI.stopGame();

            ButtonType YES = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType NO = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to stop the game ?", YES, NO);
            alert.setTitle("Stop Game Confirmation");
            alert.setHeaderText("");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == YES) {
                getGameWindow().gameUI.gameSetup();
            } else {
                getGameWindow().gameUI.startGame();
            }
        });
    }

    /**
     * Resets the toolbar button status
     * @param running Used to disable/enable buttons
     */
    public void resetToolBarButtonStatus(boolean running) {
        this.start.setDisable(running);
        this.stop.setDisable(!running);
    }

    /**
     * @return current gameWindow
     */
    public Application getGameWindow() {
        return this.gameWindow;
    }

    /**
     * @param gameWindow New gameWindow to be set
     */
    public void setGameWindow(Application gameWindow) {
        this.gameWindow = gameWindow;
    }

    /**
     * Sets the score of the toolbar..
     * @param score
     */
    public void setScore(int score) {
        DecimalFormat formatter = new DecimalFormat("00000000");
        this.score = formatter.format(score);
    }
}
