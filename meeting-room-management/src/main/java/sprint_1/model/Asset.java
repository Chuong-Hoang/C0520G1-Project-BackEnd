package sprint_1.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Asset
 *
 * Version 1.0
 *
 * Date: 22-11-2020
 *
 * Copyright
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * ----------------------------------------------------------
 * 21-11-2020         TungTS            Create Class Model
 */

@Entity
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsset;

    private String assetName;

    private String usingQuantity;

    private String fixingQuantity;

    private String total;

    private String image;

    private String description;

    private String price;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL)
    private Collection<AssetDetail> assetDetailCollection;

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

    public Collection<AssetDetail> getAssetDetailCollection() {
        return assetDetailCollection;
    }

    public void setAssetDetailCollection(Collection<AssetDetail> assetDetailCollection) {
        this.assetDetailCollection = assetDetailCollection;
    }
}
