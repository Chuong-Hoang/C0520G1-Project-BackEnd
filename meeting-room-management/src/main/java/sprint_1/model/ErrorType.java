package sprint_1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class ErrorType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idErrorType;

    private String errorTypeName;

    @JsonBackReference
    @OneToMany(mappedBy = "errorType", cascade = CascadeType.ALL)
    private Collection<Comment> commentCollection;

    //Ai muốn tạo constructor có đối số thì nhớ tạo thêm constructor không đối số nhé!!!

    public Long getIdErrorType() {
        return idErrorType;
    }

    public void setIdErrorType(Long idErrorType) {
        this.idErrorType = idErrorType;
    }

    public String getErrorTypeName() {
        return errorTypeName;
    }

    public void setErrorTypeName(String errorTypeName) {
        this.errorTypeName = errorTypeName;
    }

    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }
}
