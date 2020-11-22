package sprint_1.dto;

public class AssetDetailDTO {
    private String nameAssetDetail;
    private String quantity;

    public AssetDetailDTO(String nameAssetDetail, String quantity) {
        this.nameAssetDetail = nameAssetDetail;
        this.quantity = quantity;
    }

    public AssetDetailDTO() {
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
