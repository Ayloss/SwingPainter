package com.finalproject.command;

import java.awt.Graphics;
import java.util.Stack;

public class DrawCommandStack {

	//�Ѿ�ִ�е�����
	private Stack<DrawCommand> commandsExecuted;
	
	//���ص�����
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

	/**
	 * ��������.
	 * ʵ�ַ�ʽ:��������������Ѿ�ִ�е�������.
	 */
	public void redo() {
		
		if(commandsCancelled.size() > 0) {
			commandsExecuted.push(commandsCancelled.pop());
		}
	}

	/**
	 * ��������.
	 * ʵ�ַ�ʽ:�����Ѿ�ִ�е����������������.
	 */
	public void undo() {
		if(commandsExecuted.size() > 0) {
			commandsCancelled.push(commandsExecuted.pop());
		}
	}

	/**
	 * ��ջ���.
	 * ʵ���������ջ.
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
