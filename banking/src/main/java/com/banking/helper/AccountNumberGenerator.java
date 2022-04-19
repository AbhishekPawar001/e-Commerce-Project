package com.banking.helper;

import java.util.Random;

public class AccountNumberGenerator {
	
	public static Long getAccountNumber() {
	    String accountNo = String.format("%08d", new Random().nextInt(99999999));
	    return (long) Integer.parseInt(accountNo);
	}
}
