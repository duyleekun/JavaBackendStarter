package vn.tripath.backend_demo.repositories.privilege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import vn.tripath.backend_demo.entities.Privilege;

@Repository
public class PrivilegeCustomRepositoryImpl implements PrivilegeCustomRepository {
    @Autowired
    @Lazy
    PrivilegeRepository privilegeRepository;

    @Override
    public Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = Privilege.builder().name(name).build();
            privilegeRepository.save(privilege);
        }
        return privilege;
    }
}
