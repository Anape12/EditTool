package org.mainmenu;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import org.main.frame.CreateTxtControlFrame;
import org.main.frame.CreateWordFrame;

public class MenuScreen extends JFrame implements ActionListener, CaretListener{

	
	protected MenuScreen() {
		setTitle("Menu");
		setBounds(10, 10, 700, 500);
		setLayout(new FlowLayout());

		setButtonComponent();
	}
	
	public void openMenuScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void setButtonComponent() {
		// ワード構築
		JButton wordBtn = new JButton("Create Word");
		wordBtn.addActionListener(this);
		wordBtn.setActionCommand("openword");
		add(wordBtn);
		
		// テキスト構築
		JButton txtBtn = new JButton("Create Txt");
		txtBtn.addActionListener(this);
		txtBtn.setActionCommand("opentxt");
		add(txtBtn);
		
		// 閉じる
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(this);
		closeBtn.setActionCommand("srnclose");
		add(closeBtn);
		
	}
	
	@Override
	public void caretUpdate(CaretEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
        // ボタンアクション処理
		String actionCommand = e.getActionCommand();
		
		// メニュー画面ロック
		modalScreen(false);
		
		if(actionCommand.equals("srnclose")) {
			System.exit(0);
		} else if(actionCommand.equals("openword")) {
			// Word構築画面起動
			CreateWordFrame wdframe = new CreateWordFrame();
			wdframe.openMainFrame(this);
		} else if (actionCommand.equals("opentxt")) {
			// either
			CreateTxtControlFrame txtFrame = new CreateTxtControlFrame();
			txtFrame.openMainFrame(this);
		}
	}

	
	// 親画面ロック/解除処理
	private void modalScreen(boolean flg) {
		this.setEnabled(flg);
	}
	
}
