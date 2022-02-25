package com.neurotrainer.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class TestPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private List<Text> texts;
	private List<Form> forms;
	
	public TestPanel() {
		texts = new ArrayList<>();
		forms = new ArrayList<>();
		setBackground(Color.WHITE);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setFont(new Font("Arial", Font.BOLD, 50));
		
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setRenderingHints(rh);
	    
	    for(Text text : texts) {
			g2d.setColor(text.getColor());
			g2d.drawString(text.getText(), text.getX(), text.getY());
	    }
	    
	    for(Form form : forms) {
	    	int x = form.getX();
	    	int y = form.getY();
	    	int width = form.getWidth();
	    	int height = form.getHeight();
	    	
			switch(form.getType()) {
				case Form.CIRCLE:
					if(form.isFilled()) {
						g2d.fillArc(x, y, width, height, 0, 360);
					} else {
						g2d.drawArc(x, y, width, height, 0, 360);
					}
					break;
				case Form.RECT:
					if(form.isFilled()) {
						g2d.fillRect(x, y, width, height);
					} else {
						g2d.drawRect(x, y, width, height);
					}
					break;
			}
		}
	}
	
	public void addForm(int type, int x, int y, int height, int width, boolean filled) {
		Form form = new Form();
		form.setType(type);
		form.setX(x);
		form.setY(y);
		form.setHeight(height);
		form.setWidth(width);
		form.setFilled(filled);
		forms.add(form);
	}
	
	public void addText(String textString, int x, int y) {
		Text text = new Text();
		text.setText(textString);
		text.setColor(Color.BLACK);
		text.setX(x);
		text.setY(y);
		
		this.texts.add(text);
	}
	
	public void addText(String textString, int x, int y, Color color) {
		Text text = new Text();
		text.setText(textString);
		text.setColor(color);
		text.setX(x);
		text.setY(y);
		
		this.texts.add(text);
	}
	
	public void emptyComponents() {
		this.texts.clear();
		this.forms.clear();
	}
}
