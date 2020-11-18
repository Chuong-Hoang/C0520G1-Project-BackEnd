package sprint_1.model;

import javax.persistence.*;

@Entity
@Table
public class AssetDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAssetDetail;

    private String assetQuantity;

    @ManyToOne
    @JoinColumn(name = "idMeetingRoom")
    private MeetingRoom meetingRoom;

    @ManyToOne
    @JoinColumn(name = "idAsset")
    private Asset asset;

    //Ai muốn tạo constructor có đối số thì nhớ tạo thêm constructor không đối số nhé!!!

    public Long getIdAssetDetail() {
        return idAssetDetail;
    }

    public void setIdAssetDetail(Long idAssetDetail) {
        this.idAssetDetail = idAssetDetail;
    }

    public String getAssetQuantity() {
        return assetQuantity;
    }

    public void setAssetQuantity(String assetQuantity) {
        this.assetQuantity = assetQuantity;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }
}
