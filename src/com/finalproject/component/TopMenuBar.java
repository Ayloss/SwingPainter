package com.finalproject.component;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import javax.swing.border.EtchedBorder;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;

/**
 * 顶部菜单栏
 * 
 * @author Yixin
 *
 */
public class TopMenuBar extends JPanel {

	private JFrame parentFrame;
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
	private JMenuItem clean;

	/**
	 * Create the panel.
	 */
	public TopMenuBar(JFrame parentFrame) {

		this.parentFrame = parentFrame;

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

		clean = new JMenuItem("\u6E05\u7A7A\u753B\u677F");
		editMenu.add(clean);

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
		undo.addActionListener(e -> {
			myCanvas.undo();
		});

		redo.addActionListener(e -> {
			myCanvas.redo();
		});

		resize.addActionListener(e -> {
			int[] parameters = SizeChooser.showSizeChooser(parentFrame, (int) myCanvas.getPreferredSize().getWidth(),
					(int) myCanvas.getPreferredSize().getHeight());

			if (parameters != null) {
				myCanvas.resizeCanvas(parameters[0], parameters[1]);
//				parentFrame.pack();
			}

		});

		clean.addActionListener(e -> {
			// 询问是否清空画板
			if (JOptionPane.showConfirmDialog(getParent().getParent(), "清空后不可恢复.确定要清空画板吗?", "确认",
					JOptionPane.YES_NO_OPTION) == 0) {
				myCanvas.clean();
			}

		});
	}
}
