package org.io.file;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import javax.swing.text.rtf.RTFEditorKit;

public class FileOperation extends JFrame{

	private Component component = null;
	protected RTFEditorKit rtfEditor;
	
	private static final String WORD_PATH = ".rtf";

	
	public FileOperation(Component component, RTFEditorKit rtfEditor) {
		this.component = component;
		this.rtfEditor = rtfEditor;
	}
	
	public void fileOperation(String actionCommand, DefaultStyledDocument doc, StyleContext sc, JTextPane textPane){
	    JFileChooser chooser = new JFileChooser();
	    RtfFilter filter = new RtfFilter();
	    chooser.setFileFilter(filter);

	    if (actionCommand.equals("newItem")){
	      /* 新規ファイル */
	      doc = new DefaultStyledDocument(sc);
	      textPane.setDocument(doc);
	    }else if (actionCommand.equals("openItem")){
	      /* ファイルを開く */
	      doc = new DefaultStyledDocument(sc);

	      if (chooser.showOpenDialog(this.component) != JFileChooser.APPROVE_OPTION){
	        return;
	      }

	      File fChoosen = chooser.getSelectedFile();
	      try{
	        InputStream in = new FileInputStream(fChoosen);
	        rtfEditor.read(in, doc, 0);
	        in.close();
	      }catch(Exception ex){
	        ex.printStackTrace();
	      }

	      textPane.setDocument(doc);
	    }else if (actionCommand.equals("saveItem")){
	      /* ファイルの保存 */
	      if (chooser.showSaveDialog(this.component) != JFileChooser.APPROVE_OPTION){
	        return;
	      }

	      // 保存したファイル名を取得
	      File fChoosen = chooser.getSelectedFile();
	      // ファイル名に拡張子を設定
	      String fullPath = fChoosen.getPath();
	      
	      try{
	        OutputStream out = new FileOutputStream(new File(fullPath.concat(WORD_PATH)));
	        rtfEditor.write(out, doc, 0, doc.getLength());
	        out.close();
	      }catch(Exception ex){
	        ex.printStackTrace();
	      }
	    }else{
	      return;
	    }
	  }
}
