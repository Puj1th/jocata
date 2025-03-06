package com.jocata.oms;

import com.jocata.oms.data.um.dao.UserDao;
import com.jocata.oms.data.um.dao.impl.UserDaoImpl;
import com.jocata.oms.datamodel.um.entity.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserDao dao = new UserDaoImpl();
//        User user = new User();
//        user.setFullName("Pujith Paida");
//        user.setEmail("pujith.paida@example.com");
//        user.setPasswordHash("securepassword123");
//        user.setPhone("1234567880");
//        user.setProfilePicture("https://example.com/profile1.jpg");
//        user.setOtpSecret("873569");
//        user.setSmsEnabled(false);
//        user.setIsActive(true);
//
//        dao.createUser(user);

        List<User> users = dao.getAllUsers();
        for(User u:users){
            System.out.println(u.getFullName());
            System.out.println(u.getEmail());
            System.out.println(u.getPasswordHash());
            System.out.println(u.getPhone());
            System.out.println(u.getProfilePicture());
            System.out.println(u.getOtpSecret());
            System.out.println(u.getSmsEnabled());
            System.out.println(u.getIsActive());
            System.out.println(u.getCreatedAt().toString());
            System.out.println(u.getUpdatedAt().toString());
        }
    }
}