package sprint_1.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import sprint_1.model.MeetingRoom;
import sprint_1.model.TimeFrame;
import sprint_1.model.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class BookedRoomDTOList {
    private Long idBookedRoom;
    private String startDate;
    private String endDate;
    private String content;
    private String bookedDate;
    private String bookedStatus;
    private Double effective;
    private Integer totalUse;
    private String startTime;
    private String endTime;
    private String userName;
    private String roomName;
    private String roomType;

    public BookedRoomDTOList() {
    }

    public BookedRoomDTOList(Long idBookedRoom, String startDate, String endDate, String content, String bookedDate, String bookedStatus, Double effective, Integer totalUse, String startTime, String endTime, String userName, String roomName, String roomType) {
        this.idBookedRoom = idBookedRoom;
        this.startDate = startDate;
        this.endDate = endDate;
        this.content = content;
        this.bookedDate = bookedDate;
        this.bookedStatus = bookedStatus;
        this.effective = effective;
        this.totalUse = totalUse;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userName = userName;
        this.roomName = roomName;
        this.roomType = roomType;
    }

    public Long getIdBookedRoom() {
        return idBookedRoom;
    }

    public void setIdBookedRoom(Long idBookedRoom) {
        this.idBookedRoom = idBookedRoom;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(String bookedDate) {
        this.bookedDate = bookedDate;
    }

    public String getBookedStatus() {
        return bookedStatus;
    }

    public void setBookedStatus(String bookedStatus) {
        this.bookedStatus = bookedStatus;
    }

    public Double getEffective() {
        return effective;
    }

    public void setEffective(Double effective) {
        this.effective = effective;
    }

    public Integer getTotalUse() {
        return totalUse;
    }

    public void setTotalUse(Integer totalUse) {
        this.totalUse = totalUse;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
