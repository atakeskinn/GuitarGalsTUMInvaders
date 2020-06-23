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
            //DO IT LIKE THIS ↓↓
            //if(e.getCode() == KeyCode.A) do stuff {};
            //TODO: Key Press Handling
        };

        this.gameUI = gameUI;
        this.gameUI.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
    }
	
}
