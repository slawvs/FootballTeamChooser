package pl.slawek.data;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.slawek.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	UserRole findByRole(String role);
}
