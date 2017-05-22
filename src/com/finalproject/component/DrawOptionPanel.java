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
import com.finalproject.component.optionPanel.BrushOptionPanel;
import com.finalproject.component.optionPanel.EraserOptionPanel;
import com.finalproject.component.optionPanel.LineOptionPanel;
import com.finalproject.component.optionPanel.OilPaintOptionPanel;
import com.finalproject.component.optionPanel.OptionPanel;
import com.finalproject.component.optionPanel.OvalOptionPanel;
import com.finalproject.component.optionPanel.RectangleOptionPanel;
import com.finalproject.configure.EraserSize;
import com.finalproject.configure.LineThickness;

/**
 * 上方选项工具栏,用来选择对应图案的一些参数
 * @author Yixin
 *
 */
public class DrawOptionPanel extends JPanel {

	private MyCanvas myCanvas;
	
	private HashMap<String, OptionPanel> configures;
	
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
	public DrawOptionPanel(MyCanvas myCanvas) {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		EraserOptionPanel eraserOptionPanel = new EraserOptionPanel(myCanvas);
		LineOptionPanel lineOptionPanel = new LineOptionPanel(myCanvas);
		OvalOptionPanel ovalOptionPanel = new OvalOptionPanel(myCanvas);
		RectangleOptionPanel rectangleOptionPanel = new RectangleOptionPanel(myCanvas);
		OilPaintOptionPanel oilPaintOptionPanel = new OilPaintOptionPanel(myCanvas);
		BrushOptionPanel brushOptionPanel = new BrushOptionPanel(myCanvas);
		
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
	
	public HashMap<String, OptionPanel> getConfigures() {
		return this.configures;
	}
}
