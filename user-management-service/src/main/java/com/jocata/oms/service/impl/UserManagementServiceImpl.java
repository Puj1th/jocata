package com.jocata.oms.service.impl;

import com.jocata.oms.data.um.dao.UserDao;
import com.jocata.oms.datamodel.um.bean.UserBean;
import com.jocata.oms.datamodel.um.entity.User;
import com.jocata.oms.datamodel.um.response.UserResponse;
import com.jocata.oms.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    UserDao userDao;
    @Override
    public void createUser(UserBean userBean) {
        User user = new User();
        user.setFullName(userBean.getFullName());
        user.setEmail(userBean.getEmail());
        user.setPasswordHash(userBean.getPasswordHash());
        user.setPhone(userBean.getPhone());
        user.setProfilePicture(userBean.getProfilePicture());
        user.setOtpSecret(userBean.getOtpSecret());

        userDao.createUser(user);
    }

    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public String deleteUser(int userId, boolean flag) {
        return userDao.deleteUser(userId,flag);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public UserResponse getUserIdAndPasswordByEmail(String email) {
        return userDao.getUserIdAndPasswordByEmail(email);
    }
}
