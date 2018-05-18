package com.luv2code.springdemo;

public class TrackCoach implements Coach {
	// declare local fortune helper
	private FortuneService fortune;
	
	// constructor DI
	public TrackCoach(FortuneService theFortune) {
		fortune = theFortune;
	}

	public TrackCoach() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getDailyWorkout() {
		return "Run a hard 5k";
	}

	@Override
	public String getFortune() {
		return "track coach: " + fortune.getFortune();
	}
	
	// add an init method
	public void doMyStartupStuff() {
		System.out.println("TrackCoach: do startup stuff");
	}
	
	// add a destroy method
	public void doMyDestroyStuff() {
		System.out.println("TrackCoach: do destroy stuff");
	}
}
