package vn.tripath.backend_demo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.tripath.backend_demo.entities.Privilege;
import vn.tripath.backend_demo.entities.User;
import vn.tripath.backend_demo.repositories.UserRepository;
import vn.tripath.backend_demo.repositories.privilege.PrivilegeRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            return new org.springframework.security.core.userdetails.User(
                    " ", " ", true, true, true, true,
                    Collections.singleton(new SimpleGrantedAuthority("PUBLIC"))
            );
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.isActive(), true, true,
                true, privilegeRepository.findAllPrivilegeOfUser(user).stream().map(Privilege::getName).map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}
