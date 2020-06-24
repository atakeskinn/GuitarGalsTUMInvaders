package controller;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.PlayerShip;
import view.GameUI;

public class PlayerController {
    private EventHandler<KeyEvent> keyHandler;
    private GameUI gameUI;
    private PlayerShip player;

    public PlayerController(GameUI gameUI, PlayerShip player) {
        this.player = player;

        keyHandler = e -> {
            if(!player.isAlive()) return;
            KeyCode key = e.getCode();
            if (key == KeyCode.D) {
                player.setSpeedX(2);
            }
            else if (key == KeyCode.A) {
                player.setSpeedX(-2);
            }
            if (key == KeyCode.SPACE) {
                gameUI.getGameInternal().createProjectile(player.getPos().add(player.getDimension().getWidth() / 2, 0), -5);
            }
        };

        this.gameUI = gameUI;
        this.gameUI.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
    }

    public void remove() {
        this.gameUI.removeEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
    }

}
