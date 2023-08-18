package org.rtf.sub.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class CreateMenuBar extends JFrame
	implements ActionListener, CaretListener{
	
	ActionListener actionObj = null;
	
	public CreateMenuBar(ActionListener actionObj) {
		this.actionObj = actionObj;
	}
	
	public JMenuBar allMenuBar() {
		
		// メニューバー生成
		JMenuBar menuBar = new JMenuBar();
		menuBar = createFileBar(menuBar);
		menuBar = createEditBar(menuBar);
		
		return menuBar;
	}
	
	private JMenuBar createFileBar(JMenuBar menubar) {
		// FileMenu
		JMenu fileMenu = new JMenu("File");
		menubar.add(fileMenu);
		JMenuItem menuitem1 = new JMenuItem("Open");
		JMenuItem menuitem2 = new JMenuItem("Save");
		JMenuItem menuitem3 = new JMenuItem("Exit");
		fileMenu.add(menuitem1);
		menuitem1.addActionListener(this.actionObj);
		menuitem1.setActionCommand("openItem");
		fileMenu.add(menuitem2);
		menuitem2.addActionListener(this.actionObj);
		menuitem2.setActionCommand("saveItem");
		fileMenu.add(menuitem3);
		menuitem3.addActionListener(this.actionObj);
		menuitem3.setActionCommand("exitItem");
		return menubar;
	}
	
	private JMenuBar createEditBar(JMenuBar menubar) {
		// EditMenu
		JMenu editMenu = new JMenu("Edit");
		menubar.add(editMenu);
		JMenuItem menuitem1 = new JMenuItem("Cut");
		JMenuItem menuitem2 = new JMenuItem("Copy");
		JMenuItem menuitem3 = new JMenuItem("Paste");
		editMenu.add(menuitem1);
		menuitem1.addActionListener(this.actionObj);
		menuitem1.setActionCommand("cutItem");
		editMenu.add(menuitem2);
		menuitem2.addActionListener(this.actionObj);
		menuitem2.setActionCommand("copyItem");
		editMenu.add(menuitem3);
		menuitem3.addActionListener(this.actionObj);
		menuitem3.setActionCommand("pasteItem");
		return menubar;		
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
