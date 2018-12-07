package pl.slawek.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Team implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	private Integer index;
	private Integer score;
	@OneToMany(mappedBy="Teams")
	private GameRecord gameRecord;
	@ManyToMany
    @JoinTable(name = "team_players",
    	joinColumns = {@JoinColumn(name="team_id", referencedColumnName="id")},
    	inverseJoinColumns = {@JoinColumn(name="player_id", referencedColumnName="id")}
    )
	private Set<Player> SetOfPlayers;
	
	public Team() {
	}

	public Team(Long id, Integer index, Set<Player> setOfPlayers, Integer score) {
		this.id = id;
		this.index = index;
		SetOfPlayers = setOfPlayers;
		this.score = score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Set<Player> getSetOfPlayers() {
		return SetOfPlayers;
	}

	public void setSetOfPlayers(Set<Player> setOfPlayers) {
		SetOfPlayers = setOfPlayers;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
