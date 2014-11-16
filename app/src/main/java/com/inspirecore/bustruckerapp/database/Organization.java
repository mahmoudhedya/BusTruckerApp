package com.inspirecore.bustruckerapp.database;

/**
 * Created by mahmoudhedya on 11/8/14.
 */
public class Organization {

    /*
    {"UserId":3,
    "UserName":"Azzam",
    "FullName":"Azzam",
    "RoleId":3,
    "RoleName":"Organization",
    "OrganizationId":1,
    "OrganizationName":"Azzam Car"
     */

    private int UserId;
    private String UserName;
    private String FullName;
    private int RoleId;
    private String RoleName;
    private int OrganizationId;
    private String OrganizationName;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public int getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(int organizationId) {
        OrganizationId = organizationId;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }
}
