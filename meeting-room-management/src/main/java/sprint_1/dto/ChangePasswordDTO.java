package sprint_1.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class ChangePasswordDTO {
    private long id;
    private String userName;

    @NotEmpty(message = "Vui lòng nhập mật khẩu")
    private String oldPassword;

    @NotEmpty(message = "Vui lòng nhập mật khẩu")
    @Pattern(regexp = "^[a-z0-9]{6,30}$", message = "Mật khẩu không hợp lệ")
    private String newPassword;
//    private String message;

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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
}
