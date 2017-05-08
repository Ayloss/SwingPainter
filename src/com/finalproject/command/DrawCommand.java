package com.finalproject.command;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public abstract class DrawCommand {

	public static final int DRAW_LINE = 1;
	public static final int DRAW_OVAL = 2;
	public static final int DRAW_RECTANGLE = 3;
	public static final int PENCIL = 4;
	public static final int ERASER = 5;
	public static final int OilPaint = 6;
	
	protected HashMap<String, Object> configure;
	
	public DrawCommand(HashMap<String, Object> configure) {
		this.configure = configure;
	}

	public abstract void execute(BufferedImage image);
	
	public void setConfigure(HashMap<String, Object> configure) {
		this.configure = configure;
	}
	
}
