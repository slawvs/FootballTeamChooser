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
	/**
	 * 
	 */
	private static final long serialVersionUID = -8793182837174996633L;
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
	@OneToMany(mappedBy="gameRecord")
	private Set<Team> teams;
	
	public GameRecord() {
	}
	
	public GameRecord(ZonedDateTime date, Integer numberOfTeams) {
		this.date = date;
		this.numberOfTeams = numberOfTeams;
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
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}
	
}
