package sprint_1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprint_1.model.Role;
import sprint_1.repository.RoleRepository;
import sprint_1.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
