package com.hcl.authserver.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginRequest {
	
	@NotNull
	@Email
	private String email;
	@NotNull(message = "password must not be null")
	private String password;
}
