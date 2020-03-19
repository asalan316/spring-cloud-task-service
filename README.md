# Short Lived Microservice using spring-cloud-starter-task

Tasks in spring boot are generally used for short lived microservices.
This example microservice sends http GET request and log the response using Spring Cloud Task.

Use case

 - run microservice as task (short live microservice) and stop automatically once the HTTP request finished.
 - send HTTP GET inside commandLineRunner
 - configure Http base url in application.yml 
 - deploy service on Cloud Foundry

# Deploy service on Cloud Foundry

 1. compile app and create jar 
 
		 mvn clean install
		 
 2. Push an app using --health-check-type none and no-route parameters

    `cf push -f manifest-dev.yml --health-check-type none --no-route --no-start`
    
    Notice: In general, CF tasks has no routes as they are meant to perform background tasks.

 3. Run this app as cf task

	   `cf run-task task-service ".java-buildpack/open_jdk_jre/bin/java org.springframework.boot.loader.JarLauncher" --name "task-service"`

	 `syntax:
    cf.exe run-task APP_NAME COMMAND [-k DISK] [-m MEMORY] [--name TASK_NAME]`
    
# Trigger task automatically
 1. CF tasks can be scheduled using jobs. There is a service broker available in PCF marketplace.https://network.pivotal.io/products/p
 -scheduler-for-pcf
 
 ` cf create-job task-service task-service-job ".java-buildpack/open_jdk_jre/bin/java org.springframework.boot.loader.JarLauncher" `  

2 run job

`cf run-job task-service`

create schedule for the job. This enables job to be execute after defined time interval. The scheduler accepts MIN HOUR DAY-OF-MONTH
 MONTH DAY-OF-WEEK format. For instance, the following will execute task-service-job on 5th of every month at 10:00am

 `cf schedule-job task-service-job "0 10 5 * ? *" `
 
 
#Troubleshooting

- Pivotal Scheduler CLI plugin is required to interact with cf jobs
https://network.pivotal.io/products/p-scheduler-for-pcf

- The example spring boot app does not have "spring-boot-starter-web" dependency in pom.xml. Including the mentioned dependency will 
keep the app always in "Running" state. The goal is to create short-lived microservice as described in use case section. 
```
<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-web</artifactId>
 </dependency>  
```
# References

Spring Cloud Task:
[https://spring.io/projects/spring-cloud-task](https://spring.io/projects/spring-cloud-task)

cf tasks:
[https://docs.cloudfoundry.org/devguide/using-tasks.html](https://docs.cloudfoundry.org/devguide/using-tasks.html)

cf jobs:
[https://docs.pivotal.io/scheduler/1-2/using-jobs.html](https://docs.pivotal.io/scheduler/1-2/using-jobs.html)