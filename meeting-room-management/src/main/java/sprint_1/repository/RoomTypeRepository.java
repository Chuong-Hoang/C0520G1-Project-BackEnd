package sprint_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_1.model.RoomType;

public interface RoomTypeRepository extends JpaRepository<RoomType,Long> {
    RoomType findByRoomTypeName(String name);
}
