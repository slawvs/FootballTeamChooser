package pl.slawek.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class GameRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8793182837174996633L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_gameRecord")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "user_id",nullable = false)
	private User gameCreator;
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime date;
	@Column(nullable = false)
	@Min(2)
	//now application is ready only for 2 teams
	@Max(2)
	private Integer numberOfTeams;
	private Integer numberOfPlayersPerTeam;
	private GameStatus gameStatus;
	@ManyToMany
	@JoinTable(name = "game_player",
			joinColumns = {@JoinColumn(name="gameRecord_id", referencedColumnName="id_gameRecord")},
			inverseJoinColumns = {@JoinColumn(name="player_id", referencedColumnName="id_player")}
	)
	private List<Player> listOfPlayers = new LinkedList<>();
	@OneToMany(mappedBy="gameRecord",
			cascade = CascadeType.PERSIST)
	private List<Team> teams = new ArrayList<>();

	public void addTeam(Team team) {
		team.setGameRecord(this);
		getTeams().add(team);
	}
	
	public GameRecord() {
	}
	
	public GameRecord(LocalDateTime date, Integer numberOfTeams) {
		this.date = date;
		this.numberOfTeams = numberOfTeams;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Integer getNumberOfTeams() {
		return numberOfTeams;
	}

	public void setNumberOfTeams(Integer numberOfTeams) {
		this.numberOfTeams = numberOfTeams;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	@Override
	public String toString() {
		return "GameRecord [id=" + id + ", date=" + date + ", numberOfTeams=" + numberOfTeams + ", teams=" + teams
				+ "]";
	}
	
}
