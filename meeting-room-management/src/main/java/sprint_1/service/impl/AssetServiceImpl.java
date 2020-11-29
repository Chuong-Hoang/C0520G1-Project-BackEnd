package sprint_1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprint_1.model.Asset;
import sprint_1.repository.AssetRepository;
import sprint_1.service.AssetService;

import java.util.List;

/**
 * AssetServiceImpl
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
@Service
public class AssetServiceImpl implements AssetService {
    @Autowired
    AssetRepository assetRepository;

    //    get list asset
    @Override
    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    //    get asset by idAsset
    @Override
    public Asset findById(Long id) {
        return assetRepository.findById(id).orElse(null);
    }

    //    save asset
    @Override
    public void save(Asset asset) {
        assetRepository.save(asset);
    }

    //    delete asset by assetName
    @Override
    public void delete(Long id) {
        assetRepository.deleteById(id);
    }

    //    get assets by assetName containing
    @Override
    public List<Asset> findAllByAssetNameContaining(String name) {
        return assetRepository.findAllByAssetNameContaining(name);
    }

    // Check for duplicate asset names
    @Override
    public boolean existsByAssetName(String assetName) {
        return assetRepository.existsByAssetName(assetName);
    }

    @Override
    public Asset findByAssetName(String assetName) {
        return assetRepository.findByAssetName(assetName);
    }
}
