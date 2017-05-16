package com.finalproject.component.optionPanel;

import java.util.HashMap;

/**
 * 导出某一绘图功能的配置参数.例如线条粗细,虚线等.
 * 菜单栏下边的工具栏均实现了该接口.
 * @author Yixin
 *
 */
public interface OptionPanel {

	/**
	 * 导出配置.配置是用一个HashMap存储,以键值对的方式存放.
	 * @return
	 */
	public HashMap<String,Object> export();
}
