package sprint_1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class AssetDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAssetDetail;

    private String assetQuantity;

    @ManyToOne
    @JoinColumn(name = "idMeetingRoom")
    @JsonBackReference
    private MeetingRoom meetingRoomAsset;

    @ManyToOne
    @JoinColumn(name = "idAsset")
    @JsonBackReference
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

    public MeetingRoom getMeetingRoomAsset() {
        return meetingRoomAsset;
    }

    public void setMeetingRoomAsset(MeetingRoom meetingRoomAsset) {
        this.meetingRoomAsset = meetingRoomAsset;
    }
}
