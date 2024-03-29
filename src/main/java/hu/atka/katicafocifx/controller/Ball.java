package hu.atka.katicafocifx.controller;

import hu.atka.katicafocifx.view.resources.Settings;

public class Ball {
	private int x;
	private int y;
	private int speedX;
	private int speedY;

	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.speedX = 0;
		this.speedY = 0;
	}

	public void tick(Player[] players) {
		this.x += this.speedX;
		this.y += this.speedY;
		this.applyGravity();
		this.correctCollision(players);
	}

	private void applyGravity() {
		this.speedY -= Settings.WORLD_GRAVITY;
	}

	private void correctCollision(Player[] players) {
		// players
		for (Player player : players) {
			double dist = Math.sqrt(Math.pow(player.getX() - this.getX(), 2) + Math.pow(player.getY() - this.getY(), 2));
			if (dist < Settings.BALL_RADIUS + Settings.PLAYER_RADIUS) {
				double tangentX = player.getY() - this.y;
				double tangentY = player.getX() - this.x;
				// normalization
				tangentX /= dist;
				tangentY /= dist;
				double relVelX = player.getSpeedX() - this.speedX;
				double relVelY = player.getSpeedY() - this.speedY;
				double length = (relVelX * tangentX) + (relVelY * tangentY);
				double velCompOnTanX =  tangentX * length;
				double velCompOnTanY =  tangentY * length;
				double velCompPerToTanX = relVelX - velCompOnTanX;
				double velCompPerToTanY = relVelY - velCompOnTanY;
				this.speedX -= velCompPerToTanX;
				this.speedY -= velCompPerToTanY;
			}
		}
		// floor
		if (this.y < (Settings.WORLD_FLOOR_HEIGHT + Settings.BALL_RADIUS)) {
			this.speedY = -this.speedY - Settings.BALL_BOUNCE_SPEED_LOSS;
			this.y = Settings.WORLD_FLOOR_HEIGHT + Settings.BALL_RADIUS;
			this.speedX *= Settings.BALL_BOUNCE_FRICTION;
		}
		// wall
		if (this.x < (Settings.WORLD_WALL_WIDTH - Settings.BALL_RADIUS)) {
			this.speedX = -this.speedX;
			this.x = Settings.WORLD_WALL_WIDTH - Settings.BALL_RADIUS;
		}
		if (this.x > Settings.CANVAS_WIDTH - (Settings.WORLD_WALL_WIDTH - Settings.BALL_RADIUS)) {
			this.speedX = -this.speedX;
			this.x = Settings.CANVAS_WIDTH - (Settings.WORLD_WALL_WIDTH - Settings.BALL_RADIUS);
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
