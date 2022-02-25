package com.neurotrainer.core;

import com.neurotrainer.views.TestPanel;

public interface PsychologyTest {
	
	public void update(long delta);
	
	public void init(TestPanel panel);
	
	public void start();
	
	public String getInstruction();
	
	public String getName();
	
	public String getId();
	
	public void keyPressed(String key);
}
