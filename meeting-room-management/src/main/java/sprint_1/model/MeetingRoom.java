package sprint_1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class MeetingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoom;

    private String roomName;

    private String floor;

    private String zone;

    private String capacity;

    private String image;

    private String startDate;

    private String endDate;

    @JsonBackReference
    @OneToMany(mappedBy = "meetingRoom", cascade = CascadeType.ALL)
    private Collection<AssetDetail> assetDetailCollection;

    @JsonBackReference
    @OneToMany(mappedBy = "meetingRoom", cascade = CascadeType.ALL)
    private Collection<BookedRoom> bookedRoomCollection;

    @JsonBackReference
    @OneToMany(mappedBy = "meetingRoom", cascade = CascadeType.ALL)
    private Collection<Comment> commentCollection;

    @ManyToOne
    @JoinColumn(name = "idRoomType")
    private RoomType roomType;

    @ManyToOne
    @JoinColumn(name = "idRoomStatus")
    private RoomStatus roomStatus;

    //Ai muốn tạo constructor có đối số thì nhớ tạo thêm constructor không đối số nhé!!!

    public Long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Long idRoom) {
        this.idRoom = idRoom;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Collection<AssetDetail> getAssetDetailCollection() {
        return assetDetailCollection;
    }

    public void setAssetDetailCollection(Collection<AssetDetail> assetDetailCollection) {
        this.assetDetailCollection = assetDetailCollection;
    }

    public Collection<BookedRoom> getBookedRoomCollection() {
        return bookedRoomCollection;
    }

    public void setBookedRoomCollection(Collection<BookedRoom> bookedRoomCollection) {
        this.bookedRoomCollection = bookedRoomCollection;
    }

    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }
}
