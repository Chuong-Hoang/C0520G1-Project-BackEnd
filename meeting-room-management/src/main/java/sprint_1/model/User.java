package sprint_1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;

/**
 * User
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
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String userName;
    private String password;
    private String fullName;
    private String department;

    @OneToMany(mappedBy = "bookedUser", cascade = CascadeType.ALL)
    private Collection<BookedRoom> bookedRoomCollection;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private Collection<Comment> commentCollection_1;

    @OneToMany(mappedBy = "replier", cascade = CascadeType.ALL)
    private Collection<Comment> commentCollection_2;

    @ManyToOne
    @JoinColumn(name = "idRole")
    @JsonBackReference
    private Role role;

    //Ai muốn tạo constructor có đối số thì nhớ tạo thêm constructor không đối số nhé!!!

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Collection<BookedRoom> getBookedRoomCollection() {
        return bookedRoomCollection;
    }

    public void setBookedRoomCollection(Collection<BookedRoom> bookedRoomCollection) {
        this.bookedRoomCollection = bookedRoomCollection;
    }

    public Collection<Comment> getCommentCollection_1() {
        return commentCollection_1;
    }

    public void setCommentCollection_1(Collection<Comment> commentCollection_1) {
        this.commentCollection_1 = commentCollection_1;
    }

    public Collection<Comment> getCommentCollection_2() {
        return commentCollection_2;
    }

    public void setCommentCollection_2(Collection<Comment> commentCollection_2) {
        this.commentCollection_2 = commentCollection_2;
    }
}
