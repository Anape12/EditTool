package org.main.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import javax.swing.text.rtf.RTFEditorKit;

import org.rtf.sub.action.MenuAction;
import org.rtf.sub.bar.ToolBar;
import org.rtf.sub.frame.CreateMenuBar;
import org.window.WindowAction;

public class CreateTxtControlFrame extends JFrame
implements ActionListener, CaretListener, EventListener{
	
	protected JTextPane textPane;
	protected DefaultStyledDocument doc;
	protected StyleContext sc;
	
	protected String currentFontName = "";
	protected int currentFontSize = 0;
	protected boolean flag = false;
	
	// ボタンアクションオブジェクト
	private MenuAction action = null;
	
	// ツールバー情報
	private ToolBar toolBar = null;
	
	// 親画面オブジェクト
	private JFrame parentObj = null;
	
	private WindowAction windowAction = null;
	
	protected RTFEditorKit rtfEditor;

	public CreateTxtControlFrame(){
		setTitle("INFO_TOOL");
		setBounds(10, 10, 700, 500);
		
		textPane = new JTextPane();
	    JScrollPane scroll = new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    getContentPane().add(scroll, BorderLayout.CENTER);
	    
	    sc = new StyleContext();
	    doc = new DefaultStyledDocument(sc);

	    textPane.setDocument(doc);
	    textPane.addCaretListener(this);

	    /* ファイル操作用RTFEditorKit */
	    rtfEditor = new RTFEditorKit();

	    // メニューバーの構築
	    CreateMenuBar createMenuBar = new CreateMenuBar(this);
	    JMenuBar menuBar = createMenuBar.allMenuBar();
	    setJMenuBar(menuBar);

	    // ツールバー構築
//	    this.toolBar = new ToolBar();
//	    JToolBar jtoolBar = this.toolBar.initToolbar(this);
//	    getContentPane().add(jtoolBar, BorderLayout.NORTH);

	    /* 初期文書の読み込み */
	    initDocument();
	    
	    // アクション処理情報
	    this.action = new MenuAction(this);
	    this.action.setRtfEditor(rtfEditor);
	    
	}
	
	public void openMainFrame(JFrame frameObj) {
		// 親画面オブジェクト設定
		this.parentObj = frameObj;
		windowAction = new WindowAction(this.parentObj);
		addWindowListener(windowAction);
		// 可視化
		setVisible(true);
		
	}
		
	private void initDocument(){
	    StringBuffer sb = new StringBuffer();
	    sb.append("テキストサンプル。\n");
	    sb.append("任意のテキストを入力してください。");

	    try{
	      /* 文書を挿入する */
	      doc.insertString(0, new String(sb), 
	        sc.getStyle(StyleContext.DEFAULT_STYLE));
	    }catch (BadLocationException ble){
	      System.err.println("初期文書の読み込みに失敗しました。");
	    }
	  }

	@Override
	public void caretUpdate(CaretEvent e) {
		if(this.action != null) {
			this.action.caretUpdate(e);
		} else {
			// non action
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.action != null) {
			this.action.setActionInfo(this.flag, this.textPane, this.doc, this.sc);
			this.action.toolBarInfo(this.toolBar);
			this.action.actionPerformed(e);
		}
	}
}
