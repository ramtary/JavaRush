package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.nio.file.Files;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File file) {
        if (file.isDirectory() || file.getName().toLowerCase().endsWith(".htm") || file.getName().toLowerCase().endsWith(".html")) {
            return true;
        }
        return false;
    }
    
    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}