package com.finalproject.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import org.omg.PortableServer.RequestProcessingPolicyValue;

import com.finalproject.util.MyRegExp;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.GridLayout;

/**
 * 选择颜色对话框
 * 
 * @author Yixin
 *
 */
public class ColorSelectDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField gvalue;
	private JTextField bvalue;
	private JTextField rvalue;
	private JPanel sourcePanel;
	private JButton okButton;
	private JButton cancelButton;
	private JPanel colorPreview;

	/**
	 * Create the dialog.
	 */
	public ColorSelectDialog(Frame frame, JPanel sourcePanel) {
		super(frame, true);
		this.sourcePanel = sourcePanel;

		setBounds(100, 100, 255, 313);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		FlowLayout fl_contentPanel = new FlowLayout(FlowLayout.CENTER, 5, 5);
		fl_contentPanel.setAlignOnBaseline(true);
		contentPanel.setLayout(fl_contentPanel);
		JPanel panel = new JPanel();
		contentPanel.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		JPanel rpanel = new JPanel();
		panel.add(rpanel);
		
		JLabel rLabel = new JLabel("R:");
		rpanel.add(rLabel);
		
		rvalue = new JTextField();
		rvalue.setText(sourcePanel.getBackground().getRed() + "");
		rpanel.add(rvalue);
		rvalue.setColumns(10);
		
		JPanel gpanel = new JPanel();
		panel.add(gpanel);
		
		JLabel gLabel = new JLabel("G:");
		gpanel.add(gLabel);
		gvalue = new JTextField();
		gvalue.setText(sourcePanel.getBackground().getGreen() + "");
		gpanel.add(gvalue);
		gvalue.setColumns(10);

		JPanel bpanel = new JPanel();
		panel.add(bpanel);
		JLabel bLabel = new JLabel("B:");
		bpanel.add(bLabel);
		bvalue = new JTextField();
		bvalue.setText(sourcePanel.getBackground().getBlue() + "");
		bpanel.add(bvalue);
		bvalue.setColumns(10);

		bvalue.setDocument(new MyRegExp("^(\\d|[1-9]\\d|[1-2]([0-4]\\d|5[0-5]))$"));
		bvalue.setText("0");
		bvalue.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				String text = bvalue.getText();
				if (text.equals("")) {
					bvalue.setText("0");
				}
				colorPreview.setBackground(new Color(Integer.parseInt(rvalue.getText()),
						Integer.parseInt(gvalue.getText()), Integer.parseInt(bvalue.getText())));
			}

		});
		bvalue.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				bvalue.selectAll();
			}

		});

		gvalue.setDocument(new MyRegExp("^(\\d|[1-9]\\d|[1-2]([0-4]\\d|5[0-5]))$"));
		gvalue.setText("0");
		gvalue.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				String text = gvalue.getText();
				if (text.equals("")) {
					gvalue.setText("0");
				}
				colorPreview.setBackground(new Color(Integer.parseInt(rvalue.getText()),
						Integer.parseInt(gvalue.getText()), Integer.parseInt(bvalue.getText())));
			}

		});
		gvalue.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				gvalue.selectAll();
			}

		});

		rvalue.setDocument(new MyRegExp("^(\\d|[1-9]\\d|[1-2]([0-4]\\d|5[0-5]))$"));
		rvalue.setText("0");
		rvalue.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				String text = rvalue.getText();
				if (text.equals("")) {
					rvalue.setText("0");
				}
				colorPreview.setBackground(new Color(Integer.parseInt(rvalue.getText()),
						Integer.parseInt(gvalue.getText()), Integer.parseInt(bvalue.getText())));
			}

		});
		rvalue.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				rvalue.selectAll();
			}

		});

		colorPreview = new JPanel();
		colorPreview.setPreferredSize(new Dimension(200, 50));
		contentPanel.add(colorPreview);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		okButton = new JButton("\u786E\u5B9A");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sourcePanel.setBackground(colorPreview.getBackground());
				dispose();
			}
		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		colorPreview.setBackground(new Color(Integer.parseInt(rvalue.getText()), Integer.parseInt(gvalue.getText()),
				Integer.parseInt(bvalue.getText())));
	}

}
