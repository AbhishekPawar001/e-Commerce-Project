package com.store.order.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	private Long userId;
	private String name;
	private int age;
	private String gender;
	private String email;
	private String mobile;
	private String address;
	private String password;
	private String role;
	private LocalDateTime timestamp;
}
