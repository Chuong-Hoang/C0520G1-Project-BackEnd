package sprint_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_1.model.Asset;

public interface AssetRepository extends JpaRepository<Asset,Long> {
}
