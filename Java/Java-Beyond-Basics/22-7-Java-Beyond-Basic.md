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

