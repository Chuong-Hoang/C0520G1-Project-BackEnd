package sprint_1.dto;

/**
 * MeetingRoomSearchDTO
 *
 * Version 1.0
 *
 * Date: 24-11-2020
 *
 * Copyright
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * ---------------------------------------------------------------------------------
 * 24-11-2020        ChuongHKV           Create Data-Transfer-Object
 */
public class MeetingRoomSearchDTO {
    private Long idRoom;
    private String roomName;
    private String floor;
    private String zone;
    private String capacity;
    private String capacityMax;
    private String roomTypeName;
    private String roomStatusName;
    private String roomAsset;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;

    public String getCapacityMax() {
        return capacityMax;
    }

    public void setCapacityMax(String capacityMax) {
        this.capacityMax = capacityMax;
    }

    public MeetingRoomSearchDTO() {
    }

    public Long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Long idRoom) {
        this.idRoom = idRoom;
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

    public String getRoomAsset() {
        return roomAsset;
    }

    public void setRoomAsset(String roomAsset) {
        this.roomAsset = roomAsset;
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
