package com.javarush.task.task30.task3008.client;

import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class ClientGuiModel {
    private final Set<String> allUserNames = new HashSet<>();
    private String newMessage;
    
    
    public Set<String> getAllUserNames() {
        return Collections.unmodifiableSet(this.allUserNames);
    }
    
    public String getNewMessage() {
        return this.newMessage;
    }
    
    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }
    
    public void addUser(String newUserName) {
        this.allUserNames.add(newUserName);
    }
    
    public void deleteUser(String userName) {
        this.allUserNames.remove(userName);
    }
    
}