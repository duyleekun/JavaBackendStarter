package vn.tripath.backend_demo.controllers;

import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import vn.tripath.backend_demo.dto.authentication.LoginRequest;
import vn.tripath.backend_demo.dto.authentication.LoginResponse;
import vn.tripath.backend_demo.dto.authentication.PrivilegeResponse;
import vn.tripath.backend_demo.entities.Privilege;
import vn.tripath.backend_demo.entities.User;
import vn.tripath.backend_demo.repositories.UserRepository;
import vn.tripath.backend_demo.repositories.privilege.PrivilegeRepository;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log
@RequestMapping("authentication")
public class AuthenticationController {
    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PermitAll
    @RequestMapping(path = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        Authentication authenticate = daoAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return LoginResponse.builder().accessToken(RequestContextHolder.currentRequestAttributes().getSessionId()).build();
    }

    @RequestMapping(path = "privileges", method = RequestMethod.GET)
    public List<PrivilegeResponse> getAllPrivileges(User currentUser) {
        List<Privilege> privileges = privilegeRepository.findAllPrivilegeOfUser(currentUser);;
        return privileges.stream().map((privilege) -> modelMapper.map(privilege, PrivilegeResponse.class)).collect(Collectors.toList());
    }

}
