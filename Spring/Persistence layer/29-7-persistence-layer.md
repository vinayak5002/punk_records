# Persistence layer

Entity package: Model class

## @Enity
To create objects of records in a data base

## @Id 
Added to a field of class to mark it as primary key

## @Table
When the class name of the DTO object and the table name are different, use `@Table(name="<table_name>")`

## Column
The fields of `@Entity` class will be automatically mapped to columns of the table provided they have same name but different case

Use `@Column(name = "date_of_birth")` to map a field with column

## Transient 
Applied to the fields in the entity class which are not to be mapped with a column

## Enumerated
To map a `enum` to a column.
 - Create a Enum
 - Add `@Enumerated(EnumType.STRING)` or leave () blank for Ordinal

# Using CRUD Repository

```java
public interface CustomerRepository extends CRUDRepository<T, ID> {
}
```

T: Entity class
ID: Type of primary key

Create a Repository bean in service class and use the inbuilt CRUDRepository methods to interact with database

## save()
`S save(S)`

Get the customer as DTO object, create a new Entity object and pass it to the save()

## saveAll()
Similar to save, but takes a iterable of `List<S>`

## findById()
`Optional<S> findById(T id)`

## existsById()
`Boolean existsById(T id)`

## findAll()
`Iterable<S> findAll()`

## findAllById()
`Iterable<S> findAllById(T id)`

## deleteById
`void deleteById(T id)`

## delete()
`void delete(S entity)`

## deleteAll()
`void deleteAll(Iterable<S> enitys)`

`void deleteAll()`: Deletes all records

## count()
`long count()`


# Model mapper
```xml
<dependency>
  <groupId>org.modelmapper</groupId>
  <artifactId>modelmapper</artifactId>
  <version>3.0.0</version>
</dependency>
```

```java
ModelMapper mapper = new ModelMaper();
CustomerDTO customerDto = mapper.map(customer, CustomreDTO.class);
```

 - Attributes must be same

 	
# Excluded portions:

 - ORM using JPA
	+ Creating Entity

 - Stored Procedures execution

 - Caching

 + Spring
	- Entity Manager

# Course TOC
 - Need for Spring data, intro to spring data, spring data repositories, CURD operations using spring data, ModelMapper

 - Intro to JPQL, wrt spring data, Selection - the SELECT clause, Restriction - The where clause, JPQL String functions, Grouping and ordering - the group by, Having and order by clause, update and delete queries wrt spring data, query approaches, Pagination and sorting using spring data, CRUD operation using mongodb, PostgreSQL

 - Primary key generation stratigies, One to One relationship, many to One, One to many relationship

