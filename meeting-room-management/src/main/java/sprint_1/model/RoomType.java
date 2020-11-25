package sprint_1.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idRoomType;

    private String roomTypeName;

    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
    private Collection<MeetingRoom> meetingRoomCollection_1;

    //Ai muốn tạo constructor có đối số thì nhớ tạo thêm constructor không đối số nhé!!!

    public Long getIdRoomType() {
        return idRoomType;
    }

    public void setIdRoomType(Long idRoomType) {
        this.idRoomType = idRoomType;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Collection<MeetingRoom> getMeetingRoomCollection_1() {
        return meetingRoomCollection_1;
    }

    public void setMeetingRoomCollection_1(Collection<MeetingRoom> meetingRoomCollection_1) {
        this.meetingRoomCollection_1 = meetingRoomCollection_1;
    }
}

