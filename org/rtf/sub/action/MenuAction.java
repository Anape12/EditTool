package org.rtf.sub.action;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.event.CaretEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.rtf.RTFEditorKit;

import org.io.file.FileOperation;
import org.rtf.sub.bar.ToolBar;

public class MenuAction implements ScreenAction{

	protected boolean flag = false;
	
	protected JTextPane textPane;
	protected DefaultStyledDocument doc;
	protected StyleContext sc;
	
	private JComboBox comboFonts = null;
	
	private JComboBox comboSizes = null;
	
	private JToggleButton toggleB = null;
	
	private JToggleButton toggleI = null;
	
	private JToggleButton toggleU = null;
	
	private JToggleButton toggleS = null;
	
	private JComboBox comboColor = null;
	
	private Component component = null;
	
	private RTFEditorKit rtfEitorKit = null;
	
	public MenuAction(Component component){
		this.component = component;
	}
	
	// アクション情報設定
	public void setActionInfo(boolean flag, JTextPane textPane, DefaultStyledDocument doc, StyleContext sc) {
		this.flag = flag;
		this.textPane = textPane;
		this.doc = doc;
		this.sc = sc;
	}
	
	// ツールバー情報
	public void toolBarInfo(ToolBar toolBar) {
		this.comboFonts = toolBar.getCmbFonts();
		this.comboSizes = toolBar.getCmbSize();
		this.comboColor = toolBar.getCmbColor();
		this.toggleB = toolBar.gettoggleB();
		this.toggleI = toolBar.gettoggleI();
		this.toggleU = toolBar.gettoggleU();
		this.toggleS = toolBar.gettoggleS();
	}
	
	// 編集オブジェクト
	public void setRtfEditor(RTFEditorKit editor) {
		this.rtfEitorKit = editor;
	}
	
	@Override
	public void caretUpdate(CaretEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		flag = true;
		
		flag = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (flag){
		      /* キャレット変更に伴うActionEventの場合はパスする */
		      return;
	    }

	    String actionCommand = e.getActionCommand();

	    if (actionCommand.equals("exitItem")){
	    	JFrame frame = (JFrame)this.component;
	    	frame.dispose();
	    } else if ((actionCommand.equals("newItem")) || (actionCommand.equals("openItem")) || (actionCommand.equals("saveItem"))){
	        FileOperation operation = new FileOperation(this.component, this.rtfEitorKit);
	        operation.fileOperation(actionCommand, this.doc, this.sc, this.textPane);
	    } else if (actionCommand.equals("cutItem")) {
	    	// 切り取り
	    } else if (actionCommand.equals("copyItem")) {
	    	// コピー
	    } else if (actionCommand.equals("pasteItem")) {
	    	// ペースト
	    } else{
	      MutableAttributeSet attr = new SimpleAttributeSet();
	      if (actionCommand.equals("comboFonts")){
	        /* フォント変更 */
	        String fontName = comboFonts.getSelectedItem().toString();
	        StyleConstants.setFontFamily(attr, fontName);
	      }else if (actionCommand.equals("comboSizes")){
	        /* フォントサイズ変更 */
	        int fontSize = 0;
	        try{
	          fontSize = Integer.parseInt(comboSizes.
	            getSelectedItem().toString());
	        }catch (NumberFormatException ex){
	          return;
	        }

	        StyleConstants.setFontSize(attr, fontSize);
	      }else if (actionCommand.equals("toggleB")){
	        /* 強調 */
	        StyleConstants.setBold(attr, toggleB.isSelected());
	      }else if (actionCommand.equals("toggleI")){
	        /* 斜体 */
	        StyleConstants.setItalic(attr, toggleI.isSelected());
	      }else if (actionCommand.equals("toggleU")){
	        /* アンダーライン */
	        StyleConstants.setUnderline(attr, toggleU.isSelected());
	      }else if (actionCommand.equals("toggleS")){
	        /* 取り消し線 */
	        StyleConstants.setStrikeThrough(attr, toggleS.isSelected());
	      }else if (actionCommand.equals("comboColor")){
	        /* 前景色 */
	        int col = comboColor.getSelectedIndex();
	        int b = (col % 2) * 255;
	        int g = ((col / 2) % 2) * 255;
	        int r = ((col / 4) % 2) * 255;

	        StyleConstants.setForeground(attr, new Color(r, g, b));
	      }else{
	        return;
	      }

	      setAttributeSet(attr);
	    }
	    textPane.requestFocusInWindow();
	}
	
	protected void setAttributeSet(AttributeSet attr) {
	    /* 選択している文字のスタイルを変更する */

	    int start = this.textPane.getSelectionStart();
	    int end = this.textPane.getSelectionEnd();

	    doc.setCharacterAttributes(start, end - start, attr, false);
	  }

}
