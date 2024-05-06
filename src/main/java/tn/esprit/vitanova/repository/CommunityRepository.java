package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.vitanova.entities.Community;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    @Query("select c from Community c where upper(c.name) like upper(concat('%', ?1, '%'))")
    List<Community> findByNameContainsIgnoreCase(String name);


}