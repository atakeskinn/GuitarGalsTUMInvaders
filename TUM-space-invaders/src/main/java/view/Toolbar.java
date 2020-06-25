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

    public Toolbar(Application gameWindow) {
        this.start = new Button("Start");
        this.stop = new Button("Stop");
        start.setFocusTraversable(false);
        stop.setFocusTraversable(false);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinWidth(Region.USE_PREF_SIZE);

        initActions();
        this.getItems().addAll(start, new Separator(), stop, spacer, new Separator());
        this.setGameWindow(gameWindow);
        setFocusTraversable(false);
    }

    /**
     * Initialises the actions
     */
    private void initActions() {
        this.start.setOnAction(event -> getGameWindow().gameUI.startGame());

        this.stop.setOnAction(event -> {
            if(!gameWindow.gameUI.getGameInternal().isRunning()) return;
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
}
