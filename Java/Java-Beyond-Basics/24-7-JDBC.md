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


 