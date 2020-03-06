package vn.tripath.backend_demo.repositories.role;

import vn.tripath.backend_demo.entities.Privilege;
import vn.tripath.backend_demo.entities.Role;

import java.util.Collection;

public interface RoleCustomRepository {
    Role createRoleIfNotFound(String name, Collection<Privilege> privileges);
}
