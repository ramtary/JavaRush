package com.javarush.task.task32.task3209.listeners;


import com.javarush.task.task32.task3209.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.Component;

public class TextEditMenuListener implements MenuListener {
    private View view;
    
    public TextEditMenuListener(View view) {
        this.view = view;
    }
    
    
    @Override
    public void menuCanceled(MenuEvent e) {
        
    }
        
    @Override
    public void menuDeselected(MenuEvent e) {
        
    }
    
    @Override
    public void menuSelected(MenuEvent menuEvent) {
        JMenu menu = (JMenu) menuEvent.getSource();
        
        Component[] menuItems = menu.getMenuComponents(); 
 
        for (Component comp: menuItems) {
            comp.setEnabled(view.isHtmlTabSelected());
        }
    }
}