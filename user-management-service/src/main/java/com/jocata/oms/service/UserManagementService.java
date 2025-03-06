package com.jocata.oms.service;

import com.jocata.oms.datamodel.um.bean.UserBean;
import com.jocata.oms.datamodel.um.entity.User;
import com.jocata.oms.datamodel.um.response.UserResponse;

import java.util.List;

public interface UserManagementService {

    public void createUser(UserBean userBean);

    public User getUserById(int userId);

    public List<User> getAllUsers();

    public String deleteUser(int userId, boolean flag);

    public User getUserByEmail(String email);

    public UserResponse getUserIdAndPasswordByEmail(String email);
}
