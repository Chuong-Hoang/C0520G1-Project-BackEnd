package sprint_1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class BookedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBookedRoom;

    private String startDate;

    private String endDate;

    private String content;

    private String bookedDate;

    private String bookedStatus;

    @ManyToOne
    @JoinColumn(name = "startTime")
    @JsonBackReference
    private TimeFrame startTime;

    @ManyToOne
    @JoinColumn(name = "endTime")
    @JsonBackReference
    private TimeFrame endTime;

    @ManyToOne
    @JoinColumn(name = "idUser")
    @JsonBackReference
    private User bookedUser;

    @ManyToOne
    @JoinColumn(name = "idMeetingRoom")
    @JsonBackReference
    private MeetingRoom meetingRoom;

    //Ai muốn tạo constructor có đối số thì nhớ tạo thêm constructor không đối số nhé!!!

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

    public User getBookedUser() {
        return bookedUser;
    }

    public void setBookedUser(User bookedUser) {
        this.bookedUser = bookedUser;
    }

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public TimeFrame getStartTime() {
        return startTime;
    }

    public void setStartTime(TimeFrame startTime) {
        this.startTime = startTime;
    }

    public TimeFrame getEndTime() {
        return endTime;
    }

    public void setEndTime(TimeFrame endTime) {
        this.endTime = endTime;
    }
}
