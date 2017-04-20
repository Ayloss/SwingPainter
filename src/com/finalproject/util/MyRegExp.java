package com.finalproject.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * 自定义document类,用于限制文本框的输入格式
 * @author Yixin
 *
 */
public class MyRegExp extends PlainDocument {
	private Pattern pattern;
	private Matcher m;

	public MyRegExp(String pat) {
		super();
		this.pattern = Pattern.compile(pat);
	}

	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str == null) {
			return;
		}
		String tmp = getText(0, offset).concat(str);
		m = pattern.matcher(tmp);
		if (m.matches())
			super.insertString(offset, str, attr);
	}
}