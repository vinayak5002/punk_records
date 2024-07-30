# Spring boot
Started code: `start.spring.io`

Implement `CommandLineRunner` interface in the main class and override the run() method

Main class
```java
package com.infy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class DemoSpringBootApplication  {
	
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootApplication.class, args);
	}
}
```
`@SpringBootApplication` Involves these 2 anootations:
 - @EnableAutoConfiguration: Automatically configures your application based on the dependencies you have added
 - @ComponentScan: All application components which are annotated with `@Component` `@Repository` and `@Controller` are automatically registered as Spring Beans
 - @Configuration: This enables Java based configurations for Spring boot application.

## Application properties
`application.properties` file is present in the resource folder
 - All the properties are loaded into the environment on spring boot `run()` 
 - Environment object can be autowired into a class to using the environment object `.getProperty()`


## Logging in Spring boot
Using common loggings

`private static final Log LOGGER = LogFactory.getLog(<main_class>.class)`

Using log4j2

`private static final Logger LOGGER = LogManager.getLogger(<main_class>.class);`

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```



# Aspect Oriented Programming (AOP)
The aspects are common to all layers
 - Trasnaction management
 - Security
 - Logging

```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

Seperate the code which is common for all

Join point: In Sping AOP a joint point is execution of a method

Point cut: represents a expression used to identify join points

 - Point cut expression: `execution(<modifiers> <return-type> <fully qualified class name>.<method-name>(parameters))`


## Logging:
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
			<exclusions>
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-logging</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
```

```java
@Component
@Aspect
public class LoggingAspect {

    private static final Log LOGGER = LogFactory.getLog(BootDemoApplication.class);

}
```

Advices for logging:
 - Before
 - After
 - AfterReturning
 - AfterThrowing
 - Around (Before and after calling the method)

