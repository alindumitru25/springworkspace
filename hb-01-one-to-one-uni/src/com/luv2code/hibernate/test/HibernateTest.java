package com.luv2code.hibernate.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class HibernateTest {
	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "hbstudent";
		String pass = "hbstudent";

		try {
			Connection conn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Success");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
