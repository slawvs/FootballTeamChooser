package pl.slawek.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class GameRecord implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private ZonedDateTime date;
	@Column(nullable = false)
	@Min(2)
	//now application is ready only for 2 teams
	@Max(2)
	private Integer numberOfTeams;
	@Column(nullable = false)
	@OneToMany
	@JoinColumn(name = "gameRecord_id")
	private Set<Team> Teams;
	
	public GameRecord() {
	}
	
	public GameRecord(Long id, ZonedDateTime date, Integer numberOfTeams, Set<Team> teams) {
		this.id = id;
		this.date = date;
		this.numberOfTeams = numberOfTeams;
		Teams = teams;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZonedDateTime getDate() {
		return date;
	}

	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

	public Integer getNumberOfTeams() {
		return numberOfTeams;
	}

	public void setNumberOfTeams(Integer numberOfTeams) {
		this.numberOfTeams = numberOfTeams;
	}

	public Set<Team> getTeams() {
		return Teams;
	}

	public void setTeams(Set<Team> teams) {
		Teams = teams;
	}
	
}
