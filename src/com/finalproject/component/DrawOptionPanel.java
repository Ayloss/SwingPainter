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

import com.finalproject.component.configurepanel.BrushOptionPanel;
import com.finalproject.component.configurepanel.EraserOptionPanel;
import com.finalproject.component.configurepanel.ExportConfigure;
import com.finalproject.component.configurepanel.LineOptionPanel;
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
		
//		JPanel shapeOption = new JPanel();
//		shapeOption.setVisible(false);
//		add(shapeOption, "name_32829464246098");
//		shapeOption.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
//		
//		JCheckBox fill = new JCheckBox("\u586B\u5145");
//		shapeOption.add(fill);
//		
//		JCheckBox dotted = new JCheckBox("\u865A\u7EBF");
//		shapeOption.add(dotted);
//		
//		JLabel lblNewLabel = new JLabel("\u7EBF\u6761\u7C97\u7EC6:");
//		shapeOption.add(lblNewLabel);
//		
//		JComboBox comboBox = new JComboBox();
//		comboBox.setModel(new DefaultComboBoxModel(LineThickness.values()));
//		comboBox.setMaximumRowCount(15);
//		comboBox.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				LineThickness thickness = (LineThickness) comboBox.getSelectedItem();
//				
//				myCanvas.setLineThickness(thickness.getVal());
//			}
//		});
//		
//		shapeOption.add(comboBox);
//		
//		JLabel label = new JLabel("\u6A61\u76AE\u5927\u5C0F:");
//		shapeOption.add(label);
//		
//		JComboBox comboBox_1 = new JComboBox();
//		comboBox_1.setMaximumRowCount(15);
//		comboBox_1.setModel(new DefaultComboBoxModel(EraserSize.values()));
//		comboBox_1.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				EraserSize eraserSize = (EraserSize) comboBox_1.getSelectedItem();
//				
//				myCanvas.setEraserSize(eraserSize.getVal());
//			}
//		});
//		shapeOption.add(comboBox_1);
//		
//		fill.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				myCanvas.setFilled(fill.isSelected());
//			}
//		});
//		
//		dotted.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				myCanvas.setDotted(dotted.isSelected());
//			}
//		});
		
		BrushOptionPanel brushOptionPanel = new BrushOptionPanel();
		EraserOptionPanel eraserOptionPanel = new EraserOptionPanel();
		LineOptionPanel lineOptionPanel = new LineOptionPanel();
		OvalOptionPanel ovalOptionPanel = new OvalOptionPanel();
		RectangleOptionPanel rectangleOptionPanel = new RectangleOptionPanel();
		
		add(brushOptionPanel,"brushOptionPanel");
		add(eraserOptionPanel,"eraserOptionPanel");
		add(lineOptionPanel,"lineOptionPanel");
		add(ovalOptionPanel,"ovalOptionPanel");
		add(rectangleOptionPanel,"rectangleOptionPanel");
		
		configures = new HashMap<>();
		configures.put("brush", brushOptionPanel);
		configures.put("eraser", eraserOptionPanel);
		configures.put("line", lineOptionPanel);
		configures.put("oval", ovalOptionPanel);
		configures.put("rectangle", rectangleOptionPanel);
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
