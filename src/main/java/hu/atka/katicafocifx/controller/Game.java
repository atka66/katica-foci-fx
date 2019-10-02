package hu.atka.katicafocifx.controller;

import java.util.HashSet;
import java.util.Set;
import hu.atka.katicafocifx.view.resources.Settings;
import javafx.scene.input.KeyCode;

public class Game {
	private Player[] players;
	private Ball ball;
	private int[] scores;

	private Set<KeyCode> keyCandidates;

	public Game() {
		this.players = new Player[]{
			new Player(Settings.PLAYER_START_X, Settings.PLAYER_START_Y),
			new Player(Settings.CANVAS_WIDTH - Settings.PLAYER_START_X, Settings.PLAYER_START_Y)
		};
		this.ball = new Ball(Settings.BALL_START_X, Settings.BALL_START_Y);
		this.scores = new int[]{0, 0};
		this.keyCandidates = new HashSet<>();
	}

	public void tick() {
		applyKeys();

		for (Player player : players) {
			player.tick();
		}
		ball.tick(players);
	}

	private void applyKeys() {
		if (keyCandidates.contains(KeyCode.A)) {
			move(true, false);
		} else if (keyCandidates.contains(KeyCode.D)) {
			move(true, true);
		}
		if (keyCandidates.contains(KeyCode.W)) {
			jump(true);
		}

		if (keyCandidates.contains(KeyCode.LEFT)) {
			move(false, false);
		} else if (keyCandidates.contains(KeyCode.RIGHT)) {
			move(false, true);
		}
		if (keyCandidates.contains(KeyCode.UP)) {
			jump(false);
		}
	}

	private void move(boolean firstPlayer, boolean right) {
		players[(firstPlayer ? 0 : 1)].move(right);
	}

	private void jump(boolean firstPlayer) {
		players[(firstPlayer ? 0 : 1)].jump();
	}

	public void pressKey(KeyCode keyCandidate) {
		keyCandidates.add(keyCandidate);
	}

	public void releaseKey(KeyCode keyCandidate) {
		keyCandidates.remove(keyCandidate);
	}

	public Player[] getPlayers() {
		return players;
	}

	public Ball getBall() {
		return ball;
	}

	public int[] getScores() {
		return scores;
	}
}
