package com.richardbrenkus;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import javax.swing.border.*;

public class WeekendCalendarRenderer extends JTable implements TableCellRenderer{
	
	boolean isBordered;
	
	public WeekendCalendarRenderer(boolean isBordered) {
        this.isBordered = isBordered;
        this.setOpaque(true); //MUST do this for background to show up.
	}

    public Component getTableCellRendererComponent(JTable table, Object color, boolean isSelected, boolean hasFocus, int row, int column) {
        
//    	Color newColor = (Color)color;
        setBackground(Color.GREEN);      
                
//        if (isBordered) {
////            if (isSelected) {
////                
////                //selectedBorder is a solid border in the color
////                //table.getSelectionBackground().
//                setBorder(Border;
//            } else {
//                
//                //unselectedBorder is a solid border in the color
//                //table.getBackground().
//            	setBorder(unselectedBorder);
//            }
//        }
                return this;
    }
	
}



