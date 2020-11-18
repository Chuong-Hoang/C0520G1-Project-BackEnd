package sprint_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_1.model.AssetDetail;

public interface AssetDetailRepository  extends JpaRepository<AssetDetail,Long> {
}
