package com.javarush.task.task36.task3608.model;
import java.util.List;
import java.util.ArrayList;
import com.javarush.task.task36.task3608.bean.User;


public class FakeModel implements Model {
    private ModelData modelData = new ModelData();

    @Override
    public void deleteUserById(long id) {
        throw new UnsupportedOperationException();
    }

    public ModelData getModelData() {
        return this.modelData;
    }
    
    public void loadUsers() {
        List<User> users = new ArrayList<>();
        
        users.add(new User ("A", 1, 1));
        users.add(new User ("B", 2, 1));
        
        modelData.setUsers(users);
    }

    @Override
    public void changeUserData(String name, long id, int level) {
        throw new UnsupportedOperationException();
    }

    public void loadUserById(long userId) {
        throw new UnsupportedOperationException();
    }
    
    public void loadDeletedUsers() {
        throw new UnsupportedOperationException();
    }
}