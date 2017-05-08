package com.finalproject.component;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.border.EtchedBorder;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * 主程序
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
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
					Painter window = new Painter();
					SwingUtilities.updateComponentTreeUI(window.frame);
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
		
		topMenuBar = new TopMenuBar(frame);
		
		leftToolPanel = new LeftToolPanel();
		frame.getContentPane().add(leftToolPanel, BorderLayout.WEST);
		
		bottomToolPanel = new BottomToolPanel(frame);
		frame.getContentPane().add(bottomToolPanel, BorderLayout.SOUTH);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2, 1, 0, 0));
		topPanel.add(topMenuBar);
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		
		DrawOptionPanel drawOptionPanel = new DrawOptionPanel();
		topPanel.add(drawOptionPanel);
		
		drawOptionPanel.setMinimumSize(new Dimension(40, 4));
		
		leftToolPanel.setDrawOptionPanel(drawOptionPanel);
		
		//Canvas的外层容器
		canvasWrapper = new JPanel();
		canvasWrapper.setAutoscrolls(true);
		canvasWrapper.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(canvasWrapper, BorderLayout.CENTER);
		leftToolPanel.addButtonClickEvent();
		topMenuBar.addMenuItemClickEvent();
		canvasWrapper.setLayout(null);
		
		//由于在JScrollPane里边,JPanel会自动拉伸至容器的大小
		//为了让滚动条能够出现，需要用一个JPanel包住我们的绘图Canvas
		JPanel InnerWrapper = new JPanel();
		FlowLayout fl_InnerWrapper = (FlowLayout) InnerWrapper.getLayout();
		fl_InnerWrapper.setVgap(0);
		fl_InnerWrapper.setHgap(0);
		fl_InnerWrapper.setAlignment(FlowLayout.LEFT);
		InnerWrapper.setBounds(0, 0, 10, 10);
		
		myCanvas = new MyCanvas();
		InnerWrapper.add(myCanvas);
		myCanvas.setBounds(2, 2, 400, 300);
		
		JScrollPane scrollPane = new JScrollPane(InnerWrapper);
		scrollPane.setBounds(2, 2, 402, 302);
		canvasWrapper.add(scrollPane);
		
		canvasWrapper.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {

				super.componentResized(e);
				scrollPane.setBounds(0,0,canvasWrapper.getWidth(),canvasWrapper.getHeight());
				canvasWrapper.revalidate();
			}
			
		});
		
		leftToolPanel.setMyCanvas(myCanvas);
		
		topMenuBar.setMyCanvas(myCanvas);
		bottomToolPanel.setMyCanvas(myCanvas);
		
		drawOptionPanel.setMyCanvas(myCanvas);
		myCanvas.setConfigures(drawOptionPanel.getConfigures());
		myCanvas.setLayout(null);
		
	}
}
