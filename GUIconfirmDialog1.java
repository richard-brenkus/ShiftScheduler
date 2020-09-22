package com.richardbrenkus;

import javax.swing.*;  
import java.awt.*;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.*; 

public class GUIconfirmDialog1 extends JDialog {
		
	public GUIconfirmDialog1(Frame owner) {
		
	}
	
	public GUIconfirmDialog1(Window owner, Dialog.ModalityType DOCUMENT_MODAL) {
		int n = JOptionPane.showConfirmDialog(
				owner,
			    "The last five days of the previous month will be deleted from the archive. Do you want to continue?",
			    "Reset archive",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE);		
	
		if (n == 0) {
			FinishedCalendar.resetArchive();
//			GUImessageWindow message = new GUImessageWindow(GUImainWindow.this, "The request pool has been emptied.");
		}
		else if (n==1) {
			GUIconfirmDialog1.this.dispose();		}			
	}

}