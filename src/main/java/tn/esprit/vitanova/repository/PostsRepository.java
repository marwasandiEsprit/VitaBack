package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.vitanova.entities.Posts;

import java.util.List;
import java.util.Optional;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findByCommunity_IdCommunityOrderByCreatedDateDesc(Long idCommunity);

    List<Posts> findByIdOwner(Long idOwner);

    Optional<Posts> findByIdPostsAndCommunity_IdCommunity(Long idPosts, Long idCommunity);
}