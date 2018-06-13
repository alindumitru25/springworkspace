package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	public void addAccount() {
		System.out.println(getClass() + ": DOING STUFF: ADDING MEMBERSHIP ACC");
	}
	
	public String addSillyMember() {
		System.out.println(getClass() + ": DOING STUFF: ADDING SILLY MEMBERSHIP ACC");
		return "right";
	}
	
}
