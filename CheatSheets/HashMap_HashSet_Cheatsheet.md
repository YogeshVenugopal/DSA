# HashMap & HashSet Cheatsheet - Java DSA

## Overview

| Feature | HashMap | HashSet |
|---------|---------|---------|
| **Implements** | Map interface | Set interface |
| **Purpose** | Stores key-value pairs | Stores unique values |
| **Duplicates** | Keys are unique; values can duplicate | Only unique values |
| **Order** | No guaranteed order | No guaranteed order |
| **Null Values** | One null key, multiple null values | One null value allowed |
| **Performance** | O(1) avg, O(n) worst | O(1) avg, O(n) worst |
| **Thread-safe** | No (use Collections.synchronizedMap) | No (use Collections.synchronizedSet) |

---

## HashMap

### Declaration & Initialization

```java
// Basic declaration
HashMap<String, Integer> map = new HashMap<>();

// With initial capacity
HashMap<String, Integer> map = new HashMap<>(16);

// With capacity and load factor
HashMap<String, Integer> map = new HashMap<>(16, 0.75f);

// Initialize with values
HashMap<String, Integer> map = new HashMap<String, Integer>() {{
    put("a", 1);
    put("b", 2);
}};
```

### Common Methods

```java
// Put methods
map.put(key, value);                    // O(1) - Add/update key-value pair
map.putIfAbsent(key, value);           // O(1) - Add only if key doesn't exist
map.putAll(anotherMap);                // O(n) - Add all from another map

// Get methods
map.get(key);                          // O(1) - Returns value or null
map.getOrDefault(key, defaultValue);   // O(1) - Returns value or default

// Check methods
map.containsKey(key);                  // O(1) - Check if key exists
map.containsValue(value);              // O(n) - Check if value exists
map.isEmpty();                         // O(1) - Check if empty
map.size();                            // O(1) - Get number of entries

// Remove methods
map.remove(key);                       // O(1) - Remove by key, returns value
map.remove(key, value);                // O(1) - Remove only if key-value match
map.clear();                           // O(n) - Remove all entries

// Iteration
map.keySet();                          // Get all keys
map.values();                          // Get all values
map.entrySet();                        // Get all key-value pairs

// Replace methods
map.replace(key, newValue);            // O(1) - Replace value if key exists
map.replace(key, oldValue, newValue);  // O(1) - Replace only if old value matches
map.replaceAll((k, v) -> newValue);    // O(n) - Replace all values
```

### Iteration Methods

```java
// Method 1: Using entrySet() - MOST EFFICIENT
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    String key = entry.getKey();
    Integer value = entry.getValue();
}

// Method 2: Using keySet()
for (String key : map.keySet()) {
    Integer value = map.get(key);  // Extra O(1) lookup
}

// Method 3: Using values()
for (Integer value : map.values()) {
    // Process value only
}

// Method 4: Using Iterator
Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
while (it.hasNext()) {
    Map.Entry<String, Integer> entry = it.next();
    String key = entry.getKey();
    Integer value = entry.getValue();
}

// Method 5: Using forEach()
map.forEach((key, value) -> {
    // Process key and value
});

// Method 6: Using values().stream()
map.values().stream().forEach(System.out::println);
```

### Common Use Cases in DSA

```java
// 1. Count character/element frequency
HashMap<Character, Integer> freq = new HashMap<>();
String s = "hello";
for (char c : s.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}

// 2. Two Sum Problem
HashMap<Integer, Integer> map = new HashMap<>();
int[] nums = {2, 7, 11, 15};
int target = 9;
for (int num : nums) {
    if (map.containsKey(target - num)) {
        // Found pair
    }
    map.put(num, num);
}

// 3. Group elements
HashMap<String, List<Integer>> groups = new HashMap<>();
// Add to group
groups.computeIfAbsent(key, k -> new ArrayList<>()).add(value);

// 4. Cache/Memoization
HashMap<Integer, Integer> memo = new HashMap<>();
if (memo.containsKey(n)) {
    return memo.get(n);
}
// Compute result
memo.put(n, result);
```

### Edge Cases & Tips

```java
// Handle null keys
map.put(null, value);          // Allowed
map.get(null);                 // Works fine

// Avoid NullPointerException when iterating
if (map != null && !map.isEmpty()) {
    // Safe iteration
}

// Remove during iteration safely
Iterator<String> it = map.keySet().iterator();
while (it.hasNext()) {
    String key = it.next();
    if (condition) {
        it.remove();           // Safe removal
    }
}

// Get value with default safely
map.getOrDefault(key, 0);     // Avoid null checks
```

---

## HashSet

### Declaration & Initialization

```java
// Basic declaration
HashSet<Integer> set = new HashSet<>();

// With initial capacity
HashSet<Integer> set = new HashSet<>(16);

// Initialize with values
HashSet<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));

// Initialize with another collection
HashSet<Integer> set = new HashSet<>(anotherList);
```

### Common Methods

```java
// Add methods
set.add(element);                      // O(1) - Add element, returns true if added
set.addAll(anotherCollection);         // O(n) - Add all from collection

// Check methods
set.contains(element);                 // O(1) - Check if element exists
set.isEmpty();                         // O(1) - Check if empty
set.size();                            // O(1) - Get number of elements

// Remove methods
set.remove(element);                   // O(1) - Remove element, returns true if removed
set.removeIf(predicate);               // O(n) - Remove all matching predicate
set.clear();                           // O(n) - Remove all elements

// Set operations
set1.addAll(set2);                     // Union - O(n)
set1.retainAll(set2);                  // Intersection - O(n)
set1.removeAll(set2);                  // Difference - O(n)

// Iteration
for (Integer num : set) {
    // Process element
}

// Stream operations
set.stream().filter(x -> x > 5).forEach(System.out::println);
```

### Iteration Methods

```java
// Method 1: Enhanced for loop
for (Integer element : set) {
    // Process element
}

// Method 2: Iterator
Iterator<Integer> it = set.iterator();
while (it.hasNext()) {
    Integer element = it.next();
}

// Method 3: forEach()
set.forEach(element -> {
    // Process element
});

// Method 4: Stream
set.stream().forEach(System.out::println);
```

### Common Use Cases in DSA

```java
// 1. Remove duplicates from array
int[] arr = {1, 2, 2, 3, 3, 3};
HashSet<Integer> set = new HashSet<>(Arrays.asList(
    Arrays.stream(arr).boxed().toArray(Integer[]::new)
));
// Or simpler
HashSet<Integer> set = new HashSet<>();
for (int num : arr) {
    set.add(num);
}

// 2. Check if element exists
HashSet<Integer> seen = new HashSet<>();
for (int num : nums) {
    if (seen.contains(num)) {
        // Duplicate found
    }
    seen.add(num);
}

// 3. Find unique elements
HashSet<Character> unique = new HashSet<>();
String s = "hello";
for (char c : s.toCharArray()) {
    unique.add(c);
}

// 4. Set operations - Common elements
HashSet<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
HashSet<Integer> set2 = new HashSet<>(Arrays.asList(2, 3, 4));
set1.retainAll(set2);  // set1 now contains {2, 3}

// 5. Set difference - Elements in set1 but not in set2
set1.removeAll(set2);

// 6. Union
set1.addAll(set2);
```

### Edge Cases & Tips

```java
// HashSet with custom objects requires proper equals() and hashCode()
class Person {
    String name;
    int age;
    
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Person)) return false;
        Person other = (Person) obj;
        return name.equals(other.name) && age == other.age;
    }
}

// Safe removal during iteration
Iterator<Integer> it = set.iterator();
while (it.hasNext()) {
    Integer element = it.next();
    if (condition) {
        it.remove();  // Safe removal
    }
}

// Convert to ArrayList for indexed access
List<Integer> list = new ArrayList<>(set);
```

---

## Performance Comparison

| Operation | HashMap | HashSet | Time Complexity |
|-----------|---------|---------|-----------------|
| Add | put() | add() | O(1) average |
| Remove | remove() | remove() | O(1) average |
| Search | get/containsKey | contains() | O(1) average |
| Iteration | O(n+m) where m=capacity | O(n+m) | O(n+m) |

> **Note:** Worst case is O(n) when many hash collisions occur. Java 8+ uses balanced trees for collision handling.

---

## HashMap vs HashSet: When to Use?

### Use HashMap when:
- You need to store key-value pairs
- You need to look up values by keys
- Implementing caching/memoization
- Counting frequencies
- Mapping one set of values to another

### Use HashSet when:
- You only need to store unique values
- You need fast membership testing
- You need to remove duplicates
- You need set operations (union, intersection, difference)
- You only care about existence, not order

---

## Important Notes

1. **Thread Safety:** Both are not synchronized. Use `Collections.synchronizedMap()` or `Collections.synchronizedSet()` for multi-threaded environments.

2. **Null Handling:**
   - HashMap: Allows 1 null key and multiple null values
   - HashSet: Allows 1 null value

3. **Load Factor:** Default is 0.75. Controls when rehashing occurs. Higher = less memory, more collision. Lower = more memory, faster access.

4. **equals() and hashCode():** Essential for custom objects. Violating the contract leads to bugs.

5. **Iteration Order:** Not guaranteed. Use LinkedHashMap or LinkedHashSet if insertion order is needed.

6. **Capacity:** Start with power of 2 (16, 32, 64...). HashMap/HashSet internally use power of 2 capacity for efficiency.

---

## Quick Reference

```java
// HashMap Quick Ref
HashMap<K, V> map = new HashMap<>();
map.put(key, value);
V val = map.get(key);
map.remove(key);
map.containsKey(key);

// HashSet Quick Ref
HashSet<E> set = new HashSet<>();
set.add(element);
boolean exists = set.contains(element);
set.remove(element);
```

---

## Related Collections

- **LinkedHashMap:** HashMap with insertion order preserved
- **LinkedHashSet:** HashSet with insertion order preserved
- **TreeMap:** Sorted map (Red-Black Tree), O(log n)
- **TreeSet:** Sorted set (Red-Black Tree), O(log n)
- **ConcurrentHashMap:** Thread-safe HashMap alternative
- **EnumMap/EnumSet:** Optimized for Enum types
