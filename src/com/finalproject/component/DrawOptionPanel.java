package com.finalproject.component;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.finalproject.util.MyRegExp;

import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JCheckBox;
import javax.swing.JToolBar;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.InputVerifier;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SpringLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import javax.swing.DefaultComboBoxModel;

import com.finalproject.command.OilPaint;
import com.finalproject.component.configurepanel.BrushOptionPanel;
import com.finalproject.component.configurepanel.EraserOptionPanel;
import com.finalproject.component.configurepanel.ExportConfigure;
import com.finalproject.component.configurepanel.LineOptionPanel;
import com.finalproject.component.configurepanel.OilPaintOptionPanel;
import com.finalproject.component.configurepanel.OvalOptionPanel;
import com.finalproject.component.configurepanel.RectangleOptionPanel;
import com.finalproject.configure.EraserSize;
import com.finalproject.configure.LineThickness;

/**
 * 右侧工具栏,用来选择对应图案的一些参数
 * @author Yixin
 *
 */
public class DrawOptionPanel extends JPanel {

	private MyCanvas myCanvas;
	
	private HashMap<String, ExportConfigure> configures;
	
	private CardLayout cardLayout;
	
	public MyCanvas getMyCanvas() {
		return myCanvas;
	}
	
	public void setMyCanvas(MyCanvas myCanvas) {
		this.myCanvas = myCanvas;
	}
	
	/**
	 * Create the panel.
	 */
	public DrawOptionPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		EraserOptionPanel eraserOptionPanel = new EraserOptionPanel();
		LineOptionPanel lineOptionPanel = new LineOptionPanel();
		OvalOptionPanel ovalOptionPanel = new OvalOptionPanel();
		RectangleOptionPanel rectangleOptionPanel = new RectangleOptionPanel();
		OilPaintOptionPanel oilPaintOptionPanel = new OilPaintOptionPanel();
		BrushOptionPanel brushOptionPanel = new BrushOptionPanel();
		
		add(brushOptionPanel,"brushOptionPanel");
		add(eraserOptionPanel,"eraserOptionPanel");
		add(lineOptionPanel,"lineOptionPanel");
		add(ovalOptionPanel,"ovalOptionPanel");
		add(rectangleOptionPanel,"rectangleOptionPanel");
		add(oilPaintOptionPanel,"oilPaintOptionPanel");
		
		configures = new HashMap<>();
		
		configures.put("brush", brushOptionPanel);
		configures.put("eraser", eraserOptionPanel);
		configures.put("line", lineOptionPanel);
		configures.put("oval", ovalOptionPanel);
		configures.put("rectangle", rectangleOptionPanel);
		configures.put("oilPaint", oilPaintOptionPanel);
		
	}

	/**
	 * 切换为指定的参数选项面板
	 * @param name 参数选项面板的名称
	 */
	public void switchOptionPanel(String name) {
		cardLayout.show(this, name);
	}
	
	public HashMap<String, ExportConfigure> getConfigures() {
		return this.configures;
	}
}
