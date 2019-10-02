package hu.atka.katicafocifx.controller;

import hu.atka.katicafocifx.view.resources.Settings;

public class Player {
	private int x;
	private int y;
	private int speedX;
	private int speedY;
	private boolean onFloor;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.speedX = 0;
		this.speedY = 0;
		this.onFloor = false;
	}

	public void tick() {
		this.x += this.speedX;
		this.y += this.speedY;
		this.applyFriction();
		this.applyGravity();
		this.correctCollision();
	}

	private void applyFriction() {
		if (this.speedX > 0) {
			this.speedX -= Settings.PLAYER_MOVE_FRICTION;
			if (this.speedX < 0) {
				this.speedX = 0;
			}
		}

		if (this.speedX < 0) {
			this.speedX += Settings.PLAYER_MOVE_FRICTION;
			if (this.speedX > 0) {
				this.speedX = 0;
			}
		}
	}

	private void applyGravity() {
		this.speedY -= Settings.WORLD_GRAVITY;
	}

	private void correctCollision() {
		// floor
		if (this.y < Settings.WORLD_FLOOR_HEIGHT) {
			this.speedY = 0;
			this.y = Settings.WORLD_FLOOR_HEIGHT;
			this.onFloor = true;
		}
		// wall
		if (this.x < (Settings.WORLD_WALL_WIDTH - Settings.PLAYER_RADIUS)) {
			this.x = Settings.WORLD_WALL_WIDTH - Settings.PLAYER_RADIUS;
		}
		if (this.x > Settings.CANVAS_WIDTH - (Settings.WORLD_WALL_WIDTH - Settings.PLAYER_RADIUS)) {
			this.x = Settings.CANVAS_WIDTH - (Settings.WORLD_WALL_WIDTH - Settings.PLAYER_RADIUS);
		}
	}

	public void move(boolean right) {
		if (Math.abs(this.speedX) < Settings.PLAYER_MOVE_SPEEDLIMIT) {
			this.speedX += (right ? Settings.PLAYER_MOVE_ACCELERATION : -Settings.PLAYER_MOVE_ACCELERATION);
		}
	}

	public void jump() {
		if (this.onFloor) {
			this.speedY += Settings.PLAYER_JUMP_ACCELERATION;
			this.onFloor = false;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}
}
