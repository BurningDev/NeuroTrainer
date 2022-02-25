package com.neurotrainer.test;

import com.neurotrainer.core.PsychologyTest;
import com.neurotrainer.views.Form;
import com.neurotrainer.views.TestPanel;

public class Reaction implements PsychologyTest {

	// **************************
	// ******** SETTINGS ********
	// **************************
	public static final int MAX_AMOUNT = 30;

	private TestPanel panel;

	private int timeUntilShowForm = 3000;
	private int timeUntilVanish = 1000;
	private boolean wait = false;

	private int displayedForm = -1;

	private short statFalseAlarm;
	private short statHit;
	private short statCorrectRejection;
	private short statMiss;

	private short amount;

	private long startTimeTurn;
	private long reactionTimeTotal;

	private boolean instruction = true;

	@Override
	public void update(long delta) {
		if (instruction == true) {
			return;
		}

		if (displayedForm == -1) {
			timeUntilShowForm -= delta;
		}

		if (displayedForm != -1) {
			timeUntilVanish -= delta;
		}

		if (timeUntilVanish <= 0 && displayedForm != -1) {
			if (displayedForm == Form.CIRCLE) {
				statCorrectRejection++;
			} else {
				statMiss++;
			}

			displayedForm = -1;
			timeUntilVanish = 1000;

			panel.emptyComponents();

			panel.revalidate();
			panel.validate();
			panel.updateUI();

			wait = false;
			timeUntilShowForm = (int) (Math.random() * 2500) + 1000;
		}

		if (timeUntilShowForm <= 0 && wait == false) {
			amount++;

			if (amount > MAX_AMOUNT) {
				amount--;

				StringBuilder result = new StringBuilder();
				result.append("Amount: " + (this.amount) + "\n");
				result.append("HIT  |FA   |COR  |MISS " + "\n");
				result.append("-----+-----+-----+-----" + "\n");

				result.append(fillLength(statHit, 5) + " ");
				result.append(fillLength(statFalseAlarm, 5) + " ");
				result.append(fillLength(statCorrectRejection, 5) + " ");
				result.append(fillLength(statMiss, 5) + "\n");

				result.append(fillLength(round((double) statHit / (double) amount, 2), 5) + " ");
				result.append(fillLength(round((double) statFalseAlarm / (double) amount, 2), 5) + " ");
				result.append(fillLength(round((double) statCorrectRejection / (double) amount, 2), 5) + " ");
				result.append(fillLength(round((double) statMiss / (double) amount, 2), 5) + "\n");

				result.append("Avg. Reaction Time (Hit): " + round(reactionTimeTotal / amount, 2) + " ms\n");
				System.out.println(result.toString());
				System.exit(0);
				return;
			}

			wait = true;

			int form = (int) (Math.random() * 2) + 1;
			if (form == 2) {
				displayedForm = Form.CIRCLE;
			} else {
				displayedForm = Form.RECT;
			}

			panel.addForm(displayedForm, 1920 / 2 - 50, 1080 / 2 - 50, 100, 100, true);

			panel.revalidate();
			panel.validate();
			panel.updateUI();

			startTimeTurn = System.currentTimeMillis();
		}
	}

	@Override
	public void init(TestPanel panel) {
		this.panel = panel;
		this.panel.addText("Press 'Start' to begin", 1920 / 2 - 300, 1080 / 2 - 10);
	}

	@Override
	public void start() {

	}

	@Override
	public String getInstruction() {
		return null;
	}

	@Override
	public String getName() {
		return "Reaction";
	}

	@Override
	public String getId() {
		return null;
	}

	@Override
	public void keyPressed(String key) {
		if (!key.startsWith("Taste")) {
			return;
		}

		// Activation of instruction
		if (instruction == true && key.equals("Taste 7")) {
			instruction = false;
		} else if (instruction == true) {
			return;
		}

		if (displayedForm == Form.RECT) {
			statHit++;
			reactionTimeTotal += (System.currentTimeMillis() - startTimeTurn);
		} else {
			statFalseAlarm++;
		}

		displayedForm = -1;

		panel.emptyComponents();

		panel.revalidate();
		panel.validate();
		panel.updateUI();

		wait = false;
		timeUntilShowForm = (int) (Math.random() * 3000) + 500;

		timeUntilVanish = 2000;
	}

	private String fillLength(Object textObject, int length) {
		String text = String.valueOf(textObject);

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
