package com.richardbrenkus;

import javax.swing.*;  
import java.awt.*;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.*; 

public class GUIconfirmDialog extends JDialog {
		
	public GUIconfirmDialog(Frame owner) {
		
	}
	
	public GUIconfirmDialog(Window owner, Dialog.ModalityType DOCUMENT_MODAL) {
		int n = JOptionPane.showConfirmDialog(
				owner,
			    "All requests will be deleted. Do you want to continue?",
			    "Reset request pool",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE);		
	
		if (n == 0) {
			RequestPool.resetRequestPool();
//			GUImessageWindow message = new GUImessageWindow(GUImainWindow.this, "The request pool has been emptied.");
		}
		else if (n==1) {
			GUIconfirmDialog.this.dispose();		}			
	}

}