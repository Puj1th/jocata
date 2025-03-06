package com.jocata.oms.datamodel.um.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

//@Embeddable
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class RolePermissionId implements Serializable {
    private int roleId;
    private int permissionId;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RolePermissionId that = (RolePermissionId) o;
        return getRoleId() == that.getRoleId() && getPermissionId() == that.getPermissionId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoleId(), getPermissionId());
    }
}
