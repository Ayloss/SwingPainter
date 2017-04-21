package com.finalproject.component.configurepanel;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.finalproject.configure.EraserSize;

public class EraserOptionPanel extends JPanel implements ExportConfigure{
	private JLabel label;
	private JComboBox size;
	private JToolBar toolBar;
	public EraserOptionPanel() {
		setName("eraserOptionPanel");
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		toolBar = new JToolBar();
		add(toolBar);
		
		label = new JLabel("\u5927\u5C0F:");
		toolBar.add(label);
		
		size = new JComboBox();
		toolBar.add(size);
		size.setModel(new DefaultComboBoxModel(EraserSize.values()));
	}

	@Override
	public HashMap<String, Object> export() {
		HashMap<String, Object> configure = new HashMap<>();
		
		//ÏðÆ¤³ß´ç
		int eraserSize = ((EraserSize)(size.getSelectedItem())).getVal();
		configure.put("eraserSize", eraserSize);
		
		return configure;
	}


}
