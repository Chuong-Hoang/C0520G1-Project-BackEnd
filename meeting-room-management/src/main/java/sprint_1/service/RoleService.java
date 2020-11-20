package sprint_1.service;

import sprint_1.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    void save(Role role);

    Role findById(Long id);

    void remove(Long id);
}
