package org.rtf.sub.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.CaretListener;

public interface ScreenAction extends ActionListener, CaretListener{

	public void actionPerformed(ActionEvent e);
}
