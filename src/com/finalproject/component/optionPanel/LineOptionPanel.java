package com.finalproject.component.configurepanel;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JToolBar;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import com.finalproject.configure.LineThickness;
import com.finalproject.shape.Line;

import net.miginfocom.layout.AC;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LineOptionPanel extends JPanel implements ExportConfigure{
	private JToolBar toolBar;
	private JCheckBox dotted;
	private JLabel label1;
	private JComboBox style;
	private JToolBar toolBar_1;
	private JComboBox thickness;
	private JLabel label2;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public LineOptionPanel() {
		setName("lineOptionPanel");
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		toolBar = new JToolBar();
		add(toolBar);
		
		dotted = new JCheckBox("\u865A\u7EBF");
		toolBar.add(dotted);
		
		label1 = new JLabel("\u6837\u5F0F:");
		toolBar.add(label1);
		
		style = new JComboBox();
		style.setEnabled(false);
		toolBar.add(style);
		
		toolBar_1 = new JToolBar();
		add(toolBar_1);
		
		label2 = new JLabel("\u7C97\u7EC6:");
		toolBar_1.add(label2);
		
		thickness = new JComboBox();
		thickness.setModel(new DefaultComboBoxModel(LineThickness.values()));
		toolBar_1.add(thickness);
		
		dotted.addActionListener((e)->{
			if(dotted.isSelected()) {
				style.setEnabled(true);;
			} else {
				style.setEnabled(false);
			}
		});
	}

	@Override
	public HashMap<String, Object> export() {
		HashMap<String, Object> configure = new HashMap<>();
		
		//是否虚线
		configure.put("dotted", dotted.isSelected());
		//虚线样式
		configure.put("style", style.getSelectedItem());
		
		//线条粗细
		int lineThickness = ((LineThickness) (thickness.getSelectedItem())).getVal();
		configure.put("lineThickness", lineThickness);
		
		return configure;
	}

}
