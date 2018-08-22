package pl.slawek.model;

import javax.persistence.*;

@Entity
public class Player {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String nickname;
	private int skillIndex;
	
	public Player() {
	}

	public Player(long id, String nickname, int skillIndex) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.skillIndex = skillIndex;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSkillIndex() {
		return skillIndex;
	}
	public void setSkillIndex(int skillIndex) {
		this.skillIndex = skillIndex;
	}
	

}
