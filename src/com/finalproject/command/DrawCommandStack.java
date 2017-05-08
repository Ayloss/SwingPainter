package com.finalproject.command;

import java.awt.image.BufferedImage;
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

	public void executeAll(BufferedImage image) {
		
		for (DrawCommand drawCommand : commandsExecuted) {
			drawCommand.execute(image);
		}
	}

	/**
	 * 重做操作.
	 * 实现方式:弹出撤销的命令到已经执行的命令中.
	 */
	public void redo() {
		
		if(commandsCancelled.size() > 0) {
			commandsExecuted.push(commandsCancelled.pop());
		}
	}

	/**
	 * 撤销操作.
	 * 实现方式:弹出已经执行的命令到撤销的命令中.
	 */
	public void undo() {
		if(commandsExecuted.size() > 0) {
			commandsCancelled.push(commandsExecuted.pop());
		}
	}

	/**
	 * 清空画板.
	 * 实际上是清空栈.
	 */
	public void clean() {
		commandsExecuted.clear();
		commandsCancelled.clear();
	}
	
	public DrawCommand getCurrentCommand() {
		return commandsExecuted.peek();
	}

	public void addCommand(DrawCommand command) {
		commandsExecuted.push(command);
		commandsCancelled.clear();
	}

}
