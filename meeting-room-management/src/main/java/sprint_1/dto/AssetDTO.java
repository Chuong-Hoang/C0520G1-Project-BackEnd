package sprint_1.dto;

import java.util.List;

public class AssetDTO {
    private Long idAsset;

    private String assetName;

    private String usingQuantity;

    private String fixingQuantity;

    private String total;

    private String image;

    private String description;

    private String price;


    private List<AssetDetailDTO> assetDetailDTOList;

    public AssetDTO() {
    }

    public AssetDTO(Long idAsset, String assetName, String usingQuantity, String fixingQuantity, String total, String image, String description, String price, List<AssetDetailDTO> assetDetailDTOList) {
        this.idAsset = idAsset;
        this.assetName = assetName;
        this.usingQuantity = usingQuantity;
        this.fixingQuantity = fixingQuantity;
        this.total = total;
        this.image = image;
        this.description = description;
        this.price = price;
        this.assetDetailDTOList = assetDetailDTOList;
    }

    public Long getIdAsset() {
        return idAsset;
    }

    public void setIdAsset(Long idAsset) {
        this.idAsset = idAsset;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getUsingQuantity() {
        return usingQuantity;
    }

    public void setUsingQuantity(String usingQuantity) {
        this.usingQuantity = usingQuantity;
    }

    public String getFixingQuantity() {
        return fixingQuantity;
    }

    public void setFixingQuantity(String fixingQuantity) {
        this.fixingQuantity = fixingQuantity;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<AssetDetailDTO> getAssetDetailDTOList() {
        return assetDetailDTOList;
    }

    public void setAssetDetailDTOList(List<AssetDetailDTO> assetDetailDTOList) {
        this.assetDetailDTOList = assetDetailDTOList;
    }
}
