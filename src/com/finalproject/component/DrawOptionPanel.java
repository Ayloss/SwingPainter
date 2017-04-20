package com.finalproject.component;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.finalproject.util.MyRegExp;

import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JCheckBox;
import javax.swing.JToolBar;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.InputVerifier;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SpringLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import javax.swing.DefaultComboBoxModel;

import com.finalproject.configure.EraserSize;
import com.finalproject.configure.LineThickness;
import com.finalproject.configure.RectangleRoundedSize;

/**
 * 右侧工具栏,用来选择对应图案的一些参数
 * @author Yixin
 *
 */
public class DrawOptionPanel extends JPanel {

	private MyCanvas myCanvas;
	
	public MyCanvas getMyCanvas() {
		return myCanvas;
	}
	
	public void setMyCanvas(MyCanvas myCanvas) {
		this.myCanvas = myCanvas;
	}
	/**
	 * Create the panel.
	 */
	public DrawOptionPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new CardLayout(0, 0));
		
		JPanel shapeOption = new JPanel();
		add(shapeOption, "name_32829464246098");
		shapeOption.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JCheckBox fill = new JCheckBox("\u586B\u5145");
		shapeOption.add(fill);
		
		JCheckBox dotted = new JCheckBox("\u865A\u7EBF");
		shapeOption.add(dotted);
		
		JLabel lblNewLabel = new JLabel("\u7EBF\u6761\u7C97\u7EC6:");
		shapeOption.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(LineThickness.values()));
		comboBox.setMaximumRowCount(15);
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LineThickness thickness = (LineThickness) comboBox.getSelectedItem();
				
				myCanvas.setLineThickness(thickness.getVal());
			}
		});
		
		shapeOption.add(comboBox);
		
		JLabel label = new JLabel("\u6A61\u76AE\u5927\u5C0F:");
		shapeOption.add(label);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setMaximumRowCount(15);
		comboBox_1.setModel(new DefaultComboBoxModel(EraserSize.values()));
		comboBox_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EraserSize eraserSize = (EraserSize) comboBox_1.getSelectedItem();
				
				myCanvas.setEraserSize(eraserSize.getVal());
			}
		});
		shapeOption.add(comboBox_1);
		
		JPanel eraserOption = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) eraserOption.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(eraserOption, "name_14646263124062");
		
		JToolBar toolBar_7 = new JToolBar();
		eraserOption.add(toolBar_7);
		
		JLabel label_1 = new JLabel("\u6A61\u76AE\u5927\u5C0F:");
		toolBar_7.add(label_1);
		
		JComboBox comboBox_2 = new JComboBox();
		toolBar_7.add(comboBox_2);
		comboBox_2.setModel(new DefaultComboBoxModel(EraserSize.values()));
		comboBox_2.setMaximumRowCount(15);
		
		
		comboBox_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EraserSize eraserSize = (EraserSize) comboBox_1.getSelectedItem();
				
				myCanvas.setEraserSize(eraserSize.getVal());
			}
		});
		
		JPanel rectangleOption = new JPanel();
		FlowLayout flowLayout = (FlowLayout) rectangleOption.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(rectangleOption, "name_15137939341434");
		
		JToolBar toolBar_4 = new JToolBar();
		rectangleOption.add(toolBar_4);
		
		JCheckBox checkBox = new JCheckBox("\u586B\u5145");
		toolBar_4.add(checkBox);
		
		JToolBar toolBar_5 = new JToolBar();
		rectangleOption.add(toolBar_5);
		
		JLabel label_2 = new JLabel("\u7EBF\u6761\u7C97\u7EC6");
		toolBar_5.add(label_2);
		
		JComboBox comboBox_3 = new JComboBox();
		toolBar_5.add(comboBox_3);
		comboBox_3.setModel(new DefaultComboBoxModel(LineThickness.values()));
		
		JToolBar toolBar_6 = new JToolBar();
		rectangleOption.add(toolBar_6);
		
		JCheckBox checkBox_1 = new JCheckBox("\u5706\u89D2");
		toolBar_6.add(checkBox_1);
		
		JLabel lblNewLabel_1 = new JLabel("\u5706\u89D2\u5C3A\u5BF8");
		toolBar_6.add(lblNewLabel_1);
		
		JComboBox comboBox_5 = new JComboBox();
		toolBar_6.add(comboBox_5);
		comboBox_5.setModel(new DefaultComboBoxModel(RectangleRoundedSize.values()));
		
		JPanel ovalOption = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) ovalOption.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		add(ovalOption, "name_15354003180611");
		
		JToolBar toolBar_3 = new JToolBar();
		ovalOption.add(toolBar_3);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\u586B\u5145");
		toolBar_3.add(chckbxNewCheckBox);
		
		JToolBar toolBar_2 = new JToolBar();
		ovalOption.add(toolBar_2);
		
		JLabel label_3 = new JLabel("\u7EBF\u6761\u7C97\u7EC6:");
		toolBar_2.add(label_3);
		
		JComboBox comboBox_4 = new JComboBox();
		toolBar_2.add(comboBox_4);
		comboBox_4.setModel(new DefaultComboBoxModel(LineThickness.values()));
		
		JPanel lineOption = new JPanel();
		add(lineOption, "name_16719032098823");
		
		JToolBar toolBar = new JToolBar();
		lineOption.add(toolBar);
		
		JCheckBox checkBox_2 = new JCheckBox("\u865A\u7EBF");
		toolBar.add(checkBox_2);
		
		JLabel lblNewLabel_2 = new JLabel("\u865A\u7EBF\u6837\u5F0F");
		toolBar.add(lblNewLabel_2);
		
		JComboBox comboBox_7 = new JComboBox();
		toolBar.add(comboBox_7);
		
		JToolBar toolBar_1 = new JToolBar();
		lineOption.add(toolBar_1);
		
		JLabel label_4 = new JLabel("\u7EBF\u6761\u7C97\u7EC6");
		toolBar_1.add(label_4);
		
		JComboBox comboBox_6 = new JComboBox();
		toolBar_1.add(comboBox_6);
		comboBox_6.setModel(new DefaultComboBoxModel(LineThickness.values()));
		
		fill.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				myCanvas.setFilled(fill.isSelected());
			}
		});
		
		dotted.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				myCanvas.setDotted(dotted.isSelected());
			}
		});
	}

}
