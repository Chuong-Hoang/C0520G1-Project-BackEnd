package sprint_1.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import sprint_1.model.MeetingRoom;
import sprint_1.model.TimeFrame;
import sprint_1.model.User;

public class BookedRoomDTO {
    private Long idBookedRoom;
    private String startDate;
    private String endDate;
    private String content;
    private String bookedDate;
    private String bookedStatus;
    private Long startTimeId;
    private String startTime;
    private Long endTimeId;
    private String endTime;
    private Long bookedUserId;
    private Long meetingRoomId;
    private String meetingRoomName;
    private String roomType;

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Long getIdBookedRoom() {
        return idBookedRoom;
    }

    public void setIdBookedRoom(Long idBookedRoom) {
        this.idBookedRoom = idBookedRoom;
    }

    public String getMeetingRoomName() {
        return meetingRoomName;
    }

    public void setMeetingRoomName(String meetingRoomName) {
        this.meetingRoomName = meetingRoomName;
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

    public Long getStartTimeId() {
        return startTimeId;
    }

    public void setStartTimeId(Long startTimeId) {
        this.startTimeId = startTimeId;
    }

    public Long getEndTimeId() {
        return endTimeId;
    }

    public void setEndTimeId(Long endTimeId) {
        this.endTimeId = endTimeId;
    }

    public Long getBookedUserId() {
        return bookedUserId;
    }

    public void setBookedUserId(Long bookedUserId) {
        this.bookedUserId = bookedUserId;
    }

    public Long getMeetingRoomId() {
        return meetingRoomId;
    }

    public void setMeetingRoomId(Long meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }
}
