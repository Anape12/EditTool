package org.main;

import org.main.frame.CreateWordFrame;
import org.mainmenu.MenuScreen;

public class MainAction extends MenuScreen{

	CreateWordFrame mainFrame = null;
	
	MainAction(){
		// 初期処理（今後拡張）
	}
	
	public static void main(String[] args) {
		
		// すべてのメニュー画面
		MenuScreen menu = new MainAction();
		menu.openMenuScreen();
	}

}
