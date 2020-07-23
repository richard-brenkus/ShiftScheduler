package com.richardbrenkus;

import javax.swing.*;  
import java.awt.*;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.*; 

public class GUIexitDialog extends JDialog {
		
	public GUIexitDialog(Frame owner) {
		
	}
	
	public GUIexitDialog(Window owner, Dialog.ModalityType DOCUMENT_MODAL) {
		int n = JOptionPane.showConfirmDialog(
				owner,
			    "Do you want to exit the program?",
			    "Exit program",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE);		
	
		if (n == 0) {
			System.exit(0);
		}
		else if (n==1) {
			GUIexitDialog.this.dispose();		}			
	}

}