package sprint_1.dto;

import java.util.List;

public class MeetingRoomDTO {

    private Long idRoom;

    private String roomName;

    private String floor;

    private String zone;

    private String capacity;

    private String image;

    private String startDate;

    private String endDate;

    private String roomTypeName;

    private String roomStatusName;

    private List<AssetDetailDTO> asset;

    public MeetingRoomDTO(Long idRoom, String roomName, String floor, String zone, String capacity, String image, String startDate, String endDate, String roomTypeName, String roomStatusName, List<AssetDetailDTO> asset) {
        this.idRoom = idRoom;
        this.roomName = roomName;
        this.floor = floor;
        this.zone = zone;
        this.capacity = capacity;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomTypeName = roomTypeName;
        this.roomStatusName = roomStatusName;
        this.asset = asset;
    }

    public MeetingRoomDTO(Long idRoom, String roomName, String floor, String zone, String capacity, String image, String startDate, String endDate, String roomTypeName, String roomStatusName) {
        this.idRoom = idRoom;
        this.roomName = roomName;
        this.floor = floor;
        this.zone = zone;
        this.capacity = capacity;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomTypeName = roomTypeName;
        this.roomStatusName = roomStatusName;
    }

    public MeetingRoomDTO() {
    }


    public List<AssetDetailDTO> getAsset() {
        return asset;
    }

    public void setAsset(List<AssetDetailDTO> asset) {
        this.asset = asset;
    }

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

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public String getRoomStatusName() {
        return roomStatusName;
    }

    public void setRoomStatusName(String roomStatusName) {
        this.roomStatusName = roomStatusName;
    }
}
