package sprint_1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprint_1.model.Asset;
import sprint_1.repository.AssetRepository;
import sprint_1.service.AssetService;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {
    @Autowired
    AssetRepository assetRepository;

    @Override
    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    @Override
    public Asset findById(Long id) {
        return assetRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Asset asset) {
        assetRepository.save(asset);
    }

    @Override
    public void delete(Long id) {
        assetRepository.deleteById(id);
    }
}
