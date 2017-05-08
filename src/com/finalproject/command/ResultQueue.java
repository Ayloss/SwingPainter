package com.finalproject.command;

import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class ResultQueue {
	
	private int maxResultsNumber = 50;
	
	private LinkedList<BufferedImage> resultsExecuted = new LinkedList<>();
	
	private LinkedList<BufferedImage> resultsCancelled = new LinkedList<>();
	
	 
	public void addResult(BufferedImage image) {
		
		System.out.println(resultsExecuted.size());
		
		resultsCancelled.clear();
		
		resultsExecuted.add(image);
		
		if(resultsExecuted.size() > maxResultsNumber) {
			resultsExecuted.removeFirst();
		}
	}
	
	public void undo() {
		BufferedImage image =  resultsExecuted.removeLast();
		
		resultsCancelled.add(image);
	}
	
	public void redo() {
		BufferedImage image = resultsCancelled.removeLast();
		
		resultsExecuted.add(image);
	}
	
	public void clean() {
		resultsExecuted.clear();
		resultsCancelled.clear();
	}
	
	public BufferedImage getLastResult() {
		return resultsExecuted.getLast();
	}
	
	public boolean isEmpty() {
		return resultsExecuted.isEmpty();
	}
	
	public boolean undoable() {
		return !resultsExecuted.isEmpty();
	}
	
	public boolean redoable() {
		return !resultsCancelled.isEmpty();
	}
	
}
