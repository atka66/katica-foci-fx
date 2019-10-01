package hu.atka.katicafocifx.controller;

import java.util.HashSet;
import java.util.Set;
import javafx.scene.input.KeyCode;

public class Game {
	private Player[] players;
	private int[] scores;

	private Set<KeyCode> keyCandidates;

	public Game() {
		this.players = new Player[]{new Player(30, 30), new Player(200, 30)};
		this.scores = new int[]{0, 0};
		keyCandidates = new HashSet<>();
	}

	public void tick() {
		if (keyCandidates.contains(KeyCode.A)) {
			move(true, false);
		} else if (keyCandidates.contains(KeyCode.D)) {
			move(true, true);
		}
		if (keyCandidates.contains(KeyCode.W)) {
			jump(true);
		}

		if (keyCandidates.contains(KeyCode.KP_LEFT)) {
			move(false, false);
		} else if (keyCandidates.contains(KeyCode.KP_RIGHT)) {
			move(false, true);
		}
		if (keyCandidates.contains(KeyCode.KP_UP)) {
			jump(false);
		}

		for (Player player : players) {
			player.tick();
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

	public int[] getScores() {
		return scores;
	}
}
