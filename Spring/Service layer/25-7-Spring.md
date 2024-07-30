# Spring
Spring framework (6.x)
 - spring core
 - spring data (ORM)
 - spring web
 - spring test
 - spring AOP (Aspect Oriented Programming)

Layers:
 - Controller (presentation)
 - Service
 - Repository (DAO)

Service <uses a> Repository object (Direct instantiation) :

Tight coupling
 - Service can't be tested sereratly
 - Its not a singleton object


## IOC (Inversion of Control) container

### Java-based configurations
```xml
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-context</artifactId>
	<version>6.1.11</version>
</dependency>
```

```java
@Configuration
public class SpringConf{
	
	@Bean
	public CusotmerLoginRepository customerLoginRepository() {
		return new CustomreLoginRespositoryImpl();
	}

	@Bean(name="customerService")
	public CustomLoginService customerLoginService() {
		return new CustomLoginServiceImpl(customerLoginRepository());
	}
}
```
 - Use `@Bean(name="...")` if you want the function to have a different name

```java
public class CustomerLoginController {

	CustomerLoginService customerLoginService;

	psvm() {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConf.class);
		
		customerLoginService = context.getBean(CustomerLoginServiceImple.class);

		//Getting bean using custom name
		customerLoginService = (CustomerLoginServiceImpl) context.getBean("customerService");
	}
}
```

### Java annotation based configuration
```java
@Configuration
@ComponentScan(basePackages="com.infy.repository com.infy.service com.infy.controller")
public class SpringConf{
	
}
```

Add component annotations into the service, repository, controller classes
 - @Component
	- @Controller
 	- @Service
	- @Repository
 - Use `value` property for the above annotations to use the different name as alter. to the whole class name

#### Autowired
When creating a service bean, there should be a repository bean in that. So we should have a constructor in service class which gets the repository bean

Instead add `@Autowired` annotation to a file of type of repository class and spring will automatically **inject** this dependency

```java
public class CustomerLoginController {

	CustomerLoginService customerLoginService;

	psvm() {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConf.class);
		
		customerLoginService = context.getBean(CustomerLoginServiceImple.class);

		//Getting bean using custom name
		customerLoginService = (CustomerLoginServiceImpl) context.getBean("customerService");
	}
}
```
### Property config
Adding annotation `@PropertySource("classpath:conifuguration.properties:)`

Getting properties
```java
ApplicationContext context = new AnnotationConfigApplicationContext(SpringConf.class);

Environment environment = context.getEnvironment()

environment.getProperty("some.proprety");
```

Using context, we can get `beans` and properties

