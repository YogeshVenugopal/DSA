# ArrayList & List Types Cheatsheet

## 1. ArrayList Basics

### What is ArrayList?
ArrayList is a resizable array implementation of the List interface. It can grow or shrink dynamically as you add or remove elements.

```java
// Syntax
ArrayList<DataType> listName = new ArrayList<>();
```

### Declaration & Initialization

```java
// 1. Basic declaration
ArrayList<Integer> numbers = new ArrayList<>();

// 2. With initial capacity
ArrayList<String> fruits = new ArrayList<>(10); // capacity = 10

// 3. Initialize with values
ArrayList<String> colors = new ArrayList<>(Arrays.asList("Red", "Blue", "Green"));

// 4. Without generics (not recommended)
ArrayList list = new ArrayList(); // Raw type - causes warnings
```

---

## 2. Basic ArrayList Operations

### Adding Elements

```java
ArrayList<String> fruits = new ArrayList<>();

// add(E e) - Adds element at the end
fruits.add("Apple");
fruits.add("Banana");
fruits.add("Cherry");
// Output: [Apple, Banana, Cherry]

// add(int index, E element) - Adds at specific index
fruits.add(1, "Mango");
// Output: [Apple, Mango, Banana, Cherry]

// addAll(Collection c) - Adds all elements from another collection
ArrayList<String> moreFruits = new ArrayList<>(Arrays.asList("Date", "Elderberry"));
fruits.addAll(moreFruits);
// Output: [Apple, Mango, Banana, Cherry, Date, Elderberry]

// addAll(int index, Collection c) - Adds at specific index
fruits.addAll(2, moreFruits);
// Output: [Apple, Mango, Date, Elderberry, Banana, Cherry, Date, Elderberry]
```

### Accessing Elements

```java
ArrayList<String> fruits = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry"));

// get(int index) - Returns element at index
String first = fruits.get(0); // "Apple"
String second = fruits.get(1); // "Banana"

// Iteration - Enhanced For Loop
for (String fruit : fruits) {
    System.out.println(fruit);
}

// Iteration - Traditional For Loop
for (int i = 0; i < fruits.size(); i++) {
    System.out.println(fruits.get(i));
}

// Iteration - Iterator
Iterator<String> iterator = fruits.iterator();
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}

// Iteration - forEach (Java 8+)
fruits.forEach(fruit -> System.out.println(fruit));
```

### Modifying Elements

```java
ArrayList<String> fruits = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry"));

// set(int index, E element) - Replaces element at index
fruits.set(1, "Orange");
// Output: [Apple, Orange, Cherry]

// Example - Replace multiple elements
for (int i = 0; i < fruits.size(); i++) {
    fruits.set(i, fruits.get(i).toUpperCase());
}
// Output: [APPLE, ORANGE, CHERRY]
```

### Removing Elements

```java
ArrayList<String> fruits = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry", "Date"));

// remove(int index) - Removes element at index
fruits.remove(1); // Removes "Banana"
// Output: [Apple, Cherry, Date]

// remove(Object o) - Removes first occurrence of object
fruits.remove("Cherry"); // Removes first "Cherry"
// Output: [Apple, Date]

// removeAll(Collection c) - Removes all specified elements
ArrayList<String> toRemove = new ArrayList<>(Arrays.asList("Apple", "Date"));
fruits.removeAll(toRemove);
// Output: []

// removeIf(Predicate) - Removes elements matching condition (Java 8+)
fruits.add("Apple");
fruits.add("Banana");
fruits.removeIf(fruit -> fruit.startsWith("A"));
// Output: [Banana]

// clear() - Removes all elements
fruits.clear();
// Output: []
```

### Searching Elements

```java
ArrayList<String> fruits = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry", "Banana"));

// indexOf(Object o) - Returns index of first occurrence (-1 if not found)
int index = fruits.indexOf("Banana"); // 1

// lastIndexOf(Object o) - Returns index of last occurrence
int lastIndex = fruits.lastIndexOf("Banana"); // 3

// contains(Object o) - Checks if element exists
boolean exists = fruits.contains("Apple"); // true
boolean notExists = fruits.contains("Mango"); // false

// isEmpty() - Checks if ArrayList is empty
boolean empty = fruits.isEmpty(); // false

// size() - Returns number of elements
int size = fruits.size(); // 4
```

### Utility Operations

```java
ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));

// sort() - Sorts the list
Collections.sort(numbers);
// Output: [1, 2, 5, 8, 9]

// reverse() - Reverses the order
Collections.reverse(numbers);
// Output: [9, 8, 5, 2, 1]

// shuffle() - Randomly shuffles elements
Collections.shuffle(numbers);
// Output: [5, 9, 1, 8, 2] (varies)

// min() - Finds minimum element
int min = Collections.min(numbers); // 1

// max() - Finds maximum element
int max = Collections.max(numbers); // 9

// copy() - Copies one list to another
ArrayList<Integer> copy = new ArrayList<>();
Collections.copy(copy, numbers); // Destination must be at least as large

// frequency() - Counts occurrences
ArrayList<String> fruits = new ArrayList<>(Arrays.asList("Apple", "Banana", "Apple"));
int count = Collections.frequency(fruits, "Apple"); // 2

// swap() - Swaps two elements
Collections.swap(numbers, 0, 4);

// fill() - Fills all elements with a value
Collections.fill(numbers, 0);
// Output: [0, 0, 0, 0, 0]
```

---

## 3. List Interface & Implementations

### List Hierarchy

```
Collection
    ↓
  List Interface
    ├── ArrayList (Resizable array implementation)
    ├── LinkedList (Doubly linked list)
    ├── Vector (Legacy, synchronized ArrayList)
    ├── Stack (Extends Vector, LIFO structure)
    └── CopyOnWriteArrayList (Thread-safe)
```

### Creating Different List Types

```java
// ArrayList - Most commonly used
List<String> list1 = new ArrayList<>();

// LinkedList - Better for insertions/deletions at beginning/middle
List<String> list2 = new LinkedList<>();

// Vector - Legacy, synchronized (not recommended)
List<String> list3 = new Vector<>();

// Immutable List
List<String> list4 = Collections.unmodifiableList(new ArrayList<>());

// Fixed-size List from array
List<String> list5 = Arrays.asList("A", "B", "C");

// Immutable List (Java 9+)
List<String> list6 = List.of("A", "B", "C");
```

---

## 4. ArrayList vs LinkedList

| Feature | ArrayList | LinkedList |
|---------|-----------|-----------|
| **Internal Structure** | Array | Doubly Linked List |
| **Access Time (get/set)** | O(1) Fast | O(n) Slow |
| **Insertion (at end)** | O(1) Fast | O(1) Fast |
| **Insertion (at beginning/middle)** | O(n) Slow | O(1) Fast |
| **Deletion (at end)** | O(1) Fast | O(1) Fast |
| **Deletion (at beginning/middle)** | O(n) Slow | O(1) Fast |
| **Memory Overhead** | Low | High (pointers) |
| **Best Use Case** | Search-heavy operations | Frequent insertions/deletions |

### Example Comparison

```java
// ArrayList - Better for frequent access
ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
for (int i = 0; i < 1000000; i++) {
    arrayList.get(i % arrayList.size()); // Fast access
}

// LinkedList - Better for frequent insertions at beginning
LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
for (int i = 0; i < 1000; i++) {
    linkedList.addFirst(i); // O(1) operation
}
```

---

## 5. LinkedList Specific Operations

```java
LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "C"));

// addFirst(E e) - Adds at beginning
list.addFirst("Z");
// Output: [Z, A, B, C]

// addLast(E e) - Adds at end
list.addLast("D");
// Output: [Z, A, B, C, D]

// removeFirst() - Removes first element
list.removeFirst(); // Removes "Z"
// Output: [A, B, C, D]

// removeLast() - Removes last element
list.removeLast(); // Removes "D"
// Output: [A, B, C]

// getFirst() - Gets first element
String first = list.getFirst(); // "A"

// getLast() - Gets last element
String last = list.getLast(); // "C"

// peek() - Returns first element without removing (returns null if empty)
String peek = list.peek(); // "A"

// poll() - Removes and returns first element (returns null if empty)
String polled = list.poll(); // "A"
list.forEach(System.out::println); // Output: B, C

// push(E e) - Adds element at beginning (Stack-like)
list.push("X");
// Output: [X, B, C]

// pop() - Removes and returns first element (Stack-like)
String popped = list.pop(); // "X"
// Output: [B, C]
```

---

## 6. Vector (Legacy Implementation)

```java
Vector<Integer> vector = new Vector<>();

// Vector is synchronized (thread-safe but slower)
vector.add(1);
vector.add(2);
vector.add(3);

// Similar operations to ArrayList
vector.remove(0);
vector.set(0, 10);

// Vector-specific methods
vector.firstElement(); // Gets first element
vector.lastElement();  // Gets last element
vector.removeElement(10); // Removes first occurrence

// NOTE: Vector is legacy and generally not recommended
// Use ArrayList instead, or CopyOnWriteArrayList for thread-safety
```

---

## 7. Collections Utility Methods

```java
ArrayList<String> list = new ArrayList<>(Arrays.asList("B", "A", "C"));

// Sorting
Collections.sort(list); // Natural order: [A, B, C]

// Custom sorting - Reverse order
Collections.sort(list, Collections.reverseOrder());
// Output: [C, B, A]

// Custom sorting - Custom comparator
Collections.sort(list, new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return b.compareTo(a); // Descending
    }
});
// Output: [C, B, A]

// Lambda version (Java 8+)
list.sort((a, b) -> b.compareTo(a));

// Check if list is sorted
boolean sorted = list.equals(new ArrayList<>(Arrays.asList("A", "B", "C")));

// Binary search (requires sorted list)
list.sort(Comparator.naturalOrder());
int index = Collections.binarySearch(list, "B"); // 1

// Rotate
Collections.rotate(list, 1); // Rotates right by 1

// Shuffle
Collections.shuffle(list);

// Synchronize a list (thread-safe wrapper)
List<String> syncList = Collections.synchronizedList(new ArrayList<>());

// Make list immutable
List<String> immutable = Collections.unmodifiableList(list);
// immutable.add("X"); // Throws UnsupportedOperationException
```

---

## 8. Stream API with Lists (Java 8+)

```java
ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

// filter() - Filter elements
List<Integer> evenNumbers = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());
// Output: [2, 4]

// map() - Transform elements
List<Integer> squared = numbers.stream()
    .map(n -> n * n)
    .collect(Collectors.toList());
// Output: [1, 4, 9, 16, 25]

// forEach() - Iterate
numbers.stream()
    .forEach(System.out::println);

// find() - Find first matching element
Integer firstEven = numbers.stream()
    .filter(n -> n % 2 == 0)
    .findFirst()
    .orElse(null); // 2

// reduce() - Combine elements
int sum = numbers.stream()
    .reduce(0, Integer::sum); // 15

int product = numbers.stream()
    .reduce(1, (a, b) -> a * b); // 120

// sorted() - Sort stream
List<Integer> sorted = numbers.stream()
    .sorted()
    .collect(Collectors.toList());
// Output: [1, 2, 3, 4, 5]

// distinct() - Remove duplicates
ArrayList<Integer> withDuplicates = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 3, 3));
List<Integer> unique = withDuplicates.stream()
    .distinct()
    .collect(Collectors.toList());
// Output: [1, 2, 3]

// count() - Count elements
long count = numbers.stream()
    .filter(n -> n > 2)
    .count(); // 3

// toMap() - Convert to map
Map<Integer, String> map = numbers.stream()
    .collect(Collectors.toMap(
        n -> n,           // key
        n -> "Number: " + n // value
    ));
```

---

## 9. Practical Examples

### Example 1: Student Management

```java
class Student {
    String name;
    int age;
    double gpa;

    Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return name + " (Age: " + age + ", GPA: " + gpa + ")";
    }
}

ArrayList<Student> students = new ArrayList<>();
students.add(new Student("Alice", 20, 3.8));
students.add(new Student("Bob", 21, 3.5));
students.add(new Student("Charlie", 20, 3.9));

// Find students with GPA > 3.7
List<Student> topStudents = students.stream()
    .filter(s -> s.gpa > 3.7)
    .collect(Collectors.toList());
// Output: [Alice, Charlie]

// Sort by GPA (descending)
students.sort((a, b) -> Double.compare(b.gpa, a.gpa));

// Get names of all students
List<String> names = students.stream()
    .map(s -> s.name)
    .collect(Collectors.toList());
// Output: [Charlie, Alice, Bob]
```

### Example 2: List Manipulation

```java
ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6));

// Remove duplicates
List<Integer> unique = new ArrayList<>(new HashSet<>(numbers));
// Output: [1, 2, 3, 4, 5, 6, 9] (order may vary)

// Remove even numbers in-place
numbers.removeIf(n -> n % 2 == 0);
// Output: [3, 1, 5, 9]

// Find max and min
int max = Collections.max(numbers);
int min = Collections.min(numbers);
// max = 9, min = 1

// Get sublist
List<Integer> sublist = numbers.subList(1, 3); // [1, 5]

// Rotate list
ArrayList<Integer> rotated = new ArrayList<>(numbers);
Collections.rotate(rotated, 2);
// Output: [5, 9, 3, 1]
```

### Example 3: Working with Strings

```java
ArrayList<String> words = new ArrayList<>(Arrays.asList("Java", "Python", "Go", "Rust"));

// Convert all to lowercase
words.replaceAll(String::toLowerCase);
// Output: [java, python, go, rust]

// Filter by length
List<String> shortWords = words.stream()
    .filter(w -> w.length() <= 4)
    .collect(Collectors.toList());
// Output: [java, go]

// Join as string
String joined = String.join(", ", words);
// Output: "java, python, go, rust"

// Split and create ArrayList
ArrayList<String> fromString = new ArrayList<>(
    Arrays.asList("apple,banana,cherry".split(","))
);
// Output: [apple, banana, cherry]
```

---

## 10. Performance Tips

```java
// 1. Capacity Optimization
ArrayList<Integer> list1 = new ArrayList<>(100); // Avoids resizing

// 2. Avoid frequent insertions at beginning
// ❌ BAD
for (int i = 0; i < 1000; i++) {
    list.add(0, i); // O(n) for each operation
}

// ✅ GOOD
LinkedList<Integer> linkedList = new LinkedList<>();
for (int i = 0; i < 1000; i++) {
    linkedList.addFirst(i); // O(1) for each operation
}

// 3. Use addAll() instead of multiple add() calls
// ❌ BAD
for (String item : items) {
    list.add(item);
}

// ✅ GOOD
list.addAll(items);

// 4. Use trimToSize() to reduce memory usage
ArrayList<String> list = new ArrayList<>(100);
// ... add only 10 elements
list.trimToSize(); // Reduces capacity to 10

// 5. removeIf() is more efficient than manual removal
// ❌ BAD
for (int i = list.size() - 1; i >= 0; i--) {
    if (list.get(i) % 2 == 0) {
        list.remove(i);
    }
}

// ✅ GOOD
list.removeIf(n -> n % 2 == 0);
```

---

## 11. Common Mistakes & Solutions

```java
// Mistake 1: ConcurrentModificationException
ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

// ❌ WRONG
for (Integer num : list) {
    if (num == 3) {
        list.remove(num); // Throws ConcurrentModificationException
    }
}

// ✅ CORRECT
list.removeIf(num -> num == 3);

// ✅ CORRECT (using Iterator)
Iterator<Integer> iterator = list.iterator();
while (iterator.hasNext()) {
    if (iterator.next() == 3) {
        iterator.remove();
    }
}

// Mistake 2: NullPointerException
ArrayList<String> list = new ArrayList<>();
// list.get(0); // Throws IndexOutOfBoundsException if empty

// ✅ CHECK
if (!list.isEmpty()) {
    String first = list.get(0);
}

// Mistake 3: Wrong type casting
ArrayList list = new ArrayList(); // Raw type
list.add("String");
list.add(123);

// ❌ WRONG - Will throw ClassCastException
// String value = (String) list.get(1);

// ✅ CORRECT - Use generics
ArrayList<String> typedList = new ArrayList<>();

// Mistake 4: Modifying shared reference
ArrayList<String> original = new ArrayList<>(Arrays.asList("A", "B"));
ArrayList<String> copy = original; // Not a copy, same reference!
copy.add("C");
System.out.println(original); // [A, B, C] - original changed!

// ✅ CORRECT - Create actual copy
ArrayList<String> trueCopy = new ArrayList<>(original);
trueCopy.add("C");
System.out.println(original); // [A, B] - original unchanged
```

---

## 12. Quick Reference Table

| Operation | Syntax | Time Complexity |
|-----------|--------|-----------------|
| add(E e) | `list.add(element)` | O(1) amortized |
| add(int, E) | `list.add(index, element)` | O(n) |
| get(int) | `list.get(index)` | O(1) |
| set(int, E) | `list.set(index, element)` | O(1) |
| remove(int) | `list.remove(index)` | O(n) |
| remove(Object) | `list.remove(object)` | O(n) |
| contains(Object) | `list.contains(object)` | O(n) |
| indexOf(Object) | `list.indexOf(object)` | O(n) |
| size() | `list.size()` | O(1) |
| isEmpty() | `list.isEmpty()` | O(1) |

---

## Summary

- **ArrayList**: Use for frequent access operations
- **LinkedList**: Use for frequent insertions/deletions
- **Vector**: Legacy, avoid unless needed for compatibility
- **Collections**: Provides utility methods for sorting, searching, and synchronization
- **Streams**: Use for functional-style operations (Java 8+)
- **Always check for empty lists** before accessing elements
- **Use generics** to avoid ClassCastException
- **Prefer removeIf()** over manual removal to avoid ConcurrentModificationException
