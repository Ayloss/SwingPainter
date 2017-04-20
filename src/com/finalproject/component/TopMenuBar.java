package com.finalproject.component;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.EtchedBorder;

/**
 * ¶¥²¿²Ëµ¥À¸
 * @author Yixin
 *
 */
public class TopMenuBar extends JPanel {

	private MyCanvas myCanvas;
	private JMenu fileMenu;
	private JMenuItem newFile;
	private JMenuItem saveFile;
	private JMenu editMenu;
	private JMenuItem undo;
	private JMenuItem redo;
	private JMenuItem resize;
	private JMenu helpMenu;
	private JMenuItem help;
	private JMenuItem about;
	/**
	 * Create the panel.
	 */
	public TopMenuBar() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JMenuBar menuBar = new JMenuBar();
		add(menuBar);
		
		fileMenu = new JMenu("\u6587\u4EF6");
		menuBar.add(fileMenu);
		
		newFile = new JMenuItem("\u65B0\u5EFA");
		fileMenu.add(newFile);
		
		saveFile = new JMenuItem("\u4FDD\u5B58");
		fileMenu.add(saveFile);
		
		editMenu = new JMenu("\u7F16\u8F91");
		menuBar.add(editMenu);
		
		undo = new JMenuItem("\u540E\u9000");
		editMenu.add(undo);
		
		redo = new JMenuItem("\u91CD\u505A");
		editMenu.add(redo);
		
		resize = new JMenuItem("\u66F4\u6539\u753B\u56FE\u677F\u5927\u5C0F");
		editMenu.add(resize);
		
		helpMenu = new JMenu("\u5E2E\u52A9");
		menuBar.add(helpMenu);
		
		help = new JMenuItem("\u5E2E\u52A9");
		helpMenu.add(help);
		
		about = new JMenuItem("\u5173\u4E8E");
		helpMenu.add(about);

	}
	
	public void setMyCanvas(MyCanvas myCanvas) {
		this.myCanvas = myCanvas;
	}
	
	public void addMenuItemClickEvent() {
		undo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				myCanvas.undo();
				
			}
		});
		
		redo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				myCanvas.redo();
			}
		});
	}
	
	

}
