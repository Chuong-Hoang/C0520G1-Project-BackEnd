package sprint_1.service;

import sprint_1.model.Asset;
import java.util.List;

/**
 * AssetService
 * <p>
 * Version 1.0
 * <p>
 * Date: 22-11-2020
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE                 AUTHOR
 * -----------------------------
 * 22-11-2020         TungTS
 */
public interface AssetService {

    List<Asset> findAll();

    Asset findById(Long id);

    void save(Asset asset);

    void delete(Long id);

    List<Asset> findAllByAssetNameContaining(String name);

    boolean existsByAssetName(String assetName);
}
