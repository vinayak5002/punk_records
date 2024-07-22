# Lambda Expression

`list.sort( (s1, s2) -> s1.compareTo(s2) );`

 - Use data types before the function parameter list if needed
 - The body of the expression can be enclosed with `{}` if it has multiple lines and return statement at the end
 - Lambda expression with no function parameters also possible

## Type of lambda expression
 - We can create a object of a expression. This is done by creating a Functional interface (an interface with only one abstract method)
 - We should create an object of functional interface and instantiating it with the lambda expression of the abstract method

```java
@FunctionalInterface
interface Operation{
	//Abstract Functional Method providing definition of lambda expression
	public double calculations(double num1,double num2); 
}
//Defining a lambda expression of type Operation
Operation doAdd  =  (double x, double y)-> x+y;
```

## Inbuilt Functional interface
### Function
`Function<Long, Long> plusTen = (val) -> val+10;`
### Predicate
`Predicate<Integer> isLegalAge = (age) -> age >= 18;`
### Suplier
`Supplier<Integer> generateRandom = ()-> new Integer((int) (Math.random() * 100));`
### Comsumer
`Consumer<String> printValue = (name)-> System.out.println(name);`

## Use
```java
interface Operation {
	public double opCriteria(double firstNum, double secondNum);
}

public static void main(String[] args) {
		
	Operation add = (x,y) -> x+y;
	Operation subtract = (x,y) -> x-y;
	Operation multiply = (x,y) -> x*y;
	Operation divide = (x,y) -> x/y;
		
	
	System.out.println("Result is: "+doOperation(12, 4, multiply));
}

public static double doOperation(double firstNum, double secondNum, Operation operator) {
	return operator.opCriteria(firstNum, secondNum);
}
```

## Consise
```java
List<String> strArr = List.of("Tyson", "Kai", "Max", "Ray", "Daichi");//List.of() returns immutable list of String with mentioned values
strArr.forEach(s -> System.out.println(s));                           //Printing List Using Lambda Expression

strArr.forEach(System.out::println); // Consice
```

# Streams
## Declaration
```java
List<String> castList = List.of("Sam","Dean","Castiel","Crowley");
Stream<String> supernatural = castList.stream();
```

```java
Integer[] array = {672, 340, 999};
Stream<Integer> stream = Arrays.stream(array);
```

```java
Stream<Integer> stream = Stream.of(672, 340, 999);
```

## Stream operations
### forEach()
Traverses through a stream and performs a lambda expression

`list.forEach( e -> sout("Element: " + e) );`

### map()
Returns a new Stream after performing an operation on all the elements on the stream

`Stream<E> placesUpper = places.map(place -> place.toUpperCase());`

### filter()
Returns new Stream after filtering it with a condition

`places.filter(place -> place.length == 5);`

### sorted()
sorts the stream
`placesToVisit.stream().sorted();`

`placesToVisit.stream().sorted((str1,str2) -> str1.compareTo(str2))`

### collect()
Used to convert the streams operation result as a collection

`List<Integer> doubled = number.stream().map(x->2*x).collect(Collectors.toList());`

 - [*] Operation can only be performed only once on a stream object

 - [*] Stream operations are lazy

# Properties
Its better to store configuration information in a separate file so that when we have to change we can just change the properties file instead of recompiling java classes because its hardcoded

`<filename>.properties`

 - Store data as Key-value pairs
```
USERNAME=vizz
PASSWORD=root
SERVER_URL=https://localhost:5002/
LOGIN_SUCCESS=Successfully logged in! 
```

## Using Properties
Using apache library
`org.apache.commons.configuration.PropertiesConfiguration`

```java
Configurations configurations = new Configurations();
PropertiesConfiguration config = configurations.properties("configuration.properties");
```

**Methods**
 - `Iterator<String> getKeys()` Returns an interator containing the keys present in the configuration file
 - `Object getProperty(String Key) Returns an object associated with the respective key

```java
Iterator<String> keys = proConfig.getKeys();

while(keys.hasNext()) {
	sout(proConfig.getProperty(keys.next()));
}
```

# Logging
Logging API's available
 - JDK Logging API
 - Apache Log4j2
 - Apache Commons Logging API

## Apache Log4j2
 - Log4j2 is configured using a properties file
 - Configuration has 3 parts
	- Logger: Redirects the logging information to the appender
	- Appender: Specifies the output destination
	- Layout: Specifies the output pattern

Ex:-
```
#Name of the properties file
name=LoggerConfigFile

#Declaring logger
rootLogger.level=info
rootLogger.appenderRef.file.ref=LoggerAppender

#File Appender
appender.file.name=LoggerAppender
appender.file.type=File
appender.file.fileName=log/ErrorLog.log

#Loggin pattern (Layout)
appender.file.layout.type=PatternLayout
appender.file.layout.pattern=%d{dd-MM-yyyy HH:mm:ss} %level - %m%n
```

### Logger
 - Specify logging level
 - Levels: Each of the level will log all the information of all the levels below
	- all	For all the levels
	- trace	Information events
	- debug	Information that would be useful for debugging
	- info	Information that highlights the progress of an application
	- warn	Potentially harmful situations
	- error	Errors that would permit the application to continue running
	- fatal	Server error that may abort the application
	- off

 - Specify the Appender component name
### Appender
 - Declare the name of the appender component
 - Specifying the destination. It can be
	- console
	- external file
	- HTML layout
 - Specify the name and location of the external file (if logging into external file)

###Layout
 - Specify what type of layout is needed. Can be
	- SimpleLayout
	- HTMLLayout
	- PatternLayout
 - Specify the pattern layout if needed

## Usage
A logger object has to be instantiated in the class from which the log has to come

### Apache commons 
```java
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserAuthentication {	
	Log logger = LogFactory.getLog(UserAuthentication.class);
}
```

### Log4j
```java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
	protected static final Logger logger = LogManager.getLogger();

}
```


To print something in the logger. call the methods from the logger objet created

`logger.<logging level>( Object ExceptionMessage,Throwable Exception object);`


