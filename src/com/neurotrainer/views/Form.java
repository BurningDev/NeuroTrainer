package com.neurotrainer.views;

public class Form {
	public static final int RECT = 0;
	public static final int CIRCLE = 1;
	
	private int type;
	private int x, y, height, width;
	
	private boolean filled;
	
	public int getType() {
		return type;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setFilled(boolean filled) {
		this.filled = filled;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public boolean isFilled() {
		return filled;
	}
}