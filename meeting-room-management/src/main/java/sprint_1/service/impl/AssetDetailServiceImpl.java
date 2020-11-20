package sprint_1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprint_1.model.AssetDetail;
import sprint_1.repository.AssetDetailRepository;
import sprint_1.service.AssetDetailService;

import java.util.List;

@Service
public class AssetDetailServiceImpl implements AssetDetailService {
    @Autowired
    AssetDetailRepository assetDetailRepository;

    @Override
    public List<AssetDetail> findAll() {
        return assetDetailRepository.findAll();
    }

    @Override
    public AssetDetail findById(Long id) {
        return assetDetailRepository.findById(id).orElse(null);
    }

    @Override
    public void save(AssetDetail assetDetail) {
        assetDetailRepository.save(assetDetail);
    }

    @Override
    public void delete(Long id) {
        assetDetailRepository.deleteById(id);
    }
}
