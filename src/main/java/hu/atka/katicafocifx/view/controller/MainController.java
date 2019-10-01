package hu.atka.katicafocifx.view.controller;

import java.net.URL;
import java.util.ResourceBundle;
import hu.atka.katicafocifx.controller.Game;
import hu.atka.katicafocifx.controller.Player;
import hu.atka.katicafocifx.view.resources.Settings;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class MainController implements Initializable {

	@FXML
	private Canvas canvas;

	private GraphicsContext gc;
	private Timeline timeline;

	private Game game;

	public void initialize(URL location, ResourceBundle resources) {
		canvas.setFocusTraversable(true);
		canvas.setWidth(Settings.CANVAS_WIDTH);
		canvas.setHeight(Settings.CANVAS_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		game = new Game();

		timeline = new Timeline(new KeyFrame(Duration.millis(20), ae -> {
			game.tick();
			render();
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	@FXML
	private void onKeyPressed(KeyEvent event) {
		game.pressKey(event.getCode());
	}

	@FXML
	private void onKeyReleased(KeyEvent event) {
		game.releaseKey(event.getCode());
	}

	private void render() {
		clearCanvas();
		Player[] players = game.getPlayers();
		gc.setFill(Settings.COLOR_PLAYER_ONE_FILL);
		gc.fillRect(players[0].getX(), Settings.CANVAS_HEIGHT - players[0].getY(), Settings.PLAYER_WIDTH, Settings.PLAYER_WIDTH);
	}

	private void clearCanvas() {
		gc.setFill(Settings.COLOR_FILL_BOARD_CLEAR);
		gc.fillRect(0, 0, Settings.CANVAS_WIDTH, Settings.CANVAS_HEIGHT);
	}
}
