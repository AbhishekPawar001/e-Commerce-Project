package com.banking.dto;

import java.time.LocalDateTime;

import com.banking.helper.AccountNumberGenerator;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserDTO {
	
	private Long accountNo;
	private String accountType;
	private int currentBalance;
	private LocalDateTime createdDate;
	private LocalDateTime lastUpdatedDate;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private String gender;
	private int age;
        
    public UserDTO() {
    	this.setAccountNo(AccountNumberGenerator.getAccountNumber());
    	this.setCurrentBalance(10000);
    }
}
