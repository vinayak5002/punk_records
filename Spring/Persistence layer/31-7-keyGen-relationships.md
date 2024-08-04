# Automatic key generation
Automatic key generation for MySQL

## Strategy for key generation
 - `IDENTITY`
 - `TABLE`
 - `SEQUENCE`
 - `AUTO`

For Oracle SQL, there is no `IDENTITY` strategy

## Usage
```sql
CREATE TABLE customer (
	customer_id int AUTO_INCREMENT,
	email_id varchar(20),
	name varchar(10),
	date_of_birth date,
	constraint ps_customer_id_pk primary key (customer_id)
);
```

```java
@Entity
public class Customer{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer customerId;
	
    // rest of the code
}
```

# Relationships

## One to One

`A` table with `B` table's foreign key -> `B` table

`A`: Customer `B`: Address

```sql
create table address(
	address_id int ,
	street varchar(30) not null,
	city varchar(10) not null,
	constraint ps_addressid_pk primary key (address_id)
);

create table customer (
	customer_id int auto_increment,
	address_id int unique,
	emailid varchar(25) not null,
	name varchar(10) not null,
	date_of_birth date not null,
	constraint ps_customerid_pk primary key (customer_id),
	constraint ps_customer_address_fk foreign key(address_id) references address(address_id)
);
```

```java
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer customerId;
	private String emailId;
	private String name;
	private LocalDate dateOfBirth;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", unique = true)
	private Address address;
	//getter and setter
}
```
Because `Customer` class as a foreign key of Address, it has a `Address` instance with `@OneToOne` and `@JoinColumn` annotations

`@OneToOne(cascade = CascadeType.ALL)` 
 - This annotation specifies that the association relationship has one-to-one multiplicity
 - The cascade attribute specifies that operations performed on the owner entity that must be transferred or cascaded to the target entity. The value `ALL` specifies that all operations performed on `Customer` will be cascaded to `Address`
	- If a customer is added, address record also will be added. Or if customer is deleted, address record will also be deleted
 - Other cascading options:
	- `PERSIST`
	- `REFRESH`
	- `REMOVE`
	- `MERGE`
	- `DETACH`

