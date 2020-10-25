package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.Controller;
import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.MenuHelper;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.undo.UndoManager;
import javax.swing.text.html.HTMLDocument;



public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);
    
    
    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }
    
    public Controller getController() {
        return this.controller;
    }
    
    public void setController(Controller controller) {
        this.controller = controller;
    }
    
    public UndoListener getUndoListener() {
        return this.undoListener;
    }
    
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        switch (command) {
            case "Новый":
                controller.createNewDocument();
                break;
            case "Открыть":
                controller.openDocument();
                break;
            case "Сохранить":
                controller.saveDocument();
                break;
            case "Сохранить как...":
                controller.saveDocumentAs();
                break;
            case "Выход":
                controller.exit();
                break;
            case "О программе":
                showAbout();
                break;
        }
    }
    
    public boolean isHtmlTabSelected() {
        return tabbedPane.getSelectedIndex() == 0 ? true : false;
    }
    
    public void selectHtmlTab() {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }
    
    public void init() {
        initGui();
        addWindowListener(new FrameListener(this));
        setVisible(true);
    }
    
    public void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);
        
        getContentPane().add(menuBar, BorderLayout.NORTH);
    } 
    
    public void initEditor() {
        htmlTextPane.setContentType("text/html");
        tabbedPane.addTab("HTML", new JScrollPane(htmlTextPane));
        tabbedPane.addTab("Текст", new JScrollPane(plainTextPane));
        tabbedPane.setPreferredSize(new Dimension(100, 100));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }
    
    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }
    
    public void update() {
        HTMLDocument document = controller.getDocument();
        htmlTextPane.setDocument(document);
    }
    
    public void showAbout() {
        JOptionPane.showMessageDialog(getContentPane(), "And what you want to see there????", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void selectedTabChanged() {
        switch (tabbedPane.getSelectedIndex()) {
            case 0:
                controller.setPlainText(plainTextPane.getText());
                break;
            case 1:
                plainTextPane.setText(controller.getPlainText());
                break;
        }
        resetUndo();
    }
    
    public void undo() {
        try {
            undoManager.undo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }
    
    public void redo() {
        try {
            undoManager.redo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }
    
    public boolean canUndo() {
        return undoManager.canUndo();
    }
    
    public boolean canRedo() {
        return undoManager.canRedo();
    }
    
    public void resetUndo() {
        undoManager.discardAllEdits();
    }
    
    public void exit() {
        controller.exit();
    }
}