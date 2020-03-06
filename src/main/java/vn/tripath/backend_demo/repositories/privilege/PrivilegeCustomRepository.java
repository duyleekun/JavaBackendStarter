package vn.tripath.backend_demo.repositories.privilege;

import vn.tripath.backend_demo.entities.Privilege;

public interface PrivilegeCustomRepository {
    Privilege createPrivilegeIfNotFound(String name);

}
