package com.jocata.oms.data.um.dao;

import com.jocata.oms.datamodel.um.entity.User;
import com.jocata.oms.datamodel.um.response.UserResponse;

import java.util.List;

public interface UserDao {

    public void createUser(User user);

    public User getUserById(int userId);

    public List<User> getAllUsers();

    public String deleteUser(int userId, boolean flag);

    public User getUserByEmail(String email);

    public UserResponse getUserIdAndPasswordByEmail(String email);
}
