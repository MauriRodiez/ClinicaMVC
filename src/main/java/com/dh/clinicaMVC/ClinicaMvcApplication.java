package com.dh.clinicaMVC;

import com.dh.clinicaMVC.db.databaseConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

@SpringBootApplication
public class ClinicaMvcApplication {

	public static void main(String[] args) {
		databaseConnection.getDbConnect();
		SpringApplication.run(ClinicaMvcApplication.class, args);
	}

}
