package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;

import javax.swing.event.MenuListener;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;

public class UndoMenuListener implements MenuListener {
    private View view;
    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;
    
    public UndoMenuListener(View view, JMenuItem undoMenuItem, JMenuItem redoMenuItem) {
        this.view = view;
        this.undoMenuItem = undoMenuItem;
        this.redoMenuItem = redoMenuItem;
    }
    
    @Override
    public void menuCanceled(MenuEvent e) {
        
    }
    
    @Override
    public void menuDeselected(MenuEvent e) {
        
    }
    
    @Override
    public void menuSelected(MenuEvent menuEvent) {
        if (view.canUndo()) 
            this.undoMenuItem.setEnabled(true);    
        else 
            this.undoMenuItem.setEnabled(false);    
            
        if (view.canRedo()) 
            this.redoMenuItem.setEnabled(true);    
        else 
            this.redoMenuItem.setEnabled(false);            
    }

}