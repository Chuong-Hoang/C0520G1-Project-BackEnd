package sprint_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_1.model.Asset;

import java.util.List;

/**
 * AssetRepository
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

public interface AssetRepository extends JpaRepository<Asset, Long> {

    // Asset search by name
    List<Asset> findAllByAssetNameContaining(String name);

    // Check for duplicate asset names
    Boolean existsByAssetName(String assetName);
}
