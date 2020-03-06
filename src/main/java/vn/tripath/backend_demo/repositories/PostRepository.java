package vn.tripath.backend_demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.tripath.backend_demo.entities.Post;
import vn.tripath.backend_demo.entities.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllBy(Pageable pageable);
    Page<Post> findAllByPoster(User poster, Pageable pageable);
    Page<Post> findAllByPosterId(Long posterId, Pageable pageable);
}
