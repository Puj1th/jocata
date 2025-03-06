package com.jocata.oms.datamodel.um.response;

public class UserResponse {

    private int userId;
    private String fullName;
    private String passwordHash;
    private String roleName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public UserResponse(int userId, String fullName, String passwordHash) {
        this.userId = userId;
        this.fullName = fullName;
        this.passwordHash = passwordHash;

    }
}
