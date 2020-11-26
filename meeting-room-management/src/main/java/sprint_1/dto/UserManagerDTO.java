package sprint_1.dto;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
/**
 * UserManagerDTO
 * <p>
 * Version 1.0
 * <p>
 * Date: 24-11-2020
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 22-11-2020         HienTH           CRUD
 */
public class UserManagerDTO {
    public interface checkCreate {
    }

    public interface checkEdit {
    }

    public interface checkNewPassword {
    }

    private long id;
    @NotEmpty(message = "Vui lòng nhập tên đăng nhập", groups = checkCreate.class)
    @Pattern(regexp = "^[a-z0-9]{3,30}$", message = "Tên đăng nhập không hợp lệ", groups = checkCreate.class)
    private String userName;
    @NotEmpty(message = "Vui lòng nhập mật khẩu", groups = checkEdit.class)
    @Pattern(regexp = "^[a-z0-9]{6,30}$", message = "Mật khẩu không hợp lệ", groups = checkEdit.class)
    private String password;
    @NotEmpty(message = "Vui lòng nhập họ tên", groups = checkEdit.class)
    @Size(max = 30, message = "Họ tên không hợp lệ", groups = checkEdit.class)
    private String fullName;
    @NotEmpty(message = "Vui lòng nhập bộ phận", groups = checkEdit.class)
    @Size(max = 30, message = "Bộ phận không hợp lệ", groups = checkEdit.class)
    private String department;
    @NotEmpty(message = "Vui lòng chọn quyền", groups = checkEdit.class)
    private String roleName;

    @NotEmpty(message = "Vui lòng nhập mật khẩu", groups = checkNewPassword.class)
    @Pattern(regexp = "^[a-z0-9]{6,30}$", message = "Mật khẩu không hợp lệ", groups = checkNewPassword.class)
    private String newPassword;

    public UserManagerDTO() {
    }

    public UserManagerDTO(long id, String userName, String fullName, String department, String roleName) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.department = department;
        this.roleName = roleName;
    }

    public UserManagerDTO(long id, String userName, String newPassword, String fullName, String department, String roleName) {
        this.id = id;
        this.userName = userName;
        this.newPassword = newPassword;
        this.fullName = fullName;
        this.department = department;
        this.roleName = roleName;
    }

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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
