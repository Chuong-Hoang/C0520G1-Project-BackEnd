package sprint_1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoomType;
    private String RoomTypeName;

    @JsonBackReference
    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
    private Collection<MeetingRoom> meetingRoomCollection;

    //Ai muốn tạo constructor có đối số thì nhớ tạo thêm constructor không đối số nhé!!!

    public Long getIdRoomType() {
        return idRoomType;
    }

    public void setIdRoomType(Long idRoomType) {
        this.idRoomType = idRoomType;
    }

    public String getRoomTypeName() {
        return RoomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        RoomTypeName = roomTypeName;
    }

    public Collection<MeetingRoom> getMeetingRoomCollection() {
        return meetingRoomCollection;
    }

    public void setMeetingRoomCollection(Collection<MeetingRoom> meetingRoomCollection) {
        this.meetingRoomCollection = meetingRoomCollection;
    }
}
