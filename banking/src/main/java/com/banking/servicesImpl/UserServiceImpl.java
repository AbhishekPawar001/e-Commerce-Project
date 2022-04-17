 package com.banking.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.dto.UserDTO;
import com.banking.entities.AccountEntity;
import com.banking.entities.UserEntity;
import com.banking.repositories.AccountRepository;
import com.banking.repositories.UserRepository;
import com.banking.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public UserDTO saveUser(UserDTO userdto) {
		UserEntity userEntity = new UserEntity(userdto);
		AccountEntity accountEntity = new AccountEntity(userdto);
		accountEntity.setUserEntity(userEntity);

		userRepository.save(userEntity);
		accountRepository.save(accountEntity);
		
		return userdto;
		
	}
}
