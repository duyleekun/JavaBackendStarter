package vn.tripath.backend_demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import vn.tripath.backend_demo.entities.Privilege;
import vn.tripath.backend_demo.entities.Role;
import vn.tripath.backend_demo.entities.User;
import vn.tripath.backend_demo.repositories.privilege.PrivilegeRepository;
import vn.tripath.backend_demo.repositories.role.RoleRepository;
import vn.tripath.backend_demo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class DataSeedingService implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Privilege readMyPost = privilegeRepository.createPrivilegeIfNotFound("ROLE_READ_MY_POST");
        Privilege writeMyPost = privilegeRepository.createPrivilegeIfNotFound("ROLE_WRITE_MY_POST");
        Privilege adminReadPost = privilegeRepository.createPrivilegeIfNotFound("ROLE_ADMIN_READ_POST");
        Privilege adminWritePrivilege = privilegeRepository.createPrivilegeIfNotFound("ROLE_ADMIN_WRITE_POST");


        Role adminRole = roleRepository.createRoleIfNotFound("ADMIN", Arrays.asList(adminReadPost, adminWritePrivilege));
        Role userRole = roleRepository.createRoleIfNotFound("USER", Arrays.asList(readMyPost, writeMyPost));

        if (userRepository.findByEmail("admin@demo.com") == null) {
            userRepository.save(User.builder().name("admin").password(passwordEncoder.encode("password")).email("admin@demo.com").active(true).roles(Arrays.asList(adminRole, userRole)).build());
        }

        if (userRepository.findByEmail("user@demo.com") == null) {
            userRepository.save(User.builder().name("user").password(passwordEncoder.encode("password")).email("user@demo.com").active(true).roles(Collections.singleton(userRole)).build());
        }

        alreadySetup = true;
    }
}
