package com.example.nonAuth.user;

import java.util.Arrays;
import java.util.List;

public enum Role {

    CUSTOMER(Arrays.asList(Permission.USER, Permission.ADMIN_USER_ROLE)),

    ADMIN(Arrays.asList(Permission.ADMIN, Permission.USER,Permission.ADMIN_USER_ROLE));

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}