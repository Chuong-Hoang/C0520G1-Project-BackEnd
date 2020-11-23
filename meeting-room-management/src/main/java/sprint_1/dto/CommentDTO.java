package sprint_1.dto;

import sprint_1.model.ErrorType;

public class CommentDTO {
    private Long idComment;
    private String senderName;
    private String commentTime;
    private String contentReply;
    private String contentComment;
    private Long idReplier;
    private String replierName;
    private String errorTypeName;
    private String roomName;
    private boolean status;

    public CommentDTO() {
    }

    public CommentDTO(Long idComment, String senderName,String commentTime,Long idReplier, String contentReply, String contentComment, String replierName, String errorTypeName, String meetingRoomName,boolean status) {
        this.idComment = idComment;
        this.senderName = senderName;
        this.commentTime = commentTime;
        this.idReplier = idReplier;
        this.contentReply = contentReply;
        this.contentComment = contentComment;
        this.replierName = replierName;
        this.errorTypeName = errorTypeName;
        this.roomName = meetingRoomName;
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getErrorTypeName() {
        return errorTypeName;
    }

    public void setErrorTypeName(String errorTypeName) {
        this.errorTypeName = errorTypeName;
    }

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }


    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getContentReply() {
        return contentReply;
    }

    public void setContentReply(String contentReply) {
        this.contentReply = contentReply;
    }

    public String getContentComment() {
        return contentComment;
    }

    public void setContentComment(String contentComment) {
        this.contentComment = contentComment;
    }

    public Long getIdReplier() {
        return idReplier;
    }

    public void setIdReplier(Long idReplier) {
        this.idReplier = idReplier;
    }

    public String getReplierName() {
        return replierName;
    }

    public void setReplierName(String replierName) {
        this.replierName = replierName;
    }
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String meetingRoomName) {
        this.roomName = meetingRoomName;
    }
}
