package com.finalproject.component.optionPanel;

import java.util.HashMap;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.finalproject.component.MyCanvas;
import com.finalproject.configure.LineThickness;
import javax.swing.JSlider;

public class BrushOptionPanel extends JPanel implements OptionPanel{
	private JToolBar toolBar;
	private JLabel label;
	private JSlider thickness;
	private MyCanvas myCanvas;
	
	public BrushOptionPanel(MyCanvas myCanvas) {
		this.myCanvas = myCanvas;
		
		setName("brushOptionPanel");
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		toolBar = new JToolBar();
		add(toolBar);
		
		label = new JLabel("\u7C97\u7EC6:");
		toolBar.add(label);
		
		thickness = new JSlider();
		thickness.setValue(1);
		thickness.setPaintTicks(true);
		thickness.setPaintLabels(true);
		thickness.setMajorTickSpacing(3);
		thickness.setMaximum(15);
		thickness.setMinimum(3);
		toolBar.add(thickness);
	}

	
	@Override
	public HashMap<String, Object> export() {
		
		HashMap<String, Object> configure = new HashMap<>();
		
		//ÏßÌõ
		int lineThickness = thickness.getValue();
		configure.put("lineThickness", lineThickness);
		
		return configure;

	}


}
