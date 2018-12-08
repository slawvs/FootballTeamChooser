package pl.slawek.data;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.slawek.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
