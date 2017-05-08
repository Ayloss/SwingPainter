package com.finalproject.component.configurepanel;

import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.FlowLayout;

public class OilPaintOptionPanel extends JPanel implements ExportConfigure {
	public OilPaintOptionPanel() {
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
