package sprint_1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class RoomStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long idRoomStatus;

    private String roomStatusName;

    @JsonBackReference
    @OneToMany(mappedBy = "roomStatus", cascade = CascadeType.ALL)
    private Collection<MeetingRoom> meetingRoomCollection;

    //Ai muốn tạo constructor có đối số thì nhớ tạo thêm constructor không đối số nhé!!!

    public Long getIdRoomStatus() {
        return idRoomStatus;
    }

    public void setIdRoomStatus(Long idRoomStatus) {
        this.idRoomStatus = idRoomStatus;
    }

    public String getRoomStatusName() {
        return roomStatusName;
    }

    public void setRoomStatusName(String roomStatusName) {
        this.roomStatusName = roomStatusName;
    }

    public Collection<MeetingRoom> getMeetingRooms() {
        return meetingRoomCollection;
    }

    public void setMeetingRooms(Collection<MeetingRoom> meetingRoomCollection) {
        this.meetingRoomCollection = meetingRoomCollection;
    }
}
