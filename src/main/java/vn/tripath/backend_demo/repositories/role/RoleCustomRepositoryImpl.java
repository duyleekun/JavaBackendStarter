package vn.tripath.backend_demo.repositories.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import vn.tripath.backend_demo.entities.Privilege;
import vn.tripath.backend_demo.entities.Role;

import java.util.Collection;

@Repository
public class RoleCustomRepositoryImpl implements RoleCustomRepository {
    @Autowired
    @Lazy
    private RoleRepository roleRepository;

    @Override
    public Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = Role.builder().name(name).build();
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
