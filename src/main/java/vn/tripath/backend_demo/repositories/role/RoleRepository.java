package vn.tripath.backend_demo.repositories.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.tripath.backend_demo.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, RoleCustomRepository {
    Role findByName(String role_admin);
}
