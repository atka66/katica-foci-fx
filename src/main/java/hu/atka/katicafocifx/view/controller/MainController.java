package hu.atka.katicafocifx.view.controller;

import java.net.URL;
import java.util.ResourceBundle;
import hu.atka.katicafocifx.controller.Ball;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
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

	private void renderFloor() {
		gc.setFill(Settings.COLOR_FLOOR_FILL);
		gc.fillRect(0, Settings.CANVAS_HEIGHT - Settings.WORLD_FLOOR_HEIGHT, Settings.CANVAS_WIDTH, Settings.WORLD_FLOOR_HEIGHT);
	}

	private void renderPlayer(Player player, Color color) {
		gc.setFill(color);
		gc.fillArc(
			player.getX() - Settings.PLAYER_RADIUS, Settings.CANVAS_HEIGHT - (player.getY() + Settings.PLAYER_RADIUS),
			Settings.PLAYER_RADIUS * 2, Settings.PLAYER_RADIUS * 2, 0, 180, ArcType.CHORD
		);
	}

	private void renderBall(Ball ball) {
		gc.setFill(Settings.COLOR_BALL_FILL);
		gc.fillOval(
			ball.getX() - Settings.BALL_RADIUS, Settings.CANVAS_HEIGHT - (ball.getY() + Settings.BALL_RADIUS),
			Settings.BALL_RADIUS * 2, Settings.BALL_RADIUS * 2
		);
		gc.setStroke(Settings.COLOR_BALL_STROKE);
		gc.strokeOval(
			ball.getX() - Settings.BALL_RADIUS, Settings.CANVAS_HEIGHT - (ball.getY() + Settings.BALL_RADIUS),
			Settings.BALL_RADIUS * 2, Settings.BALL_RADIUS * 2
		);
	}

	private void render() {
		clearCanvas();
		Player[] players = game.getPlayers();
		renderFloor();
		renderPlayer(players[0], Settings.COLOR_PLAYER_ONE_FILL);
		renderPlayer(players[1], Settings.COLOR_PLAYER_TWO_FILL);
		renderBall(game.getBall());
	}

	private void clearCanvas() {
		gc.setFill(Settings.COLOR_FILL_BOARD_CLEAR);
		gc.fillRect(0, 0, Settings.CANVAS_WIDTH, Settings.CANVAS_HEIGHT);
	}
}
