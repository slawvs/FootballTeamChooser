package pl.slawek.model;

import javax.persistence.*;

@Entity
public class Player {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String nickName;
	private int skillIndex;
	
	public Player() {
	}

	public Player(long id, String nickname, int skillIndex) {
		this.id = id;
		this.nickName = nickname;
		this.skillIndex = skillIndex;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickname) {
		this.nickName = nickname;
	}
	public int getSkillIndex() {
		return skillIndex;
	}
	public void setSkillIndex(int skillIndex) {
		this.skillIndex = skillIndex;
	}
	

}
