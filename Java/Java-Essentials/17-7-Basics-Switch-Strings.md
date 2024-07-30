## Where java fits?
 - Large scale (Enterprise) applications
 - Scaling applications
 - Distributed systems

## Enterprise applications
 - Client side (React, angular, etc.,)
 - Server Side (Java, spring)

## To create server side part of enterprise applications
 - Platform independence and Performance


Courses:
 - Java: Essentials
 - Java: Beyond basics


# Enterprise applications

 - Client Tier
	- Angular, React
 - Presentation Tier
	- Handling data between user and the backend
	- Spring REST
 - Business Tier
	- Application's business logic
	- Spring core, POJO
 - Persistence Tier
	- Provides the data as objects
	- Spring Data

# Java Architecture
## JVM
 - Java Virtual Machine
 - A complied java program (called Byte code) is given to the JVM
 - JVM simulates this Byte code to run on the device its installed in
 - So Byte code can be executed in any machine that has JVM. Hence platform independence

## JIT
 - Just In Time compiler
 - All of the byte code is not compiled by the JVM to machine code
 - Byte code is compiled as the program is running

# OOPs
## Relationships between options
```java
class Vehicle {}	//( is-a relationship )
class Wheel {}		//( has-a relationship)
class Petrol {}		//( uses-a relationship)

class Car extends Vehicle {
	Wheel wheel = new Wheel();

	void method2(Petrol petrol) {
		
	}
}
```

# Switch expression
```java
int programmingLanguage = 1;
String languageName = switch (programmingLanguage) {
   case 1 -> “Java”;
   //…
};
```
 - [*] Each case should return the same type
 - [*] Default is mandatory

 - If there more than one statement to be put in a single case, enclose the statements with ; within a pair of {}. Last statement in this block should return the values with `yeild` keyword

 - We can throw an error in the default case if required

 - A single case can have multiple conditions separated with `,`. ex:- `case "Commercial","business" -> 600;`

Switch block:
```java
int programmingLanguage = 1;
String languageName;
switch (programmingLanguage) {
   case 1:
      languageName  = "Java";
      break;
   case 2:
      //...
}
```

# String class
In java, String is an object
 - String is an immutable object
 - Objects having the same string value will refer the same address

## String builder
StringBuilder objects are similar to String objects but are mutable
```java
StringBuilder name = new StringBuilder();
name.append("Oliver");
name.append(" Carter");
System.out.println(name);   //Output: Oliver Carter
```

### insert()
`StringBuilder insert(int offset, String str)`

Inserts the give string at the specified position

### reverse()
`StringBuilder reverse()`

Reverses the string on which the function is called

# Wrapper class
They are class made for primitive data types like int, float, etc,. So that they can be used in the templates in the data structures

`Integer` value is immutable

# Arrays
`int[] arr = new int[10];`
`int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};`

`int[][] arr = {{1, 2, 3},{4, 5, 6},{7, 8, 9}};`

## Unbalanced arrays
`int[][] array = {{1, 2, 3}, {4}, {7, 8, 9}};`

enhanced for loops
```java
for (int[] arr : array) {
	for (int ele : arr) {
		sout(ele + " ");	
	}
	sout("\n");
}
```

## Inbuilt methods
 - `Arrays.binarySearch(byte[], byte)` return an index or -1
 - `Arrays.equals(num1, num2)` return Boolean


# Enumeration
```java
enum PizzaSize {
    SMALL, MEDIUM, LARGE;
}
```

