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
```java
Runnable t = () -> {System.out.println("Thread created");};

Thread t = new Thread(t);
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
```java
package com.infy.thread;
public class ThreadGroupDemo {
	public static void main(String[] args) {
		ThreadGroup threadGroup = new ThreadGroup("ThreadGroup");
		Runnable r = () -> System.out.println("Thread created is :"+Thread.currentThread().getName());
		var t1 = new Thread(threadGroup,r,"one");
		var t2 = new Thread(threadGroup,r,"two");
		var t3 = new Thread(threadGroup,r,"three");
		t1.start();
		t2.start();
		t3.start();
		System.out.println("Thread Group Name: "+threadGroup.getName());
		threadGroup.list();
	}
}
```
Thread constructor `Thread(ThreadGroup group, Runnable target, String name)`

# Concurrency (Executor package)
`import java.util.concurrent.*;`
## ThreadPool
```java
    public static void main(String[] args) 
    { 
        // creates five tasks 
        Runnable r1 = new Task("task 1"); 
        Runnable r2 = new Task("task 2"); 
        Runnable r3 = new Task("task 3"); 
        Runnable r4 = new Task("task 4"); 
        Runnable r5 = new Task("task 5");       
          
        // creates a thread pool with MAX_T no. of  
        // threads as the fixed pool size(Step 2) 
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);   
         
        // passes the Task objects to the pool to execute (Step 3) 
        pool.execute(r1); 
        pool.execute(r2); 
        pool.execute(r3); 
        pool.execute(r4); 
        pool.execute(r5);  
          
        // pool shutdown ( Step 4) 
        pool.shutdown();     
    } 
```

## Callable and Future
 - Create a `ExecutorService` object
 - `ExecutorService .execute()` is for Runnable tasks only
 - `ExecutorService .submit()` is for Callable tasks also

 - The callable passed to `.submit()` will run asynchronously and will return something in future.
	- The program will continue running after submitting the callable task
	- And all the "submitted" callable tasks will run concurrently (if cpu cores are available)

```java
public class FutureExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        
        // Submit a task and obtain a Future object
        Future<String> future = executorService.submit(() -> {
            Thread.sleep(2000);
            return "Hello, Future!";
        });
        
        // Perform other tasks while the computation is in progress
        
        // Wait for the result and retrieve it
        String result = future.get();
        System.out.println(result);
        
        // Shutdown the executor service
        executorService.shutdown();
    }
}
```

 - call `executor.shutdown()` after all the futures are over

### invokeAny()
 - Takes in a collection of callable tasks
 - Returns type is V from `Callable<V>`
 - It is not `Future<V>` because the main thread is blocked till one of the thread finishes or throws an exception

```java
ExecutorService executorService = Executors.newSingleThreadExecutor();

Set<Callable<String>> callables = new HashSet<Callable<String>>();

callables.add(new Callable<String>() {
    public String call() throws Exception {
        return "Task 1";
    }
});
callables.add(new Callable<String>() {
    public String call() throws Exception {
        return "Task 2";
    }
});
callables.add(new Callable<String>() {
    public String call() throws Exception {
        return "Task 3";
    }
});

String result = executorService.invokeAny(callables);

System.out.println("result = " + result);

executorService.shutdown();
```

### invokeAll()
 - Takes in a collection of callable tasks
 - Returns type is List<Future<V>> where V is from `Callable<V>`
 - Main thread is blocked until all the `List<Future<V>>` are returned

```java
ExecutorService executorService = Executors.newSingleThreadExecutor();

Set<Callable<String>> callables = new HashSet<Callable<String>>();

callables.add(new Callable<String>() {
    public String call() throws Exception {
        return "Task 1";
    }
});
callables.add(new Callable<String>() {
    public String call() throws Exception {
        return "Task 2";
    }
});
callables.add(new Callable<String>() {
    public String call() throws Exception {
        return "Task 3";
    }
});

List<Future<String>> futures = executorService.invokeAll(callables);

for(Future<String> future : futures){
    System.out.println("future.get = " + future.get());
}

executorService.shutdown();
```


## Creating a Callable
```java
public interface Callable<V> {
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    V call() throws Exception;
}
```

- Implement `Callable<V>` here V is the type of the `Future` that callable is gonna return

