package tn.esprit.vitanova.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.vitanova.entities.Comments;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByPosts_IdPosts(Long idPosts);

    Page<Comments> findByPosts_IdPosts(Long idPosts, Pageable pageable);


}