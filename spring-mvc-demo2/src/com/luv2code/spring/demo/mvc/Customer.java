package com.luv2code.spring.demo.mvc;

import java.util.LinkedHashMap;

import javax.validation.constraints.Size;

import com.luv2code.spring.demo.mvc.validation.CourseCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Customer {

	private String firstName;
	
	@NotNull(message="should not be empty")
	@Size(min=1, message="is required")
	private String lastName;
	private String country;
	private String favoriteLanguage;
	
	@NotNull(message="required")
	@Min(value=0, message="Should be greater or equal with 0")
	@Max(value=10, message="Should be smaller or equal with 10")
	private Integer freePasses;
	
	@Pattern(regexp="^[a-zA-Z0-9]{5}", message="only 5 chars/digits")
	private String postalCode;
	
	private LinkedHashMap<String, String> countryOptions;
	
	@CourseCode(value="TOPS", message="must start with TOPS")
	private String courseCode;
	
	public Customer() {
		// populate country options
		this.countryOptions = new LinkedHashMap<>();
		this.countryOptions.put("RO", "Romania");
		this.countryOptions.put("CH", "China");
		this.countryOptions.put("US", "USA");
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public LinkedHashMap<String, String> getCountryOptions() {
		return countryOptions;
	}

	public void setCountryOptions(LinkedHashMap<String, String> countryOptions) {
		this.countryOptions = countryOptions;
	}

	public String getFavoriteLanguage() {
		return favoriteLanguage;
	}

	public void setFavoriteLanguage(String favoriteLanguage) {
		this.favoriteLanguage = favoriteLanguage;
	}

	public Integer getFreePasses() {
		return freePasses;
	}

	public void setFreePasses(Integer freePasses) {
		this.freePasses = freePasses;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
}
