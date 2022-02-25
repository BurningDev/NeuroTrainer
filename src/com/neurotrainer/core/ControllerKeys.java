package com.neurotrainer.core;

import java.util.HashMap;
import java.util.Map;

public class ControllerKeys {
	public static ControllerKeys controllerKeys;
	private Map<String, String> keys;
	
	public static final String KEY_START = "start";
	public static final String KEY_ACTION = "action";
	public static final String KEY_GREEN = "green";
	public static final String KEY_RED = "red";
	public static final String KEY_BLUE = "blue";
	public static final String KEY_YELLOW = "yellow";
	
	public String getKeyByColorCode(int colorCode) {
		switch(colorCode) {
		case 0:
			return getKey(KEY_GREEN);
		case 1:
			return getKey(KEY_RED);
		case 2:
			return getKey(KEY_BLUE);
		case 3:
			return getKey(KEY_YELLOW);
		default:
			return "ERR";
		}
	}
	
	public String getKey(String id) {
		return keys.get(id);
	}
	
	public boolean existsKeyValue(String keyValue) {
		return keys.containsValue(keyValue);
	}
	
	private void initalizeKeys() {
		this.keys = new HashMap<String, String>();
		this.keys.put(KEY_START, "Taste 7");
		this.keys.put(KEY_ACTION, "Taste 0");
		this.keys.put(KEY_GREEN, "Taste 0");
		this.keys.put(KEY_RED, "Taste 1");
		this.keys.put(KEY_BLUE, "Taste 2");
		this.keys.put(KEY_YELLOW , "Taste 3");
	}
	
	public static ControllerKeys getInstance() {
		if(controllerKeys == null) {
			controllerKeys = new ControllerKeys();
			controllerKeys.initalizeKeys();
		}
		
		return controllerKeys;
	}
}
