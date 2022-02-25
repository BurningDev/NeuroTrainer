package com.neurotrainer.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.neurotrainer.views.TestPanel;
import com.neurotrainer.test.Reaction;
import com.neurotrainer.test.Stroop;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

public class Core {
	private Map<String, PsychologyTest> tests;

	private Scanner scanner;

	private long pastDelta;
	Thread threadUpdate = new Thread(new Runnable() {

		@Override
		public void run() {
			pastDelta = System.currentTimeMillis();
			while (true) {

				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (currentTest != null) {
					currentTest.update(System.currentTimeMillis() - pastDelta);
				}

				pastDelta = System.currentTimeMillis();
			}
		}
	});

	Thread thread = new Thread(new Runnable() {

		@Override
		public void run() {
			while (true) {
				/* Get the available controllers */
				Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
				if (controllers.length == 0) {
					System.out.println("Found no controllers.");
					System.exit(0);
				}

				for (int i = 0; i < controllers.length; i++) {
					/* Remember to poll each one */
					controllers[i].poll();

					/* Get the controllers event queue */
					EventQueue queue = controllers[i].getEventQueue();

					/* Create an event object for the underlying plugin to populate */
					Event event = new Event();

					/* For each object in the queue */
					while (queue.getNextEvent(event)) {

						/*
						 * Create a string buffer and put in it, the controller name, the time stamp of
						 * the event, the name of the component that changed and the new value.
						 * 
						 * Note that the timestamp is a relative thing, not absolute, we can tell what
						 * order events happened in across controllers this way. We can not use it to
						 * tell exactly *when* an event happened just the order.
						 */
						StringBuffer buffer = new StringBuffer(controllers[i].getName());
						buffer.append(" at ");
						buffer.append(event.getNanos()).append(", ");
						Component comp = event.getComponent();
						buffer.append(comp.getName()).append(" changed to ");

						float value = event.getValue();

						if (value == 1.0f) {
							currentTest.keyPressed(comp.getName());
						}

						/*
						 * Check the type of the component and display an appropriate value
						 */
						if (comp.isAnalog()) {
							buffer.append(value);
						} else {
							if (value == 1.0f) {
								buffer.append("On");
							} else {
								buffer.append("Off");
							}
						}
					}
				}

				/*
				 * Sleep for 20 milliseconds, in here only so the example doesn't thrash the
				 * system.
				 */
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	});

	public void start() {
		initializeTests();

		thread.start();
		threadUpdate.start();

		System.out.println("+++============+++");
		System.out.println("   NeuroTrainer");
		System.out.println("+++============+++");
		this.scanner = new Scanner(System.in);

		do {
			System.out.print("Test (Reaction, Stroop): ");
			String cmd = this.scanner.next().toLowerCase();

			this.currentTest = this.tests.get(cmd);
		} while (currentTest == null);

		startTest();
	}

	private void initializeTests() {
		this.tests = new HashMap<>();

		PsychologyTest test = new Stroop();
		this.tests.put(test.getName().toLowerCase(), test);
		test = new Reaction();
		this.tests.put(test.getName().toLowerCase(), test);
	}

	private PsychologyTest currentTest;

	private void startTest() {
		JFrame frame = new JFrame();
		frame.setBounds(0, 0, 1920, 1080);
		frame.setAlwaysOnTop(true);
		frame.setAutoRequestFocus(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		TestPanel panel = new TestPanel();
		panel.setBounds(0, 0, 1920, 1080);

		frame.add(panel);

		frame.setVisible(true);

		currentTest.init(panel);
		currentTest.start();
	}
}
