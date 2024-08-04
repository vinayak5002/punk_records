# JDBC

Database API for java
 - Requires JDBC drivers for connection
 - JDBC can access tabular data from any relational database

Java application -> JDBC API -> DriverManager -JDBS Driver-> Java objects -> Database

 - Drivers are written by the database vendors

 - To connect to a driver we need:
	- Connection URL
	- Driver class
	- UserName
	- Password

## Steps
 - Load Driver class
 - Create connection
 - Create Statement
 - Execute the statement
 - Process Result

```java

static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

//Load driver class
Class.forName(JDBC_DRIVER);

//Create connection
static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc_demo";
static final String USER = "root";
static final String PASS = "infy";
Connection connection = null;
connection = DriverManager.getConnection(DB_URL, USER, PASS);

//Create Statement
Statement statement = connection.createStatement();

//Execute statement
String sql = "select * from employee";

ResultSet resultSet = statement.executeQuery(sql);

```

## Retrieving the data
```java
ResultSet resultSet = statement.executeQuery(sql);


while(resultSet.hasNext()) {
	String id = resultSet.getInt(1);
	String Name  = resultSet.getString(2);
	String param3 = result.getInt(3);
	
	sout(...);
	resultSet.next();
}
```

## Using
MySQL server should be running for API to work

Opening MySQL (Run these commands in 2 different console)
 - msqld --console
 - mysql -u root


# Optional class
- `public static <T> Optional<T> empty()`: Returns an empty Optional object. No value is present for this Optional.
 - `public static <T> Optional<T> of(T value)`: Returns an Optional with the specified present non-null value.
 - `public static <T> Optional<T> ofNullable(T value)`: Returns an Optional describing the specified value, if non-null, otherwise returns an empty Optional.
 - `public T get()`: If a value is present in this Optional, returns the value, otherwise throws NoSuchElementException.
 - `public boolean isPresent()`: Returns true if there is a value present, otherwise false.
 - `public void ifPresent(Consumer<? super T> consumer)`: If a value is present, invoke the specified consumer with the value, otherwise do nothing.
 - `public Optional<T> filter(Predicate<? super T> predicate)`: If a value is present, and the value matches the given predicate, return an Optional describing the value, otherwise return an empty Optional.
 - `public <U> Optional<U> map(Function<? super T,? extends U> mapper)`: If a value is present, apply the provided mapping function to it, and if the result is non-null, return an Optional describing the result. Otherwise return an empty Optional.
 - `public <U> Optional<U> flatMap(Function<? super T,Optional<U>> mapper)`: If a value is present, apply the provided Optional-bearing mapping function to it, return that result, otherwise return an empty Optional.
 - `public T orElse(T other)`: Returns the value if present, otherwise returns other.
 - `public T orElseGet(Supplier<? extends T> other)`: Returns the value if present, otherwise invoke other and return the result of that invocation.
 - `public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X extends Throwable`: Returns the contained value, if present, otherwise throw an exception to be created by the provided supplier.
 - `public boolean equals(Object obj)`: Indicates whether some other object is "equal to" this Optional or not. The other object is considered equal if: It is also an Optional and; Both instances have no value present or; the present values are "equal to" each other via equals().
 - `public int hashCode()`: Returns the hash code value of the present value, if any, or returns 0 (zero) if no value is present.
 - `public String toString()`: Returns a non-empty string representation of this Optional suitable for debugging. The exact presentation format is unspecified and may vary between implementations and versions.