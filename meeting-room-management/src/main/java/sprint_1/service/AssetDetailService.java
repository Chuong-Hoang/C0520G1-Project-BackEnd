package sprint_1.service;

import sprint_1.model.AssetDetail;

import java.util.List;

public interface AssetDetailService {

    List<AssetDetail> findAll();

    AssetDetail findById(Long id);

    void save(AssetDetail assetDetail);

    void delete(Long id);
}
