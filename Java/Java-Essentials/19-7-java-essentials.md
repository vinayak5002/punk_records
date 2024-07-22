# Exception handling
Throwable -> Exception
Throwable is a super class of exception

Only objects of subclasses of Throwable classes can be thrown. Inherit Exception class to create your own exception

```java
try {
    // Code that can throw exceptions
}
catch(Exception1 exception1) {
    // Code for handling Exception1
}
catch(Exception2 exception2) {
    // Code for handling Exception2
}
```

 - Each exception has methods which can be usefull in debegging
	- `String getMessage()` Returns the description of an exception
	- `void printStackTrace()` Displayes the stack trace

## Checked Exception
 - If not handled by the programmer, these exceptions will be detected during the compilation of the program which will result in compilation error
 - Programmers are forced to handle these exceptions or declare its propagation to the calling environment

## Unchecked Exception
 - These exceptions are detected during the execution of the program or the runtime, hence causing an error
 - Programmers are neither forced to handle it nor declare its propagation

## Finally
The `finally` block which is at the end of all catch blocks will be executed even if the error occurs or not

## Throwing Exceptions
```java
public static void checkStock(int stockAvailable, int quantityRequired) {
    try {      
        if(stockAvailable < quantityRequired)
            throw new Exception("There is not enough stock available.");
        System.out.println("Please proceed to the check-out");   
    }
    catch(Exception e) {
        System.out.println(e.getMessage());
    }
}
```

## Try with resources
When resource which should be closed after using them in try block are used in try block, we should close them in finally block

But while using `AutoClose` resources within try block, the resources will be automatically closed

To create your own `AutoClose` resource, implement `AutoClose` interface and override the `void close() throws IOException` method

```java
class SampleResource implements AutoCloseable {
      public String name;
      public SampleResource(String name) {
            this.name = name;
      }
      @Override
      public void close() throws IOException {
            System.out.println("Closing resource : " + name);
      }
}
public class Testers {
      public static void main(String[] args) throws IOException {
            try (SampleResourceresource = new SampleResource("Sample Resource")) {
                  System.out.println("Started Using resource : " + resource.name);
            } catch (IOException e) {
                  System.out.println(e.getMessage());
            }
      }
}
```

# Collection framework
![image](https://github.com/user-attachments/assets/5519aee3-13a6-4a0c-8c53-41d2df87c687)
## List
### ArrayList
 - No need to specify the size
`List<<type>> list = new ArrayList<type>()`

 - `add(Object element)` adds the element to the end of the list
 - `int size()` returns size
 - `addAll(Collection collection)` appends all the elements in the passed collection into the array list
 - `addAll(int n, Collection collection)` appends all the elements in the passed collection into the array list from the nth position
 - `add(int m, Object element)` inserts the element into the mth position
 - `get(int n)` returns the object in the nth place of the list
 - `void clear()` removes all elements 
 - `boolean contains()` returns true if element present in the list
 - `int indexOf(Object element)` returns the first occurrence of the specific element
 - `Boolean isEmpty()` returns true if list is empty
 - `void sort(Comparator c)` sorts using the given comparator

#### Iterator
`Iterator<Object> itr = list.iterator();`
Methods
 - `boolean hasNext()` returns true if the iteration has any more elements left
 - `Object next()` returns the next object in the List
 - `void remove()` removes the element at the iterator

```java
ListIterator<String> literator = nameList.listIterator();
		
//iterating in forward direction
while(literator.hasNext()) {
	System.out.println(literator.next());
}			
//iterating in reverse direction
while(literator.hasPrevious()) {
	System.out.println(literator.previous());
}
```

#### Comparable and Comparator
**Comparable**
 - `implement Comparable<E>` interface into the class of the objects needed to be sorted
 - Override int `int compareTo(E o)`	
	- `return -1` for less than
	- `return 0` for equal to
	- `return 1` for greater than
Sorting: `Collections.sort(list)`

**Comparator**
Create a new class that `implements Comparator<E>` and overrides `int compare(E a, E b)`

```java
class Sortbyroll implements Comparator<Student> {
 
    // Method
    // Sorting in ascending order of roll number
    public int compare(Student a, Student b)
    {
 
        return a.rollno - b.rollno;
    }
}
```
Sorting: `Collections.sort(list, <Comparator class>)`

### LinkedList
Same as `ArrayList` (random access also can be done). But internally implemented as a linked list

Difference between ArrayList and LinkedList
 - ArrayList is good for frequent use of random access
 - LinkedList is good for frequent use of inserting in between

## Set
Stores only unique elements

### HashSet
`Set<E> set = new HashSet<E>()`

 - Order of the elements are not in the order of insertion as internal implementation uses hashing

 - To differentiate object using custom criteria, override `hashCode()` or `equals()` in the class

Functions available:
 - `void add(E ele)`
 - `E get()`

### TreeSet
`Set<E> set = new TreeSet<E>()`

Same as a HashSet the but stored objects in a sorted order

### LinkedHashSet
`Set<E> set = new LinkedHashSet()`

Same as the Set but stores element in the FIFO order

## Map
### HashSet
`HashMap<K, V> map = new HashMap<E, S>();`

 - `void put<K k, V v>`
 - `void remove<K key>`
 - `void get<K key>`
 - `boolean replace(K key, V oldValue, V newValue)`
 - `void clear()` removes all key-value pairs
 - `boolean isEmpty()`

### LinkedHashMap
Same as HashMap but the elements are in the order of insertion

### TreeMap
Same as HashMap but the elements are in the natural order of key.
 - TreeMap uses compareTo() method instead of equals()