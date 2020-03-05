# Short Live Microservice using Spring Boot Task

Tasks in spring boot are used for short lived microservices.
This microservice sends http GET request and log the response using Spring Cloud Task.

Use case

 -  run microservice as task (short live microservice) and stop automatically once the HTTP request finished.
 - send HTTP GET inside commandLineRunner
 - configure Http base url in application.yml 
 - deploy service on Cloud Foundry

# Deploy service on Cloud Foundry

 1. compile app and create jar 
 
		 mvn clean install
		 
 2. Push an app using --health-check-type none and no-route parameters

    `cf push -f manifest-dev.yml --health-check-type none --no-route --no-start`

 3. Run this app as cf task

	   `cf run-task task-service ".java-buildpack/open_jdk_jre/bin/java org.springframework.boot.loader.JarLauncher" --name "task-service"`

	 `syntax:
    cf.exe run-task APP_NAME COMMAND [-k DISK] [-m MEMORY] [--name TASK_NAME]`
    

 
# References

Spring Cloud Task:
[https://spring.io/projects/spring-cloud-task](https://spring.io/projects/spring-cloud-task)

cf tasks:
[https://docs.cloudfoundry.org/devguide/using-tasks.html](https://docs.cloudfoundry.org/devguide/using-tasks.html)

