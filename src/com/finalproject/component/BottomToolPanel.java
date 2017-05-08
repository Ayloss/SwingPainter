package com.finalproject.component;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ColorConvertOp;
import java.util.ArrayList;

import javax.swing.border.BevelBorder;
import java.awt.Dimension;
import javax.swing.JLabel;

/**
 * 底部工具栏
 * 用来选择颜色
 * @author Yixin
 *
 */
public class BottomToolPanel extends JPanel {

	private JFrame parentFrame;
	
	private MyCanvas myCanvas;
	private JPanel lineColorPanel;
	private JPanel currentLineColor;
	private ArrayList<JPanel> lineColors = new ArrayList<>();
	private ArrayList<JPanel> fillColors = new ArrayList<>();
	
	public void setMyCanvas(MyCanvas myCanvas) {
		this.myCanvas = myCanvas;
	}
	/**
	 * Create the panel.
	 */
	public BottomToolPanel(JFrame parentFrame) {
		
		//设置父容器
		this.parentFrame = parentFrame;
		
		//以下是容器和布局
		//大部分代码由WindowBuilder自动生成
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JLabel label1 = new JLabel("\u7EBF\u6761");
		add(label1);
		
		//当前线条颜色容器
		currentLineColor = new JPanel();
		currentLineColor.setPreferredSize(new Dimension(30, 30));
		currentLineColor.setMinimumSize(new Dimension(30, 30));
		currentLineColor.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(128, 128, 128), new Color(128, 128, 128), new Color(64, 64, 64), new Color(64, 64, 64)));
		currentLineColor.setBounds(new Rectangle(0, 0, 100, 100));
		currentLineColor.setBackground(Color.BLACK);
		add(currentLineColor);
		
		//线条颜色选择面板
		lineColorPanel = new JPanel();
		lineColorPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY));
		add(lineColorPanel);
		lineColorPanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		//以下是12中颜色的容器
		JPanel lineColor1 = new JPanel();
		lineColor1.setPreferredSize(new Dimension(20, 20));
		lineColor1.setBackground(Color.WHITE);
		lineColorPanel.add(lineColor1);
		
		JPanel lineColor2 = new JPanel();
		lineColor2.setBackground(Color.LIGHT_GRAY);
		lineColorPanel.add(lineColor2);
		
		JPanel lineColor3 = new JPanel();
		lineColor3.setBackground(Color.GRAY);
		lineColorPanel.add(lineColor3);
		
		JPanel lineColor4 = new JPanel();
		lineColor4.setBackground(Color.BLACK);
		lineColorPanel.add(lineColor4);
		
		JPanel lineColor5 = new JPanel();
		lineColor5.setBackground(Color.RED);
		lineColorPanel.add(lineColor5);
		
		JPanel lineColor6 = new JPanel();
		lineColor6.setBackground(Color.PINK);
		lineColorPanel.add(lineColor6);
		
		JPanel lineColor7 = new JPanel();
		lineColor7.setBackground(Color.ORANGE);
		lineColorPanel.add(lineColor7);
		
		JPanel lineColor8 = new JPanel();
		lineColor8.setBackground(Color.GREEN);
		lineColorPanel.add(lineColor8);
		
		JPanel lineColor9 = new JPanel();
		lineColor9.setBackground(Color.MAGENTA);
		lineColorPanel.add(lineColor9);
		
		JPanel lineColor10 = new JPanel();
		lineColor10.setBackground(Color.CYAN);
		lineColorPanel.add(lineColor10);
		
		JPanel lineColor11 = new JPanel();
		lineColor11.setBackground(Color.BLUE);
		lineColorPanel.add(lineColor11);
		
		JPanel lineColor12 = new JPanel();
		lineColor12.setBackground(Color.YELLOW);
		lineColorPanel.add(lineColor12);

		//将12种颜色容器添加到数组便于管理
		lineColors.add(lineColor1);
		lineColors.add(lineColor2);
		lineColors.add(lineColor3);
		lineColors.add(lineColor4);
		lineColors.add(lineColor5);
		lineColors.add(lineColor6);
		lineColors.add(lineColor7);
		lineColors.add(lineColor8);
		lineColors.add(lineColor9);
		lineColors.add(lineColor10);
		lineColors.add(lineColor11);
		lineColors.add(lineColor12);
		
		JLabel label2 = new JLabel("\u586B\u5145");
		add(label2);
		
		//当前填充颜色容器
		JPanel currentFillColor = new JPanel();
		currentFillColor.setPreferredSize(new Dimension(30, 30));
		currentFillColor.setMinimumSize(new Dimension(30, 30));
		currentFillColor.setBounds(new Rectangle(0, 0, 100, 100));
		currentFillColor.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(128, 128, 128), new Color(128, 128, 128), new Color(64, 64, 64), new Color(64, 64, 64)));
		currentFillColor.setBackground(Color.WHITE);
		add(currentFillColor);
		
		//填充颜色选择面板
		JPanel fillColorPanel = new JPanel();
		fillColorPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY));
		add(fillColorPanel);
		fillColorPanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		//以下是12种填充颜色的容器
		JPanel fillColor1 = new JPanel();
		fillColor1.setPreferredSize(new Dimension(20, 20));
		fillColor1.setBackground(Color.WHITE);
		fillColorPanel.add(fillColor1);
		
		JPanel fillColor2 = new JPanel();
		fillColor2.setBackground(Color.LIGHT_GRAY);
		fillColorPanel.add(fillColor2);
		
		JPanel fillColor3 = new JPanel();
		fillColor3.setBackground(Color.GRAY);
		fillColorPanel.add(fillColor3);
		
		JPanel fillColor4 = new JPanel();
		fillColor4.setBackground(Color.BLACK);
		fillColorPanel.add(fillColor4);
		
		JPanel fillColor5 = new JPanel();
		fillColor5.setBackground(Color.RED);
		fillColorPanel.add(fillColor5);
		
		JPanel fillColor6 = new JPanel();
		fillColor6.setBackground(Color.PINK);
		fillColorPanel.add(fillColor6);
		
		JPanel fillColor7 = new JPanel();
		fillColor7.setBackground(Color.ORANGE);
		fillColorPanel.add(fillColor7);
		
		JPanel fillColor8 = new JPanel();
		fillColor8.setBackground(Color.GREEN);
		fillColorPanel.add(fillColor8);
		
		JPanel fillColor9 = new JPanel();
		fillColor9.setBackground(Color.MAGENTA);
		fillColorPanel.add(fillColor9);
		
		JPanel fillColor10 = new JPanel();
		fillColor10.setBackground(Color.CYAN);
		fillColorPanel.add(fillColor10);
		
		JPanel fillColor11 = new JPanel();
		fillColor11.setBackground(Color.BLUE);
		fillColorPanel.add(fillColor11);
		
		JPanel fillColor12 = new JPanel();
		fillColor12.setBackground(Color.YELLOW);
		fillColorPanel.add(fillColor12);
		
		fillColors.add(fillColor1);
		fillColors.add(fillColor2);
		fillColors.add(fillColor3);
		fillColors.add(fillColor4);
		fillColors.add(fillColor5);
		fillColors.add(fillColor6);
		fillColors.add(fillColor7);
		fillColors.add(fillColor8);
		fillColors.add(fillColor9);
		fillColors.add(fillColor10);
		fillColors.add(fillColor11);
		fillColors.add(fillColor12);
		
		//循环遍历添加容器点击事件
		for (JPanel jPanel : lineColors) {
			
			jPanel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					currentLineColor.setBackground(jPanel.getBackground());
					myCanvas.setLineColor(jPanel.getBackground());
				}
				
			});
		}
		

		for (JPanel jPanel : fillColors) {
			jPanel.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					currentFillColor.setBackground(jPanel.getBackground());
					myCanvas.setFillColor(jPanel.getBackground());
				}
				
			});
		}
		
		//点击当前颜色时，弹出颜色选择器
		currentLineColor.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
//				ColorSelectDialog colorSelectDialog = new ColorSelectDialog(parentFrame,currentLineColor);
//				colorSelectDialog.setVisible(true);
//				myCanvas.setLineColor(currentLineColor.getBackground());
				
				Color newColor = JColorChooser.showDialog(parentFrame, "选择线条颜色", currentLineColor.getBackground());
				if(newColor == null) {
					return;
				}
				currentLineColor.setBackground(newColor);
				myCanvas.setLineColor(newColor);
			}
			
		});
		
		currentFillColor.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
//				ColorSelectDialog colorSelectDialog = new ColorSelectDialog(parentFrame, currentFillColor);
//				colorSelectDialog.setVisible(true);
//				myCanvas.setFillColor(currentFillColor.getBackground());
				
				Color newColor = JColorChooser.showDialog(parentFrame, "选择填充颜色", currentFillColor.getBackground());
				
				if(newColor == null) {
					return;
				}
				
				currentFillColor.setBackground(newColor);
				myCanvas.setFillColor(newColor);
				
			}
			
			
		});
	}
	

}
