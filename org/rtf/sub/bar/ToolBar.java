package org.rtf.sub.bar;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class ToolBar extends JToolBar implements ActionListener, CaretListener{

	
	private JComboBox comboFonts = null;
	
	private JComboBox comboSizes = null;
	
	private JToggleButton toggleB = null;
	
	private JToggleButton toggleI = null;
	
	private JToggleButton toggleU = null;
	
	private JToggleButton toggleS = null;
	
	private JComboBox comboColor = null;
	
	protected String[] colorHTML = {"#000000", "#0000FF", "#00FF00",
		    "#00FFFF", "#FF0000", "#FF00FF", "#FFFF00", "#FFFFFF"};
	
	public JToolBar initToolbar(ActionListener parent){
	    JToolBar toolBar = new JToolBar();
	    toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));

	    /* フォント一覧の取得 */
	    GraphicsEnvironment ge = 
	      GraphicsEnvironment.getLocalGraphicsEnvironment();
	    String familyName[] = ge.getAvailableFontFamilyNames();

	    /* フォント選択用コンボボックス */
	    comboFonts = new JComboBox(familyName);
	    comboFonts.setMaximumSize(comboFonts.getPreferredSize());
	    comboFonts.addActionListener(parent);
	    comboFonts.setActionCommand("comboFonts");
	    toolBar.add(comboFonts);

	    /* フォントサイズ選択用コンボボックス */
	    comboSizes = new JComboBox(new String[] {"8", "9", "10", 
	      "11", "12", "14", "16", "18", "20", "22", "24", "26", 
	      "28", "36", "48", "72"});
	    comboSizes.setMaximumSize(comboSizes.getPreferredSize());
	    comboSizes.addActionListener(parent);
	    comboSizes.setActionCommand("comboSizes");
	    toolBar.add(comboSizes);

	    toolBar.addSeparator();

	    /* 強調 選択用トグルボタン */
	    toggleB = new JToggleButton("<html><b>B</b></html>");
	    toggleB.setPreferredSize(new Dimension(26, 26));
	    toggleB.addActionListener(parent);
	    toggleB.setActionCommand("toggleB");
	    toolBar.add(toggleB);

	    /* 斜体 選択用トグルボタン */
	    toggleI = new JToggleButton("<html><i>I</i></html>");
	    toolBar.add(toggleI);
	    toggleI.addActionListener(parent);
	    toggleI.setActionCommand("toggleI");
	    toggleI.setPreferredSize(new Dimension(26, 26));

	    /* アンダーライン 選択用トグルボタン */
	    toggleU = new JToggleButton("<html><u>U</u></html>");
	    toolBar.add(toggleU);
	    toggleU.addActionListener(parent);
	    toggleU.setActionCommand("toggleU");
	    toggleU.setPreferredSize(new Dimension(26, 26));

	    /* 取り消し線 選択用トグルボタン */
	    toggleS = new JToggleButton("<html><s>S</s></html>");
	    toolBar.add(toggleS);
	    toggleS.addActionListener(parent);
	    toggleS.setActionCommand("toggleS");
	    toggleS.setPreferredSize(new Dimension(26, 26));

	    toolBar.addSeparator();

	    /* 前景色用コンボボックス */
	    DefaultComboBoxModel colorModel = new DefaultComboBoxModel();
	    for (int i = 0 ; i < 8; i++){
	      /* 色つきのラベルを作成する */
	      StringBuffer sb = new StringBuffer();
	      sb.append("<html><font color=\"");
	      sb.append(colorHTML[i]);
	      sb.append("\">■</font></html>");

	      colorModel.addElement(new String(sb));
	    }
	    comboColor = new JComboBox(colorModel);
	    comboColor.setMaximumSize(comboColor.getPreferredSize());
	    comboColor.addActionListener(parent);
	    comboColor.setActionCommand("comboColor");
	    toolBar.add(comboColor);
	    
	    return toolBar;
	  }

	@Override
	public void caretUpdate(CaretEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	public JComboBox getCmbFonts() {
		return this.comboFonts;
	}

	public JComboBox getCmbSize() {
		return this.comboSizes;
	}

	public JComboBox getCmbColor() {
		return this.comboColor;
	}

	public JToggleButton gettoggleB() {
		return this.toggleB;
	}

	public JToggleButton gettoggleI() {
		return this.toggleI;
	}
	
	public JToggleButton gettoggleU() {
		return this.toggleU;
	}

	public JToggleButton gettoggleS() {
		return this.toggleS;
	}

}
