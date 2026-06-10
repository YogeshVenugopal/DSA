# Java Arrays & Strings Cheatsheet

## Table of Contents

1. [Arrays Basics](#arrays-basics)
2. [String Basics](#string-basics)
3. [Common Array Operations](#common-array-operations)
4. [Common String Operations](#common-string-operations)
5. [Advanced Techniques](#advanced-techniques)
6. [Problem Checklist](#problem-checklist)
7. [Mastery Action Items](#mastery-action-items)

---

## Arrays Basics

### Declaration & Initialization

```java
// 1D Array Declaration
int[] arr;                              // Declaration
int[] arr = new int[5];                 // Default values: 0
int[] arr = {1, 2, 3, 4, 5};           // With initial values
int[] arr = new int[]{1, 2, 3, 4, 5}; // Both declaration and init

// 2D Array Declaration
int[][] matrix = new int[3][4];        // 3 rows, 4 columns
int[][] matrix = {{1,2,3}, {4,5,6}};   // 2 rows, 3 columns

// Getting Array Length
int length = arr.length;                // Returns 5

// 2D Array - Get dimensions
int rows = matrix.length;               // Number of rows
int cols = matrix[0].length;            // Number of columns
```

### Array Creation & Basics

```java
// Create array with specific size
String[] fruits = new String[3];

// Initialize with default values
int[] numbers = new int[5];     // All zeros
boolean[] flags = new boolean[3]; // All false
String[] names = new String[3];   // All null

// Copy Array
int[] original = {1, 2, 3, 4, 5};
int[] copy1 = original.clone();                    // Shallow copy
int[] copy2 = Arrays.copyOf(original, original.length);
int[] copy3 = new int[original.length];
System.arraycopy(original, 0, copy3, 0, original.length);

// Important imports
import java.util.Arrays;
import java.util.ArrayList;
```

### Array vs ArrayList

```java
// Array - Fixed size
int[] arr = new int[5];
arr[0] = 10;
// arr[5] = 20;  // IndexOutOfBoundsException

// ArrayList - Dynamic size
ArrayList<Integer> list = new ArrayList<>();
list.add(10);       // O(1) amortized
list.add(20);
list.add(2, 15);    // Insert at index 2
list.get(0);        // Get element
list.set(0, 5);     // Update element
list.remove(0);     // Remove element
list.size();        // Get size
list.isEmpty();     // Check if empty

// Convert Array to ArrayList
Integer[] arr = {1, 2, 3};
ArrayList<Integer> list = new ArrayList<>(Arrays.asList(arr));

// Convert ArrayList to Array
ArrayList<Integer> list = new ArrayList<>();
Integer[] arr = list.toArray(new Integer[0]);
```

---

## String Basics

### String Creation & Basics

```java
// String Declaration
String str1 = "Hello";                    // String literal (String pool)
String str2 = new String("Hello");        // Creates new object (heap)
String str3 = String.valueOf(42);         // Convert to string
String str4 = Integer.toString(42);       // Convert int to string

// String is IMMUTABLE
String original = "Hello";
String modified = original + " World";    // Creates new String object
// original remains "Hello"

// String Comparison (IMPORTANT!)
String a = "hello";
String b = "hello";
String c = new String("hello");

a == b;              // true (same reference, string pool)
a == c;              // false (different objects)
a.equals(b);         // true (same content)
a.equals(c);         // true (same content)
a.equalsIgnoreCase(c); // true (case insensitive)
a.compareTo(b);      // 0 (equal), positive (greater), negative (less)

// Get Character
char ch = str.charAt(0);                 // First character 'H'
// str.charAt(5);     // StringIndexOutOfBoundsException

// Substring
String sub = str.substring(0, 3);        // "Hel" (0 to 2, exclusive end)
String sub2 = str.substring(2);          // "llo" (from index 2 to end)
```

### String Methods

```java
// LENGTH & COMPARISON
int len = str.length();                  // Returns length
boolean empty = str.isEmpty();           // Check if empty
boolean blank = str.isBlank();           // Java 11+ (whitespace only)
boolean equals = str.equals("Hello");    // Exact match
boolean startsWith = str.startsWith("He");
boolean endsWith = str.endsWith("lo");
boolean contains = str.contains("ell");

// CASE CONVERSION
String upper = str.toUpperCase();        // "HELLO"
String lower = str.toLowerCase();        // "hello"

// TRIMMING & PADDING
String trimmed = str.trim();             // Remove leading/trailing spaces
String stripped = str.strip();           // Java 11+ (better than trim)
String padded = str.repeat(3);           // Java 11+ "HelloHelloHello"

// INDEXING
int index = str.indexOf('l');            // First occurrence: 2
int index2 = str.indexOf('l', 3);        // From index 3: 3
int index3 = str.lastIndexOf('l');       // Last occurrence: 3
// Returns -1 if not found

// REPLACEMENT
String replaced = str.replace("l", "L"); // "HeLLo"
String replaced2 = str.replaceAll("[aeiou]", "*"); // Regex
String replaced3 = str.replaceFirst("l", "L"); // First occurrence

// SPLITTING
String text = "apple,banana,cherry";
String[] words = text.split(",");        // ["apple", "banana", "cherry"]
String[] words2 = text.split(",", 2);    // Limit to 2 parts

// JOINING (Java 8+)
String[] fruits = {"apple", "banana", "cherry"};
String joined = String.join(",", fruits); // "apple,banana,cherry"

// CHARACTER EXTRACTION
char[] chars = str.toCharArray();        // Convert to char array
String fromChars = new String(chars);    // Convert back

// FORMATTING
String formatted = String.format("Hello %s, you are %d years old", "John", 25);
// "Hello John, you are 25 years old"

// CONVERSION
int num = Integer.parseInt("42");        // "42" → 42
double dbl = Double.parseDouble("3.14"); // "3.14" → 3.14
```

---

## Common Array Operations

### Searching

```java
// Linear Search
public static int linearSearch(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) {
            return i;
        }
    }
    return -1;  // Not found
}

// Binary Search (array must be sorted)
int index = Arrays.binarySearch(arr, target);  // Returns index or negative value
// O(log n) time complexity

// Using Java's built-in
int index = Arrays.binarySearch(arr, 5);
if (index >= 0) {
    System.out.println("Found at index: " + index);
} else {
    System.out.println("Not found");
}
```

### Sorting

```java
// Sort ascending
int[] arr = {5, 2, 8, 1, 9};
Arrays.sort(arr);                        // [1, 2, 5, 8, 9]
// O(n log n) - Uses Timsort internally

// Sort 2D Array (by first column)
int[][] matrix = {{3,1}, {1,2}, {2,3}};
Arrays.sort(matrix, (a, b) -> Integer.compare(a[0], b[0]));

// Sort with custom comparator
Integer[] arr = {5, 2, 8, 1, 9};
Arrays.sort(arr, Collections.reverseOrder()); // Descending

// Sort ArrayList
ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5,2,8,1,9));
Collections.sort(list);                  // Ascending
Collections.sort(list, Collections.reverseOrder()); // Descending
Collections.sort(list, (a, b) -> b - a); // Descending with lambda

// Custom sorting
ArrayList<Person> people = new ArrayList<>();
Collections.sort(people, (p1, p2) -> p1.age - p2.age); // By age
Collections.sort(people, (p1, p2) -> p1.name.compareTo(p2.name)); // By name
```

### Filtering & Mapping

```java
// Filter
ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
ArrayList<Integer> evens = new ArrayList<>();
for (int num : numbers) {
    if (num % 2 == 0) {
        evens.add(num);
    }
}
// Result: [2, 4, 6]

// Using Streams (Java 8+)
List<Integer> evens = numbers.stream()
                              .filter(n -> n % 2 == 0)
                              .collect(Collectors.toList());

// Map (transform)
List<Integer> doubled = numbers.stream()
                               .map(n -> n * 2)
                               .collect(Collectors.toList());
// Result: [2, 4, 6, 8, 10, 12]

// Find
Optional<Integer> firstEven = numbers.stream()
                                     .filter(n -> n % 2 == 0)
                                     .findFirst();
if (firstEven.isPresent()) {
    System.out.println(firstEven.get()); // 2
}
```

### Aggregations

```java
// Sum
int[] arr = {1, 2, 3, 4, 5};
int sum = 0;
for (int num : arr) sum += num;
// Result: 15

// Using Streams
int sum = Arrays.stream(arr).sum();

// Find Max
int max = arr[0];
for (int num : arr) {
    max = Math.max(max, num);
}
// Result: 5

// Using Streams
int max = Arrays.stream(arr).max().orElse(Integer.MIN_VALUE);

// Find Min
int min = Arrays.stream(arr).min().orElse(Integer.MAX_VALUE);

// Average
double avg = Arrays.stream(arr).average().orElse(0.0);

// Count
long count = Arrays.stream(arr).filter(n -> n > 3).count();
```

### Reversing

```java
// Reverse Array
int[] arr = {1, 2, 3, 4, 5};
// Method 1: Using two pointers
for (int i = 0; i < arr.length / 2; i++) {
    int temp = arr[i];
    arr[i] = arr[arr.length - 1 - i];
    arr[arr.length - 1 - i] = temp;
}
// Result: [5, 4, 3, 2, 1]

// Method 2: Using Collections
Integer[] arr = {1, 2, 3, 4, 5};
Collections.reverse(Arrays.asList(arr));

// Method 3: Using Streams
int[] arr = {1, 2, 3, 4, 5};
int[] reversed = IntStream.rangeClosed(1, arr.length)
                          .map(i -> arr[arr.length - i])
                          .toArray();
```

---

## Common String Operations

### Character Frequency

```java
// Count character frequency
String str = "hello";
Map<Character, Integer> freq = new HashMap<>();

for (char ch : str.toCharArray()) {
    freq.put(ch, freq.getOrDefault(ch, 0) + 1);
}
// Result: {e=1, h=1, l=2, o=1}

// Using Streams
Map<Character, Long> freq = str.chars()
                               .mapToObj(c -> (char) c)
                               .collect(Collectors.groupingBy(
                                   Function.identity(),
                                   Collectors.counting()
                               ));

// Find character with max frequency
char maxChar = freq.entrySet().stream()
                   .max((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()))
                   .get()
                   .getKey();
```

### Check Anagram

```java
// Method 1: Sort and Compare
boolean isAnagram(String s1, String s2) {
    if (s1.length() != s2.length()) return false;
    
    char[] arr1 = s1.toCharArray();
    char[] arr2 = s2.toCharArray();
    
    Arrays.sort(arr1);
    Arrays.sort(arr2);
    
    return Arrays.equals(arr1, arr2);
}

// Method 2: Frequency Map
boolean isAnagram(String s1, String s2) {
    if (s1.length() != s2.length()) return false;
    
    Map<Character, Integer> freq = new HashMap<>();
    for (char ch : s1.toCharArray()) {
        freq.put(ch, freq.getOrDefault(ch, 0) + 1);
    }
    
    for (char ch : s2.toCharArray()) {
        if (!freq.containsKey(ch) || freq.get(ch) == 0) {
            return false;
        }
        freq.put(ch, freq.get(ch) - 1);
    }
    
    return true;
}
```

### Palindrome Check

```java
// Method 1: Two Pointer
boolean isPalindrome(String str) {
    int left = 0, right = str.length() - 1;
    
    while (left < right) {
        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }
        left++;
        right--;
    }
    return true;
}

// Method 2: Reverse and Compare
boolean isPalindrome(String str) {
    String reversed = new StringBuilder(str).reverse().toString();
    return str.equals(reversed);
}

// Alphanumeric Palindrome (skip non-alphanumeric)
boolean isPalindrome(String str) {
    int left = 0, right = str.length() - 1;
    
    while (left < right) {
        while (left < right && !Character.isAlphanumeric(str.charAt(left))) {
            left++;
        }
        while (left < right && !Character.isAlphanumeric(str.charAt(right))) {
            right--;
        }
        
        if (Character.toLowerCase(str.charAt(left)) != 
            Character.toLowerCase(str.charAt(right))) {
            return false;
        }
        left++;
        right--;
    }
    return true;
}
```

### Remove Duplicates

```java
// From Array
int[] arr = {1, 2, 2, 3, 3, 3, 4};
int[] result = Arrays.stream(arr).distinct().toArray();
// Result: [1, 2, 3, 4]

// From String
String str = "hello";
String result = str.chars()
                   .distinct()
                   .collect(StringBuilder::new, 
                            (sb, c) -> sb.append((char) c),
                            (sb1, sb2) -> sb1.append(sb2))
                   .toString();
// Result: "helo"

// Using LinkedHashSet (preserves insertion order)
String str = "hello";
String result = str.chars()
                   .boxed()
                   .collect(Collectors.toCollection(LinkedHashSet::new))
                   .stream()
                   .map(c -> String.valueOf((char) (int) c))
                   .collect(Collectors.joining());
```

---

## Advanced Techniques

### Two Pointers

```java
// Two Sum (Sorted Array)
public static int[] twoSum(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    
    while (left < right) {
        int sum = arr[left] + arr[right];
        
        if (sum == target) {
            return new int[]{left, right};
        } else if (sum < target) {
            left++;
        } else {
            right--;
        }
    }
    return new int[]{-1, -1};
}

// Remove Duplicates (In-place)
public static int removeDuplicates(int[] arr) {
    if (arr.length == 0) return 0;
    
    int i = 0;
    for (int j = 1; j < arr.length; j++) {
        if (arr[j] != arr[i]) {
            arr[++i] = arr[j];
        }
    }
    return i + 1;
}

// Reverse String (In-place)
public static void reverseString(char[] chars) {
    int left = 0, right = chars.length - 1;
    
    while (left < right) {
        char temp = chars[left];
        chars[left] = chars[right];
        chars[right] = temp;
        
        left++;
        right--;
    }
}
```

### Sliding Window

```java
// Maximum Sum Subarray of Size K
public static int maxSumSubarray(int[] arr, int k) {
    int maxSum = 0, currentSum = 0;
    
    // Initial window
    for (int i = 0; i < k; i++) {
        currentSum += arr[i];
    }
    maxSum = currentSum;
    
    // Slide the window
    for (int i = k; i < arr.length; i++) {
        currentSum = currentSum - arr[i - k] + arr[i];
        maxSum = Math.max(maxSum, currentSum);
    }
    
    return maxSum;
}

// Longest Substring Without Repeating Characters
public static int lengthOfLongestSubstring(String str) {
    Map<Character, Integer> charIndex = new HashMap<>();
    int maxLen = 0, left = 0;
    
    for (int right = 0; right < str.length(); right++) {
        char ch = str.charAt(right);
        
        if (charIndex.containsKey(ch)) {
            left = Math.max(left, charIndex.get(ch) + 1);
        }
        
        charIndex.put(ch, right);
        maxLen = Math.max(maxLen, right - left + 1);
    }
    
    return maxLen;
}
```

### Prefix Sum

```java
// Build Prefix Sum Array
public static int[] buildPrefixSum(int[] arr) {
    int[] prefix = new int[arr.length];
    prefix[0] = arr[0];
    
    for (int i = 1; i < arr.length; i++) {
        prefix[i] = prefix[i - 1] + arr[i];
    }
    
    return prefix;
}

// Query Sum in Range [left, right]
public static int rangeSum(int[] prefix, int left, int right) {
    if (left == 0) {
        return prefix[right];
    }
    return prefix[right] - prefix[left - 1];
}

// 2D Prefix Sum
public static int[][] build2DPrefix(int[][] matrix) {
    int m = matrix.length, n = matrix[0].length;
    int[][] prefix = new int[m + 1][n + 1];
    
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            prefix[i][j] = matrix[i-1][j-1] + 
                          prefix[i-1][j] + 
                          prefix[i][j-1] - 
                          prefix[i-1][j-1];
        }
    }
    
    return prefix;
}

// Query 2D Range Sum
public static int rangeSum2D(int[][] prefix, int r1, int c1, int r2, int c2) {
    return prefix[r2+1][c2+1] - 
           prefix[r1][c2+1] - 
           prefix[r2+1][c1] + 
           prefix[r1][c1];
}
```

### Matrix Operations

```java
// Rotate Matrix 90 Degrees Clockwise
public static void rotateMatrix(int[][] matrix) {
    int n = matrix.length;
    
    // Transpose
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            int temp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = temp;
        }
    }
    
    // Reverse each row
    for (int i = 0; i < n; i++) {
        int left = 0, right = n - 1;
        while (left < right) {
            int temp = matrix[i][left];
            matrix[i][left] = matrix[i][right];
            matrix[i][right] = temp;
            left++;
            right--;
        }
    }
}

// Set Row and Column to Zero
public static void setMatrixZeroes(int[][] matrix) {
    Set<Integer> rows = new HashSet<>();
    Set<Integer> cols = new HashSet<>();
    
    // Find all zeros
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[i][j] == 0) {
                rows.add(i);
                cols.add(j);
            }
        }
    }
    
    // Set zeros
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
            if (rows.contains(i) || cols.contains(j)) {
                matrix[i][j] = 0;
            }
        }
    }
}

// Get Matrix Diagonal
public static List<Integer> getDiagonal(int[][] matrix) {
    List<Integer> diagonal = new ArrayList<>();
    
    for (int i = 0; i < matrix.length; i++) {
        diagonal.add(matrix[i][i]);
    }
    
    return diagonal;
}
```

---

## Problem Checklist

### 🟢 Easy Problems (Master First)

- [x] **#1 Two Sum** | HashMap/Brute Force | `O(n)` time

  - Topics: Hash Map, Two Pointers
  - Variants: Sorted array, return indices

- [ ] **#7 Reverse Integer** | String manipulation | `O(1)` time

  - Topics: Math, String conversion
  - Watch for: Integer overflow

- [ ] **#9 Palindrome Number** | String/Math approach | `O(1)` time

  - Topics: String, Palindrome, Number manipulation
  - Variants: Check if string palindrome

- [ ] **#13 Roman to Integer** | HashMap | `O(n)` time

  - Topics: Hash Map, String, Math
  - Key insight: Subtractive notation

- [ ] **#14 Longest Common Prefix** | String comparison | `O(n*m)` time

  - Topics: String, Array
  - Approaches: Horizontal scan, vertical scan, binary search

- [ ] **#20 Valid Parentheses** | Stack | `O(n)` time

  - Topics: Stack, String matching
  - Edge cases: Empty string, mismatched pairs

- [ ] **#26 Remove Duplicates from Sorted Array** | Two Pointers | `O(n)` time

  - Topics: Two Pointers, In-place modification
  - Important: Return length, array modified

- [ ] **#27 Remove Element** | Two Pointers | `O(n)` time

  - Topics: Two Pointers, Array
  - Similar to #26

- [ ] **#35 Search Insert Position** | Binary Search | `O(log n)` time

  - Topics: Binary Search, Array
  - Key: Find insertion point for missing element

- [ ] **#58 Length of Last Word** | String manipulation | `O(n)` time

  - Topics: String, Simple iteration
  - Edge cases: Trailing spaces

- [ ] **#66 Plus One** | Array manipulation | `O(n)` time

  - Topics: Array, Math
  - Watch for: Carry over

- [ ] **#67 Add Binary** | String manipulation | `O(n)` time

  - Topics: String, Math, Bit operations
  - Variants: Add decimal numbers

- [ ] **#88 Merge Sorted Array** | Two Pointers | `O(n+m)` time

  - Topics: Two Pointers, Array
  - Key: Merge in-place from end

- [ ] **#100 Same Tree** | Tree/Recursion | `O(n)` time

  - Topics: Tree comparison, Recursion
  - Variants: Symmetric tree, invert tree

- [ ] **#101 Symmetric Tree** | Recursion | `O(n)` time

  - Topics: Tree, Recursion
  - Approach: Check mirror condition

- [ ] **#104 Maximum Depth of Binary Tree** | Recursion/BFS | `O(n)` time

  - Topics: Tree, DFS, BFS
  - Variants: Minimum depth, height

- [ ] **#108 Convert Sorted Array to BST** | Recursion | `O(n)` time

  - Topics: Tree, Array, Recursion
  - Key: Use middle element as root

- [ ] **#118 Pascal's Triangle** | Array generation | `O(n²)` time

  - Topics: Array, Math, DP
  - Pattern recognition

- [ ] **#121 Best Time to Buy and Sell Stock** | Array scan | `O(n)` time

  - Topics: Array, DP, Tracking min
  - Important: Single transaction

- [ ] **#125 Valid Palindrome** | Two Pointers | `O(n)` time

  - Topics: String, Two Pointers
  - Key: Skip non-alphanumeric, case insensitive

- [ ] **#141 Linked List Cycle** | Two Pointers | `O(n)` time

  - Topics: Linked List, Two Pointers (Floyd's algorithm)
  - Important: Fast and slow pointer

- [ ] **#155 Min Stack** | Stack design | `O(1)` time

  - Topics: Stack, Design
  - Approach: Maintain separate min stack

- [ ] **#160 Intersection of Two Linked Lists** | Two Pointers | `O(n+m)` time

  - Topics: Linked List, Two Pointers
  - Key insight: Use difference in lengths

- [ ] **#169 Majority Element** | HashMap/Sorting | `O(n)` time

  - Topics: Array, Voting algorithm
  - Approaches: HashMap, sort, Boyer-Moore

- [ ] **#172 Factorial Trailing Zeroes** | Math | `O(log n)` time

  - Topics: Math, Number theory
  - Key: Count factors of 5

- [ ] **#189 Rotate Array** | Array rotation | `O(n)` time

  - Topics: Array, In-place rotation
  - Approaches: Reverse, extra array, rotation algorithm

- [ ] **#202 Happy Number** | HashMap | `O(1)` time

  - Topics: Hash Map, Cycle detection
  - Approach: Detect cycle with set

- [ ] **#205 Isomorphic Strings** | HashMap | `O(n)` time

  - Topics: Hash Map, String mapping
  - Important: Bidirectional mapping

- [ ] **#217 Contains Duplicate** | HashMap/Set | `O(n)` time

  - Topics: Hash Map, Array
  - Variants: #219, #220

- [ ] **#219 Contains Duplicate II** | Sliding Window | `O(n)` time

  - Topics: Sliding Window, Hash Set
  - Key: Window size constraint

- [ ] **#242 Valid Anagram** | Sorting/HashMap | `O(n log n)` time

  - Topics: String, Sorting, Hash Map
  - Approaches: Sort, frequency map

- [ ] **#257 Binary Tree Paths** | DFS/Recursion | `O(n)` time

  - Topics: Tree, DFS, String building
  - Approach: Build path string recursively

---

### 🟡 Medium Problems (Strengthen Foundations)

- [ ] **#2 Add Two Numbers** | Linked List simulation | `O(max(m,n))` time

  - Topics: Linked List, Math
  - Key: Handle carry

- [ ] **#3 Longest Substring Without Repeating Characters** | Sliding Window | `O(n)` time

  - Topics: Sliding Window, Hash Map
  - Important: Character index tracking

- [ ] **#5 Longest Palindromic Substring** | DP/Expansion | `O(n²)` time

  - Topics: String, DP, Two Pointers
  - Approaches: DP, center expansion

- [ ] **#6 Zigzag Conversion** | String manipulation | `O(n)` time

  - Topics: String, Matrix pattern
  - Key: Track row direction

- [ ] **#8 String to Integer (atoi)** | String parsing | `O(n)` time

  - Topics: String, Parsing, Edge cases
  - Watch for: Overflow, leading spaces, signs

- [ ] **#11 Container With Most Water** | Two Pointers | `O(n)` time

  - Topics: Two Pointers, Array
  - Key: Move pointer with smaller height

- [ ] **#15 3Sum** | Two Pointers + Sorting | `O(n²)` time

  - Topics: Array, Two Pointers, Sorting
  - Important: Avoid duplicates

- [ ] **#16 3Sum Closest** | Two Pointers | `O(n²)` time

  - Topics: Two Pointers, Sorting
  - Variant of #15

- [ ] **#18 4Sum** | Two Pointers | `O(n³)` time

  - Topics: Nested loops, Two Pointers
  - Generalization of #15

- [ ] **#19 Remove Nth Node From End of List** | Linked List | `O(n)` time

  - Topics: Linked List, Two Pointers
  - Key: Dummy node for head removal

- [ ] **#24 Swap Nodes in Pairs** | Linked List | `O(n)` time

  - Topics: Linked List, Recursion
  - Approaches: Iterative, recursive

- [ ] **#33 Search in Rotated Sorted Array** | Binary Search | `O(log n)` time

  - Topics: Binary Search, Array
  - Key: Identify which half is sorted

- [ ] **#34 Find First and Last Position** | Binary Search | `O(log n)` time

  - Topics: Binary Search, Array
  - Approach: Two binary searches

- [ ] **#39 Combination Sum** | Backtracking | `O(2^n)` time

  - Topics: Backtracking, Array
  - Key: Reuse elements allowed

- [ ] **#40 Combination Sum II** | Backtracking | `O(2^n)` time

  - Topics: Backtracking, Sorting, Skip duplicates
  - Different from #39: Each element used once

- [ ] **#42 Trapping Rain Water** | Two Pointers/DP | `O(n)` time

  - Topics: Array, Two Pointers, DP
  - Key insight: min(left_max, right_max) - height

- [ ] **#46 Permutations** | Backtracking | `O(n!)` time

  - Topics: Backtracking, Array
  - Approach: Choose element, recurse, backtrack

- [ ] **#47 Permutations II** | Backtracking | `O(n!)` time

  - Topics: Backtracking, Duplicates
  - Key: Skip duplicate elements

- [ ] **#48 Rotate Image** | Matrix rotation | `O(n²)` time

  - Topics: Matrix, In-place rotation
  - Approach: Transpose + reverse

- [ ] **#49 Group Anagrams** | HashMap | `O(n * k log k)` time

  - Topics: Hash Map, Sorting, String
  - Key: Sort characters as key

- [ ] **#50 Pow(x, n)** | Binary exponentiation | `O(log n)` time

  - Topics: Math, Recursion, Bit manipulation
  - Watch for: Integer overflow, negative exponent

- [ ] **#54 Spiral Matrix** | Matrix traversal | `O(m*n)` time

  - Topics: Matrix, Traversal
  - Approach: Shrink boundaries

- [ ] **#55 Jump Game** | Greedy/DP | `O(n)` time

  - Topics: Greedy, Array, DP
  - Key: Track maximum reachable index

- [ ] **#56 Merge Intervals** | Sorting | `O(n log n)` time

  - Topics: Array, Sorting, Interval merging
  - Approach: Sort by start, then merge overlapping

- [ ] **#59 Spiral Matrix II** | Matrix generation | `O(n²)` time

  - Topics: Matrix, Spiral pattern
  - Similar to #54 but generating

- [ ] **#61 Rotate List** | Linked List | `O(n)` time

  - Topics: Linked List, Array concepts
  - Key: Find rotation point

- [ ] **#62 Unique Paths** | DP | `O(m*n)` time

  - Topics: DP, Combinatorics
  - Approach: Build DP table

- [ ] **#63 Unique Paths II** | DP | `O(m*n)` time

  - Topics: DP, Obstacles
  - Extension of #62

- [ ] **#64 Minimum Path Sum** | DP | `O(m*n)` time

  - Topics: DP, Array
  - Similar to #62 with weights

- [ ] **#71 Simplify Path** | Stack/String | `O(n)` time

  - Topics: Stack, String parsing
  - Key: Handle ., .., and /

- [ ] **#73 Set Matrix Zeroes** | In-place modification | `O(m*n)` time

  - Topics: Matrix, In-place, Space optimization
  - Approach: Use first row/column as markers

- [ ] **#74 Search a 2D Matrix** | Binary Search | `O(log(m*n))` time

  - Topics: Matrix, Binary Search
  - Key: Treat as sorted 1D array

- [ ] **#75 Sort Colors** | Array sorting | `O(n)` time

  - Topics: Array, Two Pointers, Dutch National Flag
  - Constraint: Single pass required

- [ ] **#76 Minimum Window Substring** | Sliding Window | `O(n)` time

  - Topics: Sliding Window, Hash Map
  - Hard but important pattern

- [ ] **#77 Combinations** | Backtracking | `O(2^n)` time

  - Topics: Backtracking, Array
  - Key: Generate all k-combinations

- [ ] **#78 Subsets** | Backtracking | `O(2^n)` time

  - Topics: Backtracking, Array
  - Approach: Include/exclude each element

- [ ] **#79 Word Search** | DFS/Backtracking | `O(n*m*4^L)` time

  - Topics: Matrix, DFS, Backtracking
  - Key: Check 4 directions, mark visited

- [ ] **#80 Remove Duplicates II** | Two Pointers | `O(n)` time

  - Topics: Two Pointers, Array
  - Variant of #26 with frequency limit

- [ ] **#82 Remove Duplicates from Sorted List II** | Linked List | `O(n)` time

  - Topics: Linked List, Dummy node
  - Key: Remove nodes with duplicates

- [ ] **#83 Remove Duplicates from Sorted List** | Linked List | `O(n)` time

  - Topics: Linked List, Simple traversal
  - Easier variant

- [ ] **#86 Partition List** | Linked List | `O(n)` time

  - Topics: Linked List, Partition
  - Approach: Create two lists, merge

- [ ] **#90 Subsets II** | Backtracking | `O(2^n)` time

  - Topics: Backtracking, Duplicates
  - Key: Skip duplicate elements

- [ ] **#92 Reverse Linked List II** | Linked List | `O(n)` time

  - Topics: Linked List, Pointer manipulation
  - Key: Reverse sublist in place

- [ ] **#102 Binary Tree Level Order Traversal** | BFS | `O(n)` time

  - Topics: Tree, BFS, Queue
  - Approach: Process level by level

- [ ] **#103 Binary Tree Zigzag Level Order** | BFS | `O(n)` time

  - Topics: Tree, BFS, Direction alternation
  - Extension of #102

- [ ] **#105 Construct Binary Tree from Preorder/Inorder** | Recursion | `O(n)` time

  - Topics: Tree, Recursion, Array
  - Key: Use first element as root

- [ ] **#106 Construct Binary Tree from Inorder/Postorder** | Recursion | `O(n)` time

  - Topics: Tree, Recursion
  - Similar to #105

- [ ] **#113 Path Sum II** | DFS | `O(n)` time

  - Topics: Tree, DFS, Path building
  - Key: Build path during traversal

- [ ] **#114 Flatten Binary Tree to Linked List** | DFS | `O(n)` time

  - Topics: Tree, DFS, In-place modification
  - Approach: Process right subtree first

- [ ] **#130 Surrounded Regions** | DFS/BFS | `O(m*n)` time

  - Topics: Matrix, DFS, Connected components
  - Key: Mark from boundaries

- [ ] **#138 Copy List with Random Pointer** | Hash Map | `O(n)` time

  - Topics: Linked List, Hash Map
  - Key: Two-pass approach

- [ ] **#143 Reorder List** | Linked List | `O(n)` time

  - Topics: Linked List, Two Pointers
  - Approach: Reverse second half, merge

- [ ] **#144 Binary Tree Preorder Traversal** | Tree traversal | `O(n)` time

  - Topics: Tree, Stack, Recursion
  - Approaches: Recursive, iterative

- [ ] **#145 Binary Tree Postorder Traversal** | Tree traversal | `O(n)` time

  - Topics: Tree, Stack, Recursion
  - Approaches: Recursive, iterative

- [ ] **#146 LRU Cache** | Design | `O(1)` time

  - Topics: Hash Map, Doubly Linked List, Design
  - Key: Combination data structure

- [ ] **#148 Sort List** | Linked List sorting | `O(n log n)` time

  - Topics: Linked List, Merge sort
  - Approach: Merge sort on linked list

- [ ] **#150 Evaluate Reverse Polish Notation** | Stack | `O(n)` time

  - Topics: Stack, String parsing
  - Key: Push operands, evaluate operators

- [ ] **#151 Reverse Words in a String** | String manipulation | `O(n)` time

  - Topics: String, Array, Two Pointers
  - Watch for: Multiple spaces

- [ ] **#152 Maximum Product Subarray** | DP | `O(n)` time

  - Topics: DP, Array
  - Key: Track both max and min

- [ ] **#153 Find Minimum in Rotated Sorted Array** | Binary Search | `O(log n)` time

  - Topics: Binary Search, Array
  - Key: Identify which side has rotation

- [ ] **#162 Find Peak Element** | Binary Search | `O(log n)` time

  - Topics: Binary Search, Array
  - Approach: Move towards larger neighbor

- [ ] **#167 Two Sum II - Input Array Sorted** | Two Pointers | `O(n)` time

  - Topics: Two Pointers, Array
  - Key: Use sorted property

- [ ] **#173 Binary Search Tree Iterator** | Design | `O(1)` amortized time

  - Topics: Tree, Iterator, Stack
  - Approach: In-order traversal

- [ ] **#189 Rotate Array** | In-place rotation | `O(n)` time

  - Topics: Array, Rotation algorithm
  - Approaches: Reverse, reverse 3 times

- [ ] **#198 House Robber** | DP | `O(n)` time

  - Topics: DP, Array
  - Key: Can't rob adjacent houses

- [ ] **#199 Binary Tree Right Side View** | DFS/BFS | `O(n)` time

  - Topics: Tree, DFS, BFS
  - Approach: Track rightmost at each level

- [ ] **#200 Number of Islands** | DFS/BFS | `O(m*n)` time

  - Topics: Matrix, DFS, Connected components
  - Key: Mark visited cells

- [ ] **#206 Reverse Linked List** | Linked List | `O(n)` time

  - Topics: Linked List, Pointer manipulation
  - Approaches: Iterative, recursive

- [ ] **#207 Course Schedule** | Topological Sort | `O(V+E)` time

  - Topics: Graph, Topological sort, Cycle detection
  - Key: Detect cycle in directed graph

- [ ] **#209 Minimum Size Subarray Sum** | Sliding Window | `O(n)` time

  - Topics: Sliding Window, Array
  - Key: Two pointer technique

- [ ] **#210 Course Schedule II** | Topological Sort | `O(V+E)` time

  - Topics: Graph, Topological sort
  - Extension of #207

- [ ] **#213 House Robber II** | DP | `O(n)` time

  - Topics: DP, Array, Circular
  - Key: Split into two cases

- [ ] **#215 Kth Largest Element** | Heap/Quickselect | `O(n log k)` time

  - Topics: Heap, Array, Quickselect
  - Approaches: Min heap, quickselect

- [ ] **#221 Maximal Square** | DP | `O(m*n)` time

  - Topics: DP, Matrix
  - Key: DP state depends on three neighbors

- [ ] **#238 Product of Array Except Self** | Prefix/Suffix | `O(n)` time

  - Topics: Array, Prefix sum, Two-pass
  - Constraint: Cannot use division

- [ ] **#239 Sliding Window Maximum** | Deque | `O(n)` time

  - Topics: Sliding Window, Deque, Array
  - Key: Maintain decreasing deque

- [ ] **#240 Search a 2D Matrix II** | Binary Search | `O(m+n)` time

  - Topics: Matrix, Binary Search
  - Approach: Start from corner

- [ ] **#289 Game of Life** | In-place modification | `O(m*n)` time

  - Topics: Matrix, In-place, State encoding
  - Key: Encode old and new state

- [ ] **#322 Coin Change** | DP | `O(n*amount)` time

  - Topics: DP, Array
  - Key: Minimum coins for amount

- [ ] **#347 Top K Frequent Elements** | Heap/Quickselect | `O(n log k)` time

  - Topics: Heap, Hash Map, Array
  - Key: Use min heap of size k

---

### 🔴 Hard Problems (Advanced Mastery)

- [ ] **#37 Sudoku Solver** | Backtracking | `O(9^(n*n))` time

  - Topics: Backtracking, Matrix
  - Approach: Try digits, validate, backtrack

- [ ] **#41 First Missing Positive** | Array marking | `O(n)` time

  - Topics: Array, In-place marking
  - Key: Use array as hash

- [ ] **#42 Trapping Rain Water** | Two Pointers | `O(n)` time

  - Topics: Array, Two Pointers, DP
  - Important: Classic hard problem

- [ ] **#51 N-Queens** | Backtracking | `O(n!)` time

  - Topics: Backtracking, Array
  - Key: Check diagonal constraints

- [ ] **#72 Edit Distance** | DP | `O(m*n)` time

  - Topics: DP, String, Levenshtein
  - Key: Three operations: insert, delete, replace

- [ ] **#84 Largest Rectangle in Histogram** | Stack | `O(n)` time

  - Topics: Stack, Monotonic stack
  - Key: Maintain increasing stack

- [ ] **#85 Maximal Rectangle** | DP | `O(m*n)` time

  - Topics: DP, Matrix, Histogram
  - Key: Convert to histogram problem

- [ ] **#99 Recover Binary Search Tree** | Tree traversal | `O(n)` time

  - Topics: Tree, DFS, In-order traversal
  - Key: Find two swapped nodes

- [ ] **#124 Binary Tree Maximum Path Sum** | DFS | `O(n)` time

  - Topics: Tree, DFS, Recursion
  - Key: Track both sum and max

- [ ] **#132 Palindrome Partitioning II** | DP | `O(n²)` time

  - Topics: DP, String, Palindrome
  - Key: Minimum cuts needed

- [ ] **#135 Candy** | Greedy | `O(n)` time

  - Topics: Array, Greedy
  - Key: Two passes for constraints

- [ ] **#145 Binary Tree Postorder Traversal** | Tree traversal | `O(n)` time

  - Topics: Tree, Stack, Recursion
  - Morris traversal approach

- [ ] **#154 Find Minimum in Rotated Sorted Array II** | Binary Search | `O(n)` worst

  - Topics: Binary Search, Duplicates
  - Challenge: Handle duplicates

- [ ] **#214 Shortest Palindrome** | KMP/Hashing | `O(n)` time

  - Topics: String, KMP, Palindrome
  - Key: Avoid O(n²) with naive approach

- [ ] **#224 Basic Calculator** | Stack/String parsing | `O(n)` time

  - Topics: Stack, String parsing
  - Key: Handle + - ( ) operators

- [ ] **#227 Basic Calculator II** | Stack/String parsing | `O(n)` time

  - Topics: Stack, String parsing
  - Key: Handle \* / with precedence

- [ ] **#295 Find Median from Data Stream** | Heap design | `O(log n)` time

  - Topics: Heap, Design, Data structure
  - Key: Two heaps (max, min)

- [ ] **#297 Serialize and Deserialize Binary Tree** | Tree traversal | `O(n)` time

  - Topics: Tree, Serialization, Recursion
  - Approaches: Preorder, level-order

- [ ] **#301 Remove Invalid Parentheses** | BFS | `O(n * 2^n)` time

  - Topics: String, BFS, Validation
  - Key: Generate and validate

- [ ] **#329 Longest Increasing Path in Matrix** | DP/DFS | `O(m*n)` time

  - Topics: DP, DFS, Matrix, Memoization
  - Key: Combine DFS with DP

- [ ] **#330 Patching Array** | Greedy | `O(n log N)` time

  - Topics: Greedy, Array
  - Key: Track reachable range

- [ ] **#335 Self Crossing** | Array logic | `O(n)` time

  - Topics: Array, Line crossing
  - Key: Check crossing patterns

- [ ] **#352 Data Stream as Disjoint Intervals** | Data structure | `O(n)` time

  - Topics: Data structure, Intervals
  - Key: Merge intervals

- [ ] **#354 Russian Doll Envelopes** | DP/Binary Search | `O(n log n)` time

  - Topics: DP, Binary search, 2D sorting
  - Key: Longest increasing subsequence

- [ ] **#358 Rearrange String k Distance Apart** | Greedy/Heap | `O(n)` time

  - Topics: String, Heap, Greedy
  - Key: Use max heap with cooldown

---

## Mastery Action Items

### Phase 1: Foundation (Week 1-2)

**Goal: Solve all 25 Easy problems with confidence**

- [ ] **Daily Checklist:**

  - [ ] Code 1 problem from scratch (no looking at hints for 30 min minimum)

  - [ ] Identify the pattern used

  - [ ] Optimize the solution (time/space)

  - [ ] Write it in your problem template notes

  - [ ] Trace through with 2-3 test cases

- [ ] **Weekly Goals:**

  - [ ] Complete 3-4 easy problems per day

  - [ ] Achieve 95%+ success rate on easy problems

  - [ ] Complete in 10-15 minutes per problem

  - [ ] Understand all patterns used

  - [ ] Create notes for all problems

- [ ] **Pattern Recognition:**

  - [ ] Understand when to use Hash Map

  - [ ] Master Two Pointers technique

  - [ ] Learn basic String operations

  - [ ] Understand Array traversal patterns

  - [ ] Practice sorting and searching

- [ ] **Code Quality:**

  - [ ] Write clean, readable code

  - [ ] Add proper variable names

  - [ ] Include comments for complex logic

  - [ ] Verify edge cases before submission

  - [ ] Optimize after solving

### Phase 2: Intermediate (Week 3-5)

**Goal: Solve all 62 Medium problems with 70%+ success on first try**

- [ ] **Daily Checklist:**

  - [ ] Code 1 medium problem from scratch

  - [ ] Don't look at solution for 45+ minutes

  - [ ] Try multiple approaches

  - [ ] Compare time/space complexity

  - [ ] Document all approaches in notes

  - [ ] Trace through complex test cases

- [ ] **Weekly Goals:**

  - [ ] Complete 2-3 medium problems per day

  - [ ] Achieve 70%+ success rate on medium problems

  - [ ] Complete in 25-35 minutes per problem

  - [ ] Understand at least 2 different approaches per problem

  - [ ] Identify weaknesses in approach

- [ ] **Pattern Recognition:**

  - [ ] Master Sliding Window technique

  - [ ] Understand Binary Search variations

  - [ ] Learn DFS/BFS for trees and graphs

  - [ ] Practice Dynamic Programming basics

  - [ ] Understand backtracking approach

- [ ] **Optimization Practice:**

  - [ ] Always find O(n log n) solution if possible

  - [ ] Try to optimize space usage

  - [ ] Recognize when to use in-place modifications

  - [ ] Practice two-pass approaches

  - [ ] Learn to trade space for time wisely

### Phase 3: Advanced (Week 6-7)

**Goal: Solve all 20 Hard problems and solidify weak areas**

- [ ] **Daily Checklist:**

  - [ ] Solve 1 hard problem OR revisit 2-3 weak medium problems

  - [ ] Don't give up in first 60 minutes

  - [ ] Read solution only after honest attempt

  - [ ] Understand the key insight/aha moment

  - [ ] Rewrite solution from scratch after reading

  - [ ] Practice explaining approach out loud

- [ ] **Weekly Goals:**

  - [ ] Complete 1-2 hard problems per day

  - [ ] Achieve 40%+ success rate on hard problems

  - [ ] Revisit all weak medium problems

  - [ ] Understand the unique insight in each hard problem

  - [ ] Practice under time pressure

- [ ] **Advanced Techniques:**

  - [ ] Master complex data structures (Heap, segment trees)

  - [ ] Learn advanced DP techniques

  - [ ] Understand topological sorting

  - [ ] Practice monotonic stacks

  - [ ] Learn KMP/Z-algorithm for strings

- [ ] **Interview Preparation:**

  - [ ] Practice explaining approach verbally

  - [ ] Handle edge cases confidently

  - [ ] Discuss trade-offs between solutions

  - [ ] Code efficiently without bugs

  - [ ] Optimize on-the-fly based on feedback

### Phase 4: Mastery (Week 8)

**Goal: Can solve any Arrays/Strings problem in 20-30 minutes**

- [ ] **Daily Checklist:**

  - [ ] Solve 2-3 random problems (mixed difficulty)

  - [ ] Complete in 20-30 minutes each

  - [ ] First-try success rate target: 80%+

  - [ ] Practice thinking about edge cases first

  - [ ] Practice clean code implementation

- [ ] **Weekly Goals:**

  - [ ] Achieve 80%+ accuracy on medium problems

  - [ ] Achieve 50%+ accuracy on hard problems

  - [ ] Solve any easy problem in &lt;10 minutes

  - [ ] Solve any medium problem in &lt;25 minutes

  - [ ] Attempt hard problems confidently

- [ ] **Mock Interviews:**

  - [ ] Do 2 mock interviews with Arrays/Strings problems

  - [ ] Record yourself or practice with a friend

  - [ ] Aim for 45 minutes per interview

  - [ ] Get feedback on communication

  - [ ] Identify remaining weak areas

- [ ] **Final Review:**

  - [ ] Review all problem notes

  - [ ] Create final pattern reference guide

  - [ ] Solve your 5 weakest problems again

  - [ ] Practice explaining each pattern

  - [ ] Build confidence with easy problems

---

## Things To Do To Master Arrays & Strings

### 1. **Build Mental Models** ✨

```
Visual Learning:
□ Draw array indices: [0][1][2][3][4]
□ Visualize two pointers moving towards each other
□ Draw sliding window expanding and contracting
□ Visualize string character iteration
□ Draw 2D matrix indices (row, col)
□ Diagram recursive calls in a tree
□ Visualize stack operations

For each problem:
□ Draw initial state
□ Draw intermediate states
□ Draw final state
```

### 2. **Pattern Recognition System** 🎯

```
Create a pattern index:
□ Identify: What pattern is this problem?
  - Two Pointers?
  - Sliding Window?
  - Binary Search?
  - Hash Map?
  - DP?
  - Prefix Sum?
  - Backtracking?
  
□ When to use each pattern:
  - Sorted array + pairs → Two Pointers
  - Max/Min in subarray → Sliding Window
  - Find element in sorted → Binary Search
  - Frequency/lookup → Hash Map
  - Optimal subproblem → DP
  - Sum in range → Prefix Sum
  - Explore all combinations → Backtracking

□ Template for each pattern
□ Practice 5 problems with same pattern
□ Recognize pattern in 2-3 minutes
```

### 3. **Edge Case Mastery** 🛡️

```
Always check:
□ Empty array/string (length = 0)
□ Single element (length = 1)
□ Two elements (length = 2)
□ All same elements
□ All different elements
□ Negative numbers (if applicable)
□ Large numbers (overflow risk)
□ Duplicates
□ Special characters (for strings)
□ Whitespace (for strings)
□ Null values

Create edge case checklist for each problem type
Test edge cases before submission
Trace through edge case by hand
```

### 4. **Optimization Practice** ⚡

```
For every solution:
□ Start with brute force (obvious approach)
□ Calculate: Time O(?) Space O(?)
□ Think: Can I optimize time?
  - Use data structure (HashMap)?
  - Reduce iterations?
  - Eliminate nested loops?
  
□ Think: Can I optimize space?
  - Do in-place if possible?
  - Use pointers instead of arrays?
  - Trade space for time?

□ Write optimized solution
□ Compare complexity
□ Document both approaches in notes

Goal: Always reach optimal solution
```

### 5. **Code Efficiency Drills** ⚙️

```
Practice:
□ Type out 5 template solutions daily
□ No IDE - write in notepad first
□ Must be syntactically correct
□ Time yourself: Target 10 min for easy, 20 min for medium
□ Review for bugs immediately after
□ Practice until muscle memory

Benefits:
□ Avoid syntax errors in interviews
□ Code faster in real interviews
□ Fewer bugs = confidence
□ Can focus on problem-solving not coding
```

### 6. **Complexity Analysis Muscle** 💪

```
For every problem:
□ Write complexity before coding
□ Analyze the loops:
  - Single loop = O(n)
  - Nested loops = O(n²) or higher
  - Divide & conquer = O(n log n)
  - Exponential = O(2^n)
  
□ Analyze space:
  - No extra data structures = O(1)
  - HashMap with all elements = O(n)
  - Recursion depth d = O(d)
  
□ Compare with optimal
□ Explain complexity during interview
□ Identify when to optimize

Practice: Speak complexity analysis for 1 minute
```

### 7. **Communication Skills** 🗣️

```
For each problem:
□ Read problem out loud (clarify understanding)
□ Ask clarifying questions:
  - Can array be empty?
  - Can array have duplicates?
  - What about negative numbers?
  - Space constraint?
  - Time constraint?

□ Explain approach before coding:
  "I'll use a two-pointer approach because...
   The algorithm is:
   1. ...
   2. ...
   3. ...
   Time complexity: ...
   Space complexity: ..."

□ Code while explaining
□ Trace through example while coding
□ Verify solution at end

Practice: Record yourself explaining
```

### 8. **Spaced Repetition Schedule** 📅

```
Study Pattern:
Day 1: Learn problem, solve it
Day 3: Redo problem without looking
Day 7: Redo problem + optimize
Day 14: Redo problem from memory
Day 30: Redo problem type-out style
Day 60: Random mix with other problems

Track:
□ Date learned
□ Date first solved
□ Attempts needed
□ Time to solve
□ Any mistakes made
□ Pattern it belongs to
```

### 9. **Weak Area Identification** 🔍

```
Track:
□ Problems you got wrong
□ Problems taking too long
□ Patterns you struggle with
□ Edge cases you miss
□ Optimization you can't find

Weekly Analysis:
□ Which patterns caused failures?
□ Which edge cases were missed?
□ Which type of problem is weak?
□ Do 5 extra problems in weak area
□ Re-read notes for weak pattern
□ Ask for help if stuck for 30+ min
```

### 10. **Interview Simulation** 🎬

```
Do Mock Interviews:
□ Set 45-50 minute timer
□ Solve 1 problem like real interview
□ Think out loud the whole time
□ Write clean code
□ Handle edge cases
□ Optimize before submitting
□ Explain approach clearly

Frequency:
□ Week 3: First mock (easy problem)
□ Week 4: Second mock (medium problem)
□ Week 6: Third mock (medium problem)
□ Week 7: Fourth mock (hard problem)
□ Week 8: Fifth mock (random difficulty)

Feedback:
□ Record and review yourself
□ Get feedback from friend
□ Identify communication issues
□ Fix explanation style
□ Improve code clarity
```

### 11. **Resource Management** 📚

```
Best Resources:
□ LeetCode discussions (after solving)
□ YouTube explanations (after attempt)
□ GeeksforGeeks (supplementary)
□ Your own notes (primary reference)

Time Allocation:
□ 60% Problem solving (actual coding)
□ 20% Understanding solutions
□ 15% Creating notes
□ 5% Watching videos (optional)

Rule:
□ NEVER watch solution before 30 min attempt
□ ALWAYS attempt problem yourself first
□ Only read solution after honest failure
□ Rewrite solution from scratch after reading
```

### 12. **Consistency & Accountability** 📊

```
Daily Habits:
□ Code for 5-6 hours daily
□ Solve 3-4 problems per day
□ Update tracking spreadsheet
□ Review notes from day 1 pattern weekly
□ Celebrate small wins

Weekly Review:
□ Problems solved: ___ / Target
□ Success rate: ___% (Target: 80%+)
□ Average time per problem: ___ min
□ New patterns learned: ___
□ Weak areas identified: ___

Accountability:
□ Share progress with friend
□ Join study group
□ Post daily on social media
□ Set weekly goals publicly
□ Track progress visually (checklist)
```

---

## Quick Reference: Command Cheat Sheet

```java
// ARRAYS
Arrays.sort(arr);
Arrays.binarySearch(arr, target);
Arrays.copyOf(arr, length);
System.arraycopy(src, srcPos, dest, destPos, length);
Arrays.equals(arr1, arr2);
Arrays.fill(arr, value);
Arrays.stream(arr);

// STRINGS
str.charAt(index);
str.substring(start, end);
str.indexOf(char);
str.lastIndexOf(char);
str.toCharArray();
str.toUpperCase() / toLowerCase();
str.trim() / strip();
str.replace(old, new);
str.split(regex);
String.join(delimiter, array);
String.format(format, args);

// COLLECTIONS
ArrayList<Integer> list = new ArrayList<>();
list.add(element);
list.get(index);
list.set(index, element);
list.remove(index);
list.size();
Collections.sort(list);
Collections.reverse(list);

// MAPS & SETS
Map<K, V> map = new HashMap<>();
map.put(key, value);
map.get(key);
map.getOrDefault(key, defaultValue);
map.containsKey(key);
map.remove(key);
Set<T> set = new HashSet<>();
set.add(element);
set.contains(element);
set.remove(element);
```

---

**Remember:** Master is not about speed, it's about deep understanding. Solve fewer problems deeply than many problems superficially.

Good luck! You've got this! 🚀