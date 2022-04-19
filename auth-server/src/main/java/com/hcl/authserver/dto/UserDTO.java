package com.hcl.authserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO {
	
	private Long userId;
	private String name;
	private String email;
	private String mobile;
	private int age;
	private String gender;
	private String password;
	private String address;

}
