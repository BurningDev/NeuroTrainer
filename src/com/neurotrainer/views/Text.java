package com.neurotrainer.views;

import java.awt.Color;

public class Text {
	private String text;
	private Color color;
	private int x;
	private int y;
	
	public String getText() {
		return text;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
}
