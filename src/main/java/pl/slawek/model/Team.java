package pl.slawek.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Team implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7766305440567788367L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_team")
	private Long id;
	private Integer indexOfTeam;
	private Integer score;
	@ManyToOne
	@JoinColumn(name = "gameRecord_id")
	private GameRecord gameRecord;
	@ManyToMany
    @JoinTable(name = "team_player",
    	joinColumns = {@JoinColumn(name="team_id", referencedColumnName="id_team")},
    	inverseJoinColumns = {@JoinColumn(name="player_id", referencedColumnName="id_player")}
    )
	private Set<Player> setOfPlayers;
	
	public Team() {
	}

	public Team(Integer indexOfTeam, Integer score) {
		this.indexOfTeam = indexOfTeam;
		this.score = score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIndex() {
		return indexOfTeam;
	}

	public void setIndex(Integer indexOfTeam) {
		this.indexOfTeam = indexOfTeam;
	}

	public Set<Player> getSetOfPlayers() {
		return setOfPlayers;
	}

	public void setSetOfPlayers(Set<Player> setOfPlayers) {
		this.setOfPlayers = setOfPlayers;
	}
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public GameRecord getGameRecord() {
		return gameRecord;
	}

	public void setGameRecord(GameRecord gameRecord) {
		this.gameRecord = gameRecord;
	}
}
