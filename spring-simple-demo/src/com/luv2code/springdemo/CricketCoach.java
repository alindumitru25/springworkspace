package com.luv2code.springdemo;

public class CricketCoach implements Coach {
	private FortuneService fortuneService;
	
	// add new fields for emailAddr & team
	private String emailAddress;
	private String team;
	
	// create a no-arg constructor - calls when reference the bean
	public CricketCoach() {
		System.out.println("CricketCoach: inside no-arg constr");
	}
	
	// setter method for DI
	public void setFortuneService(FortuneService fortuneService) {
		System.out.println("CricketCoach: inside setter method - setFortuneService");
		this.fortuneService = fortuneService;
	}
	
	public void setEmailAddress(String emailAddress) {
		System.out.println("CricketCoach: inside setter method - setEmailAddress");
		this.emailAddress = emailAddress;
	}
	
	public String getEmailAddress() {
		return this.emailAddress;
	}
	
	public void setTeam(String team) {
		System.out.println("CricketCoach: inside setter method - setTeam");
		this.team = team;
	}
	
	public String getTeam() {
		return team;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Fasting fast bowling for 40 min ";
	}

	@Override
	public String getFortune() {
		return fortuneService.getFortune();
	}
}
