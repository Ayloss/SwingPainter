package com.finalproject.component.optionPanel;

import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.finalproject.component.MyCanvas;

import java.awt.FlowLayout;

/**
 * ����Ͱѡ����塣
 * 
 * @author Yixin
 *
 */
public class OilPaintOptionPanel extends JPanel implements OptionPanel {
	private MyCanvas myCanvas;
	
	public OilPaintOptionPanel(MyCanvas myCanvas) {
		this.myCanvas = myCanvas;
		
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JToolBar toolBar = new JToolBar();
		add(toolBar);
	}

	@Override
	public HashMap<String, Object> export() {
		return null;
	}

}
