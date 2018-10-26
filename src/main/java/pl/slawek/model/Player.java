package pl.slawek.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import pl.slawek.constraint.UniqueNickName;


@Entity
@UniqueNickName
public class Player {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, length = 15, unique = true)
	@NotBlank(message = "{pl.slawek.model.Player.nickName.NotEmpty}")
	private String nickName;
	@NotNull(message = "{pl.slawek.model.Player.skillIndex.NotEmpty}")
	@Min(1)
	@Max(10)
	private Integer skillIndex;
	
	public Player() {
	}

	public Player(long id, String nickname, Integer skillIndex) {
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
	public Integer getSkillIndex() {
		return skillIndex;
	}
	public void setSkillIndex(Integer skillIndex) {
		this.skillIndex = skillIndex;
	}
	

}
