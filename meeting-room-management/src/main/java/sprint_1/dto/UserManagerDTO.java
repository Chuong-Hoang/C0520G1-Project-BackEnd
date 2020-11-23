package sprint_1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserManagerDTO {
    private long id;
    private String userName;
    private String password;
    private String fullName;
    private String department;
    private String roleName;

    public UserManagerDTO() {
    }

    public UserManagerDTO(long id, String userName, String fullName, String department, String roleName) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.department = department;
        this.roleName = roleName;
    }

//    public UserManagerDTO(long id, String userName, String password, String fullName, String department, String roleName) {
//        this.id = id;
//        this.userName = userName;
//        this.password = password;
//        this.fullName = fullName;
//        this.department = department;
//        this.roleName = roleName;
//    }

    public UserManagerDTO(String userName, String password, String fullName, String department, String roleName) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.department = department;
        this.roleName = roleName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
