package sprint_1.dto;

import java.util.List;

public class UserDTO {
    private String token;
    private String type = "Bearer";
    private long id;
    private String userName;
    private String fullName;
    private String department;
    private List<String> role;

    public UserDTO() {
    }

    public UserDTO(String token, long id, String username, String fullName, String department, List<String> role) {
        this.token = token;
        this.id = id;
        this.userName = username;
        this.fullName = fullName;
        this.department = department;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
