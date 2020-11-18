package sprint_1.service;

import sprint_1.model.Asset;

import java.util.List;

public interface AssetService {

    List<Asset> findAll();

    Asset findById(Long id);

    void save(Asset asset);

    void delete(Long id);
}
