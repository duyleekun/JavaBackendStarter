package vn.tripath.backend_demo.repositories.privilege;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.tripath.backend_demo.entities.Privilege;
import vn.tripath.backend_demo.entities.User;

import java.util.List;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long>, PrivilegeCustomRepository {
    Privilege findByName(String name);

    @Query("select distinct p from Privilege p join p.roles r join r.users u where u = :user")
    List<Privilege> findAllPrivilegeOfUser(User user);
}
