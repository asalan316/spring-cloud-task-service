package com.ak.cloud.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

@EnableTask
@SpringBootApplication
public class TaskServiceApplication {

	private static final Logger logger = LoggerFactory
			.getLogger(TaskServiceApplication.class);

	public static void main(final String[] args) {

		logger.info("TaskAppApplication started");
		SpringApplication.run(TaskServiceApplication.class, args);
		logger.info("TaskAppApplication finished");
	}
}
