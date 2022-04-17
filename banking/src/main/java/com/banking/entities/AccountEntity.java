package com.banking.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.banking.dto.UserDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Data
@Table(name = "account")
public class AccountEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long accountNo;
	private int currentBalance;
	private String accountType;
	private LocalDateTime createdDate;
	private LocalDateTime lastUpdatedDate;
	
	@JoinColumn(name = "user_id")
	@OneToOne
	private UserEntity userEntity;
	
	public AccountEntity(UserDTO userdto) {
		this.accountNo = userdto.getAccountNo();
		this.currentBalance = userdto.getCurrentBalance();
		this.accountType = userdto.getAccountType();
		this.lastUpdatedDate = userdto.getLastUpdatedDate();
		this.createdDate = userdto.getCreatedDate();
	}
}
