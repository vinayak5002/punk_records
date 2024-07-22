# Regular expression
`import java.util.regex`

## matches()
Searches for a regex patter and returns a Boolean

`Boolean matches(String regex)`  
`<String object>.matches(regex)`

alter  
`Pattern.matches(regex, input)`

## split()
Splits the String into an array of Strings based on the regex passed

`Array<String> split(regex)`  
`<String object>.split(regex)`

## replaceFirst()
## replaceAll()

## Expressions
 - Use `()` to group parts of regex
 - `[]` for list of conditions
 - Quantifiers
	- `+` **One or more**
	- `*` **Zero or more**
	- `?` **Zero or one**
 - `.` Wild card
 - `^` Starts with
 - `$` Ends with
 - `{}` Exactly specified number of occurrences
	- `{m,}` Matches preceding exp. with m or more
	- `{m, n}` Matches any number between m and n (inclusive)
 - `[^]` Matches for characters except those in `[]`
 - `\n` Matches previous exp. `n` number of times
 - `|` Either or

 - `\w` Alphanumeric chars. and the underscore
 - `|W` Non-word characters
 - `\d` Digits
 - `\D` Non digits
 - `\s` White space characters `[\t \n \f \r]`
 - `\S` Non-Shite space characters

 - [*] In java we should use escape sequence to use metacharacters `"[\\w]{2}"`

Ex:- `"([A-Za-z0-9-_]+)[@]([a-z]+)[.](com|in)"` regex for email id

Ex:- `"([A-Za-z0-9]+) (\\d+) ([A-Z]+) [:] \\1 \\2 \\3"`  Here `\\1` refers to the first group `([A-Za-z0-9]+)` and `\\2` refers to the second group

Matches for "Tom123 9090 JERRY : Tom123 9090 JERRY"

Ex:- `"([^\\w])[0-9]{3,4}\\1"` will not match for `"#5002%"` as for `\\1` should be same as the first expression

"[A-Z][\w]* ([A-Z][\w]*)? ([A-Z][\w]*)?"

# Object class

## equals()
Override this method in any class to compare any two objects (compare the data members as required and return boolean)

## toString()
Override this method to return the required string when this class's object is used while printing

## hashCode()
Objects which are equal will have same hash codes. So for complex class's objects like Set, Stack, etc., if to objects are equal according to `equals()` then they will have same hashcode. So we need not check their DMs, instead we can compare their hashcode


# Final

## Before a instance variable
 - Before instance variable can't be changed.
 - The value of the final instance variable must be provided while declaration

## Before a method
 - This method can't be overridden by any child class

## Before a class
 - A final class can't be inherited

# Abstract 
 - Abstract class is not fully implemented. Other classes can inherit these class.
 - We can't instantiate object of an abstract class

## abstract methods
 - Is a method in an abstract class without any body
 - A class inheriting this abstract class **should** override all the abstract methods


# Interface
 - Similar to abstract class
 - Object can't be instantiated
 - All methods are public and abstract
 - An interface can have static methods also
 - All the fields are public static and final
 - All abstract methods **must** be implemented by the class inheriting the interface
 - If an abstract class is inheriting an interface, it need not implement all the abstract methods
 - A class can inherit more than one interface using `implement` keyword
 - An interface can inherit more than one interface too

 - A `default` method can be concrete, and it **can** be overridden by the class implementing the interface
 - A `static` method also can be concrete, and it **can't** be overridden by the class implementing the interface

 - **Sealed interface** are interfaces which restricts such that only some classes can implement the interface  
Ex:- `sealed interface ElectronicDevice permits SmartPhone, Laptop {...}`
	- Classes also can be sealed

