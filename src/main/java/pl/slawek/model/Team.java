package pl.slawek.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
	@Min(0)
	@Max(99)
	private Integer score;
	@ManyToOne
	@JoinColumn(name = "gameRecord_id")
	private GameRecord gameRecord;
	@ManyToMany
    @JoinTable(name = "team_player",
    	joinColumns = {@JoinColumn(name="team_id", referencedColumnName="id_team")},
    	inverseJoinColumns = {@JoinColumn(name="player_id", referencedColumnName="id_player")}
    )
	private List<Player> players = new ArrayList<>();
	
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

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
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
	
	@Override
	public String toString() {
		return "Team [id=" + id + ", indexOfTeam=" + indexOfTeam + ", score=" + score + ", gameRecord=" + gameRecord.getDate()
				+ ", players=" + players + "]";
	}
}
