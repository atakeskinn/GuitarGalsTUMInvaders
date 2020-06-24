package model;

import javafx.geometry.Dimension2D;

import javafx.geometry.Point2D;

public class PlayerShip {
	public static Dimension2D DEFAULT_SIZE = new Dimension2D(30 /2 *3, 20 /2 * 3);
	public static String IMAGE_NAME = "player";

	private Dimension2D screenSize;

	private Dimension2D dimension;
	private Point2D pos;
	private double speedX;
	private boolean alive;

	public Point2D getPos() {
		return pos;
	}

	public void setPos(Point2D pos) {
		this.pos = pos;
	}

	public PlayerShip(Dimension2D screenSize) {
		this.screenSize = screenSize;
		this.dimension = DEFAULT_SIZE;
		initPlayer((int) ((screenSize.getWidth()/2) - (dimension.getWidth()/2)), 500);
	}
	private void initPlayer(int x, int y) {
		pos = new Point2D(x, y);
		this.speedX = 0;
		alive = true;
	}

	public void update() {
		if(!alive) return;
		pos = pos.add(speedX, 0);
		if (pos.getX() <= 2) {
			pos = new Point2D(2, pos.getY());
		}
		if (pos.getX() + 2 >= screenSize.getWidth() - dimension.getWidth()) {
			pos = new Point2D(screenSize.getWidth() - dimension.getWidth() - 2, pos.getY());
		}
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public Dimension2D getDimension() {
		return dimension;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public static String getImage() {
		return IMAGE_NAME;
	}
}
