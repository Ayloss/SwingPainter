package com.finalproject.component.optionPanel;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import com.finalproject.component.MyCanvas;
import com.finalproject.configure.EraserSize;

/**
 * ÏðÆ¤²ÁÑ¡ÏîÃæ°å¡£
 * 
 * @author Yixin
 *
 */
public class EraserOptionPanel extends JPanel implements OptionPanel{
	private JLabel label;
	private JComboBox size;
	private JToolBar toolBar;
	private MyCanvas myCanvas;
	
	public EraserOptionPanel(MyCanvas myCanvas) {
		this.myCanvas = myCanvas;
		
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
	
		size.addActionListener(e->{
			myCanvas.setEraserCursor();
		});
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
