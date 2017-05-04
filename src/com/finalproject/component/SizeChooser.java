package com.finalproject.component;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.finalproject.util.MyRegExp;

import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class SizeChooser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private static int[] parameters;
	
	private JTextField width;
	private JTextField height;
	
	/**
	 * 弹出选择大小对话框.
	 * 
	 * @param parent 父级frame
	 * @param preWidth 当前canvas的宽度
	 * @param preHeight 当前canvas的高度
	 * @return 一个二维int数组,第一个值为宽度,第二个值为高度
	 */
	public static int[] showSizeChooser(JFrame parent, int preWidth,int preHeight) {
		
		parameters = null;
		
		SizeChooser sizeChooser = new SizeChooser(parent, preWidth, preHeight);
		
		return parameters;
	}
	
	//限制用户输入的宽度与高度只能在0-9999之间
	private String inputPattern = "0|[1-9]\\d{0,3}";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SizeChooser dialog = new SizeChooser(null,100,100);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SizeChooser(JFrame parent, int width_,int height_) {
		
		super(parent,true);
		
		setTitle("\u66F4\u6539\u753B\u56FE\u677F\u5927\u5C0F");
		setResizable(false);
		setBounds(100, 100, 212, 162);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][left][]", "[24px][]"));
		{
			JLabel lblNewLabel = new JLabel("\u5BBD:");
			contentPanel.add(lblNewLabel, "cell 0 0,alignx left,aligny center");
		}
		{
			width = new JTextField();
			//限制用户输入
			width.setDocument(new MyRegExp(inputPattern));
			//设置为当前画图板宽度
			width.setText(width_ + "");
			//设置点击事件,当用户点击时，自动全选
			width.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					width.selectAll();
				}
				
			});
			contentPanel.add(width, "flowx,cell 1 0,alignx left,aligny top");
			width.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("px");
			contentPanel.add(lblNewLabel_2, "cell 2 0");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("\u9AD8:");
			contentPanel.add(lblNewLabel_1, "cell 0 1,alignx left,aligny center");
		}
		{
			height = new JTextField();
			height.setDocument(new MyRegExp(inputPattern));
			//设置为当前画图板的高度
			height.setText(height_ + "");
			//设置点击事件,当用户点击时，自动全选
			height.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					height.selectAll();
				}
				
			});
			contentPanel.add(height, "cell 1 1,alignx left");
			height.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("px");
			contentPanel.add(lblNewLabel_3, "cell 2 1");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u786E\u5B9A");
				okButton.addActionListener(e->{
					parameters = new int[2];
					parameters[0] = Integer.parseInt(width.getText());
					parameters[1] = Integer.parseInt(height.getText());
					
					dispose();
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u53D6\u6D88");
				cancelButton.addActionListener(e->{
					dispose();
				});
				buttonPane.add(cancelButton);
			}
		}
		
		setVisible(true);
	}
	

}
