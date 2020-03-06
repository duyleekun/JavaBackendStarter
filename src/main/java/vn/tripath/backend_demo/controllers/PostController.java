package vn.tripath.backend_demo.controllers;

import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.tripath.backend_demo.dto.post.PostRequest;
import vn.tripath.backend_demo.dto.post.PostResponse;
import vn.tripath.backend_demo.entities.Post;
import vn.tripath.backend_demo.entities.User;
import vn.tripath.backend_demo.repositories.PostRepository;
import vn.tripath.backend_demo.repositories.UserRepository;
import vn.tripath.backend_demo.repositories.privilege.PrivilegeRepository;

@RestController
@Log
@RequestMapping("posts")
public class PostController {
    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepository postRepository;


    @RequestMapping(path = "all", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN_READ_POST')")
    public Page<PostResponse> listAll(Authentication authentication) {
        return postRepository.findAllBy(Pageable.unpaged()).map((this::convertToDTO));
    }


    @PreAuthorize("hasRole('READ_MY_POST')")
    @RequestMapping(path = "me", method = RequestMethod.GET)
    public Page<PostResponse> listMine(User currentUser) {
        return postRepository.findAllByPoster(currentUser, Pageable.unpaged()).map(this::convertToDTO);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @PreAuthorize("hasRole('WRITE_MY_POST')")
    public PostResponse create(@RequestBody PostRequest postRequest, User currentUser) {
        Post newPost = postRepository.save(Post.builder().title(postRequest.getTitle()).content(postRequest.getContent()).poster(currentUser).build());
        return convertToDTO(newPost);
    }

    private PostResponse convertToDTO(Post post) {
        return modelMapper.map(post, PostResponse.class);
    }
}
