package im.fehead.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import im.fehead.domain.enums.SocialType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User implements Serializable {
	private static final long serialVersionUID = 8275063230446266076L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private	Long	idx;
	
	@Column
	private	String	name;
	
	@Column
	private	String	password;
	
	@Column
	private	String	email;
	
	@Column
	private	String	pincipal;
	
	@Column
	@Enumerated(EnumType.STRING)
	private	SocialType socialType;
	
	@Column
	private	LocalDateTime	createdDate;
	
	@Column
	private	LocalDateTime	updatedDate;
	
}
