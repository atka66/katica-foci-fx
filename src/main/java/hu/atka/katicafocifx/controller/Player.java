package hu.atka.katicafocifx.controller;

import hu.atka.katicafocifx.view.resources.Settings;

public class Player {
	private int x;
	private int y;
	private int speedX;
	private int speedY;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.speedX = 0;
		this.speedY = 0;
	}

	public void tick() {
		this.x += this.speedX;
		this.y += this.speedY;
	}

	public void move(boolean right) {
		this.speedX += (right ? Settings.PLAYER_MOVE_ACCELERATION : -Settings.PLAYER_MOVE_ACCELERATION);
	}

	public void jump() {
		this.speedY += Settings.PLAYER_JUMP_ACCELERATION;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
