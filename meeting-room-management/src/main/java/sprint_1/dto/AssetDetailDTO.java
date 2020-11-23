package sprint_1.dto;

public class AssetDetailDTO {
    private String nameMeetingRoom;
    private String nameAssetDetail;
    private String quantity;

    public AssetDetailDTO(String nameAssetDetail, String quantity) {
        this.nameAssetDetail = nameAssetDetail;
        this.quantity = quantity;
    }

    public AssetDetailDTO() {
    }

    public String getNameMeetingRoom() {
        return nameMeetingRoom;
    }

    public void setNameMeetingRoom(String nameMeetingRoom) {
        this.nameMeetingRoom = nameMeetingRoom;
    }

    public String getNameAssetDetail() {
        return nameAssetDetail;
    }

    public void setNameAssetDetail(String nameAssetDetail) {
        this.nameAssetDetail = nameAssetDetail;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
