package com.finalproject.component.configurepanel;

import java.util.HashMap;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.finalproject.configure.LineThickness;

public class BrushOptionPanel extends JPanel implements ExportConfigure{
	private JToolBar toolBar;
	private JLabel label;
	private JComboBox thickness;
	public BrushOptionPanel() {
		setName("brushOptionPanel");
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		toolBar = new JToolBar();
		add(toolBar);
		
		label = new JLabel("\u7C97\u7EC6:");
		toolBar.add(label);
		
		thickness = new JComboBox();
		toolBar.add(thickness);
		thickness.setModel(new DefaultComboBoxModel(LineThickness.values()));
	}

	
	@Override
	public HashMap<String, Object> export() {
		
		HashMap<String, Object> configure = new HashMap<>();
		
		//ÏßÌõ
		int lineThickness = ((LineThickness) (thickness.getSelectedItem())).getVal();
		configure.put("lineThickness", lineThickness);
		
		return configure;

	}


}
