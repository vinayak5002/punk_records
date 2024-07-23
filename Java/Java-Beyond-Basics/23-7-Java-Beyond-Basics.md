# Junit

Build path > library > add library


# Lombox

Adding dependency to mave pom.xml
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.28</version>
    <scope>provided</scope>
</dependency>
```

 - `@Data` annotation will provide getters, setters, required feilds constructor, toString, hashCode methods will be generated automatically while compiling
 - `@Getter`
 - `@Setter`
 - `@NoArgsConstructor`
 - `@AllArgsConstructor`
 - `@NotNull` (For not null fields)
 - `@RequiredArgsConstructor` (Cons. for only non null fields)
 - `@ToString`
 - `@EqualsAndHashCode`
 - `@Equals`
 - `@HashCode`
 - `@Log` For apache commons logger
 - `@Log4` For Log4j2

# Multi threading
2 ways to create a thread
 - Extend `Thread` class
 - Implement `Runnable` interface

Life cycle
 - New born
 - Runnable: waiting to be scheduled
 - Running: thread is running on CPU
 - Non-Runable:
	- Waiting: waiting for the resource allocation
	- Sleep: thread put to sleep intentionally
	- Block: thread waiting for IO process
 - Dead: thread ended

## Thread
```java
class MyThread extends Thread {
	
	@Override
	public void run() {
		System.out.println("Thread created");
	}
	
}

public class MultiThreading {

	public static void main(String[] args ) {
		Thread t = new MyThread();
		
		t.start();
	}
	
}
```

## Runnable
```java
class MyThread implements Runnable {
	@Override
	public void run() {
		System.out.println("Thread created");
	}
}

public class MultiThreading {
	public static void main(String[] args ) {
		Runnable t = new MyThread();
		t.run();
	}
	
}
```

```java
Runnable t = () -> {System.out.println("Thread created");};
t.run();
```

## Methods
### start()
Call this method for running a thread extending Thread
### run()
Call this method for running a thread implementing Runnable
### setName()
### getName()
### currentThread()
### setPriority()
### getPriority()

## Synchronize
 - Use `synchronized` keyword in the function signature of a function which is operating on a shared resource
 - Use `synchronized` block
```java
synchronized(sync_object)
{
   // Access shared variables and other
   // shared resources
}
```

## Thread groups
