package com.javarush.task.task36.task3608.model;
import java.util.List;
import java.util.ArrayList;
import com.javarush.task.task36.task3608.bean.User;


public class ModelData {
    private List<User> users;
    private User activeUser;
    private boolean displayDeletedUserList;
    
    public boolean isDisplayDeletedUserList() {
        return this.displayDeletedUserList;
    }
    
    public void setDisplayDeletedUserList(boolean displayDeletedUserList) {
        this.displayDeletedUserList = displayDeletedUserList;
    }
    
    public User getActiveUser() {
        return this.activeUser;
    }
    
    public void setActiveUser(User user) {
        this.activeUser = user;
    }
    
    public List<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(List<User> users) {
        this.users = users;
    }
}