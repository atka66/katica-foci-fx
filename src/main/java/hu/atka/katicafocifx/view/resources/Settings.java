package hu.atka.katicafocifx.view.resources;

import javafx.scene.paint.Color;

public class Settings {
	public static final int WINDOW_WIDTH = 640;
	public static final int WINDOW_HEIGHT = 480;
	public static final int CANVAS_WIDTH = WINDOW_WIDTH;
	public static final int CANVAS_HEIGHT = WINDOW_HEIGHT;

	public static final int PLAYER_RADIUS = 32;
	public static final int PLAYER_MOVE_ACCELERATION = 2;
	public static final int PLAYER_MOVE_FRICTION = 1;
	public static final int PLAYER_MOVE_SPEEDLIMIT = 5;
	public static final int PLAYER_JUMP_ACCELERATION = 15;
	public static final int PLAYER_START_X = 192;
	public static final int PLAYER_START_Y = 256;

	public static final int BALL_RADIUS = 8;
	public static final int BALL_START_X = CANVAS_WIDTH / 2;
	public static final int BALL_START_Y = 384;
	public static final int BALL_BOUNCE_SPEED_LOSS = 3;
	public static final double BALL_BOUNCE_FRICTION = 0.9;

	public static final int WORLD_GRAVITY = 1;
	public static final int WORLD_FLOOR_HEIGHT = 128;
	public static final int WORLD_WALL_WIDTH = 128;

	public static final Color COLOR_FILL_BOARD_CLEAR = Color.SILVER;
	public static final Color COLOR_PLAYER_ONE_FILL = Color.RED;
	public static final Color COLOR_PLAYER_TWO_FILL = Color.BLUE;
	public static final Color COLOR_BALL_FILL = Color.GRAY;
	public static final Color COLOR_BALL_STROKE = Color.BLACK;
	public static final Color COLOR_FLOOR_FILL = Color.DARKGREEN;
}
