package sprint_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_1.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
