# 3 ways to write queries

## Method name
Examples:
 - findByEmailId(String email)
 - findByEmailIdAndDateOfBirth(String email, LocalDate date)
 - findByEmailIdOrDateOfBirth(String email, LocalDate date)
 - findByNameOrderByDateOfBirth(String name)  [Ascending order by default]
 - findByNameOrderByDateOfBirthDesc(String name)
 - findByNameLike(String pattern)
 - findByEmailIdNull()
 - findByEmailIdNotNull()

return type: `List<E>`

Keywords:
 - `Between`
 - `Equals`
 - `LessThan`
 - `LessThanEquals`
 - `GreaterThan`
 - `GreaterThanEqual`
 - `After`
 - `Before`
 - `Like`
 - `Null`
 - `NotNull`

## @Query
Named parameter:
```java
@Query("SELECT c FROM Customer c WHERE c.emailId = :emailId AND c.name = :name")
String findNameByEmailId(@Param("emailId") String emailId, String name); 
```

Positional parameter:
```java
@Query("SELECT c FROM Customer c WHERE c.emailId = ?1")
String findNameByEmailId(String emailId); 
```

 - Can't use both positional and named parameter at the same time

### Update queries
```java
@Query("UPDATE Customer c SET c.emailId = :emailId WHERE c.customerId = :customerId")
@Modifying
@Transactional
Integer updateCustomerEmailId(@Param("emailId") String updateCustomerByEmailId, @Param("customerId") Integer customerId);
```

## Named query
To define queries on the entity level

```java
@Entity
@Table(name="customer")
@NamedQuery(name="Customer.findNameByEmailId", query="SELECT c.name FROM Customer c WHERE c.emailId = :emailId")
public class Customer {
	@Id
	private Integer customerId;
	private String emailId;
	private String name;
	private LocalDate dateOfBirth;
	@Enumerated(value=EnumType.STRING)
	private CustomerType customerType;
    // getter and setter methods
}
```

```java
public interface CustomerRespository extends CrudRepository<Customer, Integer>{
	String findNameByEmailId(@Param("emailId") String emailId); 
}
```

## Aggregate functions
Functions:
 - `AVG()`
 - `SUM()`
 - `COUNT()`
 - `MIN()`
 - `MAX()`

## Update and delete queries
```
@Query("UPDATE Customer c SET c.emailId = :emailId WHERE c.customerName = :name")
@Modifying
@Transcational
public void updateCustomerEmailIdByName(@Param(emailId) String email, String name);


# Paging And Sorting

Extend interface `PagingAndSortingRepository<T, I>`
 - T: Repository of which Entity
 - I: Type of primary key of the entity

## Paging
`Page<T> findAll(Pagable pageable)`

### PageRequest
 - Creates an object which is used to specify which page we require and out of how many pages
 - `Pagable firstPageWithTwoRecords = PageRequest.of(0,2);`

### Pageable
 - `PageRequest` returns `Pagable` which can be passed to find method of `PagingAndSortingRepository`

### Page
 - Return type of find methos.
 - `getContent()` return the content of the requested page as a list
 - `hasContent()` return Boolean representing if page has content or not

## Sorting

## Sort
 - `by()`: `Sort sort = Sort.by("transactionDate")`
 - `descending()`: `Sort sort = Sort.by("transactionDate").descending()`
 - `ascending()`: `Sort sort = Sort.by("transactionDate").ascending()`
 - `Sort sort = Sort.by("transactionDate").descending().and(Sort.by("transactionAmount"))`

## Usage
```java
LocalDate transactionDate = LocalDate.of(1996, 1, 29);

Sort sort = Sort.by("transactionDate");

Pageable pageable = PageRequest.of(0,2,sort);

List<Transaction> transactionList = transactionRepository.findByTransactionDateAfter(transactionDate, pageable);
```

Or use `Iterable<T> findAll(Sort sort)`



