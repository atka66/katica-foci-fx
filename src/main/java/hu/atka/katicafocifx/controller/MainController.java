package hu.atka.katicafocifx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import hu.atka.katicafocifx.resources.Settings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class MainController implements Initializable {

	@FXML
	private Canvas canvas;

	private GraphicsContext gc;

	public void initialize(URL location, ResourceBundle resources) {
		canvas.setWidth(Settings.CANVAS_WIDTH);
		canvas.setHeight(Settings.CANVAS_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		render();
	}

	private void render() {
		clearCanvas();
	}

	private void clearCanvas() {
		gc.setFill(Settings.COLOR_FILL_BOARD_CLEAR);
		gc.fillRect(0, 0, Settings.CANVAS_WIDTH, Settings.CANVAS_HEIGHT);
	}
}
