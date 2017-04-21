package com.finalproject.component.configurepanel;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.finalproject.configure.LineThickness;

public class OvalOptionPanel extends JPanel implements ExportConfigure{
	private JCheckBox filled;
	private JComboBox thickness;
	private JToolBar toolBar_1;
	private JLabel label;
	public OvalOptionPanel() {
		setName("ovalOptionPanel");
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
	}

	@Override
	public HashMap<String, Object> export() {
		
		HashMap<String, Object> configure = new HashMap<>();
		
		//ÊÇ·ñÌî³ä
		configure.put("filled", filled.isSelected());
		//±ß¿ò´ÖÏ¸
		int lineThickness = ((LineThickness)(thickness.getSelectedItem())).getVal();
		configure.put("lineThickness", lineThickness);
		
		return configure;
	}


}
