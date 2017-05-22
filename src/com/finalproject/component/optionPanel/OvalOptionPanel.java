package com.finalproject.component.optionPanel;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.finalproject.component.MyCanvas;
import com.finalproject.configure.LineThickness;
import javax.swing.JSlider;

/**
 * Õ÷‘≤—°œÓ√Ê∞Â°£
 * 
 * @author Yixin
 *
 */
public class OvalOptionPanel extends JPanel implements OptionPanel{
	private JCheckBox filled;
	private JSlider thickness;
	private JToolBar toolBar_1;
	private JLabel label;
	private MyCanvas myCanvas;
	
	public OvalOptionPanel(MyCanvas myCanvas) {
		this.myCanvas = myCanvas;
		
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
		
		thickness = new JSlider();
		thickness.setMajorTickSpacing(4);
		thickness.setPaintTicks(true);
		thickness.setPaintLabels(true);
		thickness.setMaximum(20);
		thickness.setMinimum(4);
		thickness.setValue(4);
		toolBar_1.add(thickness);
	}

	@Override
	public HashMap<String, Object> export() {
		
		HashMap<String, Object> configure = new HashMap<>();
		
		// «∑ÒÃÓ≥‰
		configure.put("filled", filled.isSelected());
		//±ﬂøÚ¥÷œ∏
		int lineThickness = thickness.getValue();
		configure.put("lineThickness", lineThickness);
		
		return configure;
	}


}
