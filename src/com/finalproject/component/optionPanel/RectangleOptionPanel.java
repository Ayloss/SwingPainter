package com.finalproject.component.optionPanel;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.finalproject.component.MyCanvas;
import com.finalproject.configure.LineThickness;
import com.finalproject.configure.RectangleRoundedRadius;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSlider;

public class RectangleOptionPanel extends JPanel implements OptionPanel {
	private JCheckBox filled;
	private JComboBox thickness;
	private JToolBar toolBar_1;
	private JLabel label;
	private JToolBar toolBar_2;
	private JCheckBox rounded;
	private JSlider radius;
	private JLabel label2;
	private MyCanvas myCanvas;
	
	public RectangleOptionPanel(MyCanvas myCanvas) {
		this.myCanvas = myCanvas;
		
		setName("rectangleOptionPanel");
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		JToolBar toolBar = new JToolBar();
		add(toolBar);

		filled = new JCheckBox("\u586B\u5145");
		toolBar.add(filled);

		toolBar_1 = new JToolBar();
		add(toolBar_1);

		label = new JLabel("\u8FB9\u6846\u7C97\u7EC6:");
		toolBar_1.add(label);

		thickness = new JComboBox();
		toolBar_1.add(thickness);
		thickness.setModel(new DefaultComboBoxModel(LineThickness.values()));
		
		toolBar_2 = new JToolBar();
		add(toolBar_2);
		
		rounded = new JCheckBox("\u5706\u89D2");
		toolBar_2.add(rounded);
		
		label2 = new JLabel("\u5706\u89D2\u5C3A\u5BF8");
		toolBar_2.add(label2);
		
		radius = new JSlider();
		radius.setMajorTickSpacing(5);
		radius.setPaintLabels(true);
		radius.setPaintTicks(true);
		radius.setValue(5);
		radius.setMaximum(50);
		radius.setMinimum(5);
		radius.setEnabled(false);
		toolBar_2.add(radius);
		
		//Ñ¡ÖĞÊ±£¬ÆôÓÃÏÂÀ­¿ò
		rounded.addActionListener((e)->{
			if(rounded.isSelected()) {
				radius.setEnabled(true);
			} else {
				radius.setEnabled(false);
			}
		});
	}

	@Override
	public HashMap<String, Object> export() {

		HashMap<String, Object> configure = new HashMap<>();

		// ÊÇ·ñÌî³ä
		configure.put("filled", filled.isSelected());
		// ±ß¿ò´ÖÏ¸
		int lineThickness = ((LineThickness) (thickness.getSelectedItem())).getVal();
		configure.put("lineThickness", lineThickness);
		//ÊÇ·ñÔ²½Ç
		configure.put("rounded", rounded.isSelected());
		//Ô²½Ç³ß´ç
		int  roundedRadius = radius.getValue();
		configure.put("radius", roundedRadius);
		
		return configure;
	}

}
