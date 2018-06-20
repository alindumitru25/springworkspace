package com.luv2code.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {
	
	private String name;
	private String serviceCode;
	
	public List<Account> getAccounts(boolean tripWire) {
		// simulate if true
		if (tripWire) {
			throw new RuntimeException("No soup for you");
		}

		List<Account> myAccounts = new ArrayList<>();
		
		Account acc1 = new Account("John", "Silver");
		Account acc2 = new Account("Madhu", "Platinum");
		Account acc3 = new Account("Luca", "Gold");
		
		myAccounts.add(acc1);
		myAccounts.add(acc2);
		myAccounts.add(acc3);
		
		return myAccounts;
	}
	
	public void addAccount(Account account, boolean vipFlag) {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACC\n");
	}
	
	public boolean doWork() {
		System.out.println(getClass() + ": DOING WORK\n");
		
		return true;
	}

	public String getName() {
		System.out.println(getClass() + ": getName()\n");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + ": setName()\n");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + ": getServiceCode()\n");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + ": setServiceCode()\n");
		this.serviceCode = serviceCode;
	}
	
}
