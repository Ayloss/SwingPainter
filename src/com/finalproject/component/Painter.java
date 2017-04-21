package com.finalproject.component;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.EtchedBorder;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * Ö÷³ÌÐò
 * @author Yixin
 *
 */
public class Painter {

	private JFrame frame;
	private TopMenuBar topMenuBar;
	private LeftToolPanel leftToolPanel;
	private BottomToolPanel bottomToolPanel;
	private JPanel canvasWrapper;
	private MyCanvas myCanvas;
	private JPanel panel;
	private JPanel TopPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Painter window = new Painter();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Painter() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 865, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		topMenuBar = new TopMenuBar();
		
		leftToolPanel = new LeftToolPanel();
		frame.getContentPane().add(leftToolPanel, BorderLayout.WEST);
		
		bottomToolPanel = new BottomToolPanel();
		frame.getContentPane().add(bottomToolPanel, BorderLayout.SOUTH);
		
		canvasWrapper = new JPanel();
		canvasWrapper.setAutoscrolls(true);
		canvasWrapper.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(canvasWrapper, BorderLayout.CENTER);
		canvasWrapper.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		myCanvas = new MyCanvas();
		canvasWrapper.add(myCanvas);
		
		leftToolPanel.setMyCanvas(myCanvas);
		leftToolPanel.addButtonClickEvent();
		
		topMenuBar.setMyCanvas(myCanvas);
		topMenuBar.addMenuItemClickEvent();
		
		bottomToolPanel.setParentFrame(frame);
		bottomToolPanel.setMyCanvas(myCanvas);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2, 1, 0, 0));
		topPanel.add(topMenuBar);
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		
		DrawOptionPanel drawOptionPanel = new DrawOptionPanel();
		topPanel.add(drawOptionPanel);
		
		drawOptionPanel.setMinimumSize(new Dimension(40, 4));
		
		drawOptionPanel.setMyCanvas(myCanvas);
		
		leftToolPanel.setDrawOptionPanel(drawOptionPanel);
		myCanvas.setConfigures(drawOptionPanel.getConfigures());
		
	}

}
