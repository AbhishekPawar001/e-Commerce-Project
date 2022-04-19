package com.banking.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.banking.dto.UserDTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private String gender;
	private int age;
	private LocalDateTime createdDate;
	
	public UserEntity(UserDTO userdto) {
		this.firstName = userdto.getFirstName();
		this.lastName = userdto.getLastName();
		this.mobile = userdto.getMobile();
		this.email = userdto.getEmail();
		this.age = userdto.getAge();
		this.gender = userdto.getGender();
		this.createdDate = userdto.getCreatedDate();
	}
}
