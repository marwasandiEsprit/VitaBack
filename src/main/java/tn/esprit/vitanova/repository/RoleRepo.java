package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.vitanova.entities.ERole;
import tn.esprit.vitanova.entities.Role;

import java.util.Optional;


@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
