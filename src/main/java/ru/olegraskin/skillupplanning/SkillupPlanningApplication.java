package ru.olegraskin.skillupplanning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(LiquibaseProperties.class)
public class SkillupPlanningApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillupPlanningApplication.class, args);
	}

}
