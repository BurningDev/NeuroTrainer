package com.neurotrainer.test;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import com.neurotrainer.core.PsychologyTest;
import com.neurotrainer.views.TestPanel;

public class Stroop implements PsychologyTest {

	private TestPanel panel;

	// **************************
	// ******** SETTINGS ********
	// **************************
	private static final int MAX_AMOUNT = 90;

	private int colorDisplayedPast;
	private int colorNamePast;

	private int colorDisplayed;
	private int colorName;

	private boolean instruction = true;

	private long turnTime;

	private short amount;

	private Map<Integer, Integer> amountColors;
	private Map<Integer, Long> timeColors;
	private Map<Integer, Integer> hitColors;

	private boolean colorMet;

	private boolean testStarted = false;
	private long testStartCountdown = 3000;

	@Override
	public void start() {

	}

	@Override
	public String getInstruction() {
		return null;
	}

	@Override
	public String getName() {
		return "Stroop";
	}

	@Override
	public String getId() {
		return getName().toLowerCase();
	}

	@Override
	public void init(TestPanel panel) {
		this.panel = panel;

		this.panel.setBackground(Color.DARK_GRAY);
		this.panel.addText("Press 'Start' to begin", 1920 / 2 - 300, 1080 / 2 - 10);

		this.amountColors = new HashMap<>();
		this.amountColors.put(0, 0);
		this.amountColors.put(1, 0);
		this.amountColors.put(2, 0);
		this.amountColors.put(3, 0);

		this.timeColors = new HashMap<>();
		this.timeColors.put(0, 0L);
		this.timeColors.put(1, 0L);
		this.timeColors.put(2, 0L);
		this.timeColors.put(3, 0L);

		this.hitColors = new HashMap<>();
		this.hitColors.put(0, 0);
		this.hitColors.put(1, 0);
		this.hitColors.put(2, 0);
		this.hitColors.put(3, 0);
	}

	public void update(long delta) {
		if (instruction == false && testStarted == false) {
			testStartCountdown -= delta;

			if (testStartCountdown <= 0) {
				amount++;
				colorMet = true;
				testStarted = true;
				selectWord();

				amountColors.put(colorDisplayed, amountColors.get(colorDisplayed) + 1);

				panel.addText(textToDisplay, 1920 / 2, 1080 / 2, colorToDisplay);

				panel.revalidate();
				panel.validate();
				panel.updateUI();

				turnTime = System.currentTimeMillis();
				return;
			}

			panel.emptyComponents();

			panel.revalidate();
			panel.validate();
			panel.updateUI();
		}
	}

	public void keyPressed(String key) {
		// Only buttons are accepted
		if (!(key.startsWith("Taste"))) {
			return;
		}

		// Activation of instruction
		if (instruction == true && key.equals("Taste 7")) {
			instruction = false;
		} else if (instruction == true) {
			return;
		} else {
			if (key.equals("Taste " + colorDisplayed)) {
				if (colorMet == true) {
					timeColors.put(colorDisplayed,
							timeColors.get(colorDisplayed) + (System.currentTimeMillis() - turnTime));
					hitColors.put(colorDisplayed, hitColors.get(colorDisplayed) + 1);
				}
			} else {
				colorMet = false;
			}

			if (key.equals("Taste " + colorDisplayed)) {
				amount++;

				if (amount > MAX_AMOUNT) {
					StringBuffer message = new StringBuffer();
					message.append("Amount: " + (this.amount - 1) + "\n");
					message.append("ITEM   |N   |HIT |PER  |TIME" + "\n");
					message.append("-------+----+----+-----+-------" + "\n");

					for (int i = 0; i < amountColors.size(); i++) {

						double percentage = (double) hitColors.get(i)
								/ (double) (amountColors.get(i) == 0 ? 1 : amountColors.get(i));
						percentage = round(percentage, 2);

						message.append(getColorById(i) + "  ");

						message.append(fillLength(String.valueOf(amountColors.get(i)), 3) + "  ");

						message.append(fillLength(String.valueOf(hitColors.get(i)), 3) + "  ");
						message.append(fillLength(String.valueOf(percentage), 4) + "  ");
						message.append(fillLength(String
								.valueOf(timeColors.get(i) / (amountColors.get(i) == 0 ? 1 : amountColors.get(i))), 4)
								+ " ms \n");
					}
					System.out.println(message.toString());

					this.panel.emptyComponents();

					panel.emptyComponents();

					panel.revalidate();
					panel.validate();
					panel.updateUI();

					colorDisplayed = -1;
					return;
				}

				colorMet = true;

				selectWord();

				amountColors.put(colorDisplayed, amountColors.get(colorDisplayed) + 1);

				panel.emptyComponents();
				panel.addText(textToDisplay, 1920 / 2, 1080 / 2, colorToDisplay);

				panel.revalidate();
				panel.validate();
				panel.updateUI();

				turnTime = System.currentTimeMillis();
			}
		}
	}

	private Color colorToDisplay;
	private String textToDisplay;

	private void selectWord() {
		// This loop is nessassary to avoid that a word with the same color is used twice 
		do {
			int color = (int) (Math.random() * 4) + 0;
			this.colorDisplayed = color;

			switch (color) {
			case 0:
				colorToDisplay = Color.GREEN;
				textToDisplay = getColor(0);
				break;
			case 1:
				colorToDisplay = Color.RED;
				textToDisplay = getColor(1);
				break;
			case 2:
				colorToDisplay = Color.BLUE;
				textToDisplay = getColor(2);
				break;
			case 3:
				colorToDisplay = Color.ORANGE;
				textToDisplay = getColor(3);
				break;
			default:

			}

		} while (colorDisplayed == colorDisplayedPast && colorName == colorNamePast);

		colorDisplayedPast = colorDisplayed;
		colorNamePast = colorName;
	}

	private String getColor(int excludedColor) {
		String[] colors = new String[] { "GREEN", "RED", "BLUE", "YELLOW" };
		int color = -1;

		do {
			color = (int) (Math.random() * 4) + 0;
		} while (color == -1 || color == excludedColor);

		colorName = color;
		return colors[color];
	}

	private String getColorById(int id) {
		switch (id) {
		case 0:
			return "GREEN ";
		case 1:
			return "RED   ";
		case 2:
			return "BLUE  ";
		case 3:
			return "YELLOW";
		default:
			return "ERR";
		}
	}

	private String fillLength(String text, int length) {
		int add = length - text.length();
		String result = text;

		for (int i = 0; i < add; i++) {
			result += " ";
		}

		return result;
	}

	public double round(double value, int places) {
		if (places < 0) {
			throw new IllegalArgumentException();
		}

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);

		return (double) tmp / factor;
	}
}
