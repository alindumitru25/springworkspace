package com.luv2code.springdemo;

public class BaseballCoach implements Coach {
	// declare local fortune helper
	private FortuneService fortune;
	
	// constructor DI
	public BaseballCoach(FortuneService theFortune) {
		fortune = theFortune;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Spend 30 minutes on batting practice";
	}
	
	@Override
	public String getFortune() {
		return fortune.getFortune();
	}
}
