package com.jocata.oms.controller;

import com.jocata.oms.datamodel.um.bean.UserBean;
import com.jocata.oms.datamodel.um.entity.User;
import com.jocata.oms.datamodel.um.response.UserResponse;
import com.jocata.oms.request.GenericRequestPayload;
import com.jocata.oms.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @PostMapping("/public/create")
    public ResponseEntity<String> createUser(@RequestBody GenericRequestPayload<UserBean> payload) {
        try {
            UserBean userBean = payload.getData();
            userManagementService.createUser(userBean);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user");
        }
    }

    @GetMapping("/user/getbyid/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        User user = userManagementService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/admin/getall")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userManagementService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/admin/delete")
    public String deleteUser(@RequestParam int userId, @RequestParam boolean flag){
        return userManagementService.deleteUser(userId,flag);
    }

    @GetMapping("/user/getbyemail")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        User user = userManagementService.getUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/admin/getpassandid")
    public ResponseEntity<UserResponse> getPassAndId(@RequestParam String email) {
        UserResponse user = userManagementService.getUserIdAndPasswordByEmail(email);
        return ResponseEntity.ok(user);
    }
}
