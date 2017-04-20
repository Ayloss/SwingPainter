package com.finalproject.command;

import java.awt.Graphics;

public interface DrawCommand {

	public static final int DRAW_LINE = 1;
	public static final int DRAW_OVAL = 2;
	public static final int DRAW_RECTANGLE = 3;
	public static final int PENCIL = 4;
	public static final int ERASER = 5;
	
	public void execute(Graphics g);
	
}
