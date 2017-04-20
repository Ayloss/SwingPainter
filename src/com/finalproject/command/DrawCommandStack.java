package com.finalproject.command;

import java.awt.Graphics;
import java.util.Stack;

public class DrawCommandStack {

	//已经执行的命令
	private Stack<DrawCommand> commandsExecuted;
	
	//撤回的命令
	private Stack<DrawCommand> commandsCancelled;

	public DrawCommandStack() {
		commandsExecuted = new Stack<>();
		commandsCancelled = new Stack<>();
	}

	public void executeAll(Graphics g) {
		
		for (DrawCommand drawCommand : commandsExecuted) {
			drawCommand.execute(g);
		}
	}

	public void redo() {
		
		if(commandsCancelled.size() > 0) {
			commandsExecuted.push(commandsCancelled.pop());
		}
	}

	public void undo() {
		if(commandsExecuted.size() > 0) {
			commandsCancelled.push(commandsExecuted.pop());
		}
	}

	public DrawCommand getCurrentCommand() {
		return commandsExecuted.peek();
	}

	public void addCommand(DrawCommand command) {
		commandsExecuted.push(command);
		commandsCancelled.clear();
	}

}
