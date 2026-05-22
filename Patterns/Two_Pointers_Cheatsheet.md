# Two Pointers Cheatsheet

## What is Two Pointers?

Two Pointers is a technique where you use **two variables (pointers) to traverse through a data structure** (usually an array or linked list) to solve problems efficiently. Instead of using nested loops, you move two pointers strategically to find a solution.

**Key Idea:** Use one pointer starting from the beginning and another from the end (or both from the beginning with different speeds).

---

## When to Use Two Pointers?

✅ **Use Two Pointers when:**
- Working with sorted arrays
- Need to find pairs or triplets
- Need to remove/reverse elements
- Working with linked lists
- Need to compare elements from two ends
- Trying to avoid O(n²) time complexity from nested loops

---

## Types of Two Pointers Approaches

### 1. **Opposite Direction Approach**
- One pointer starts at the **beginning** (left)
- One pointer starts at the **end** (right)
- They move towards each other

```java
int left = 0;
int right = arr.length - 1;
while (left < right) {
    // Do something
    if (condition) {
        left++;
    } else {
        right--;
    }
}
```

### 2. **Same Direction Approach**
- Both pointers start at the **beginning**
- One moves faster than the other
- Common in cycle detection and removing duplicates

```java
int slow = 0;
int fast = 0;
while (fast < arr.length) {
    // Process
    slow++;
    fast += 2; // or other increment
}
```

---

## Common Problems & Solutions

### Problem 1: Two Sum (Sorted Array)
**Problem:** Find two numbers that add up to a target in a sorted array.

```java
public static int[] twoSum(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    
    while (left < right) {
        int sum = arr[left] + arr[right];
        
        if (sum == target) {
            return new int[]{left, right};
        } else if (sum < target) {
            left++;  // Need larger sum, move left pointer right
        } else {
            right--; // Need smaller sum, move right pointer left
        }
    }
    return new int[]{-1, -1}; // Not found
}

// Example
int[] arr = {1, 3, 5, 7, 9};
int[] result = twoSum(arr, 12);
// Output: [2, 4] (arr[2]=5, arr[4]=9, sum=14... adjust target)
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1)

---

### Problem 2: Reverse a String/Array
**Problem:** Reverse an array in-place without extra space.

```java
public static void reverse(char[] arr) {
    int left = 0, right = arr.length - 1;
    
    while (left < right) {
        // Swap elements
        char temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
        
        left++;
        right--;
    }
}

// Example
char[] arr = {'h', 'e', 'l', 'l', 'o'};
reverse(arr);
// Output: ['o', 'l', 'l', 'e', 'h']
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1)

---

### Problem 3: Container With Most Water
**Problem:** Find two lines that form a container with maximum area.

```java
public static int maxArea(int[] height) {
    int left = 0, right = height.length - 1;
    int maxArea = 0;
    
    while (left < right) {
        // Calculate current area
        int width = right - left;
        int currentHeight = Math.min(height[left], height[right]);
        int area = width * currentHeight;
        
        maxArea = Math.max(maxArea, area);
        
        // Move the pointer pointing to smaller height
        if (height[left] < height[right]) {
            left++;
        } else {
            right--;
        }
    }
    return maxArea;
}

// Example
int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
int result = maxArea(height);
// Output: 49 (width=8, height=6, area=48... check calculation)
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1)

---

### Problem 4: Remove Duplicates from Sorted Array
**Problem:** Remove duplicates in-place and return new length.

```java
public static int removeDuplicates(int[] arr) {
    if (arr.length == 0) return 0;
    
    int left = 0;  // Position to place next unique element
    
    for (int right = 1; right < arr.length; right++) {
        if (arr[right] != arr[left]) {
            left++;
            arr[left] = arr[right];
        }
    }
    return left + 1;
}

// Example
int[] arr = {1, 1, 2, 2, 3, 3, 4};
int newLength = removeDuplicates(arr);
// Output: 4 (arr becomes {1, 2, 3, 4, ...})
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1)

---

### Problem 5: Valid Palindrome
**Problem:** Check if a string is a palindrome (ignoring non-alphanumeric characters).

```java
public static boolean isPalindrome(String s) {
    int left = 0, right = s.length() - 1;
    
    while (left < right) {
        // Skip non-alphanumeric characters from left
        while (left < right && !Character.isAlphaNumeric(s.charAt(left))) {
            left++;
        }
        
        // Skip non-alphanumeric characters from right
        while (left < right && !Character.isAlphaNumeric(s.charAt(right))) {
            right--;
        }
        
        // Compare characters (case-insensitive)
        if (Character.toLowerCase(s.charAt(left)) != 
            Character.toLowerCase(s.charAt(right))) {
            return false;
        }
        
        left++;
        right--;
    }
    return true;
}

// Example
System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
// Output: true
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1)

---

### Problem 6: Merge Sorted Array
**Problem:** Merge two sorted arrays into one (with enough space).

```java
public static void merge(int[] nums1, int m, int[] nums2, int n) {
    int p1 = m - 1;      // Pointer for nums1
    int p2 = n - 1;      // Pointer for nums2
    int p = m + n - 1;   // Pointer for final position
    
    while (p1 >= 0 && p2 >= 0) {
        if (nums1[p1] > nums2[p2]) {
            nums1[p] = nums1[p1];
            p1--;
        } else {
            nums1[p] = nums2[p2];
            p2--;
        }
        p--;
    }
    
    // If nums2 has remaining elements, copy them
    while (p2 >= 0) {
        nums1[p] = nums2[p2];
        p2--;
        p--;
    }
}

// Example
int[] nums1 = {1, 2, 3, 0, 0, 0};
int[] nums2 = {2, 5, 6};
merge(nums1, 3, nums2, 3);
// Output: nums1 becomes {1, 2, 2, 3, 5, 6}
```

**Time Complexity:** O(m + n)  
**Space Complexity:** O(1)

---

### Problem 7: Linked List Cycle Detection
**Problem:** Detect if a linked list has a cycle.

```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public static boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) return false;
    
    ListNode slow = head;
    ListNode fast = head.next;
    
    while (slow != fast) {
        if (fast == null || fast.next == null) {
            return false; // No cycle
        }
        slow = slow.next;      // Move 1 step
        fast = fast.next.next;  // Move 2 steps
    }
    return true; // Cycle detected
}
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1)

---

## Key Takeaways

| Aspect | Details |
|--------|---------|
| **Best For** | Sorted arrays, pairs, avoiding nested loops |
| **Time Complexity** | Usually O(n) instead of O(n²) |
| **Space Complexity** | Usually O(1) - in-place operations |
| **Common Patterns** | Opposite ends, same start (slow/fast) |
| **When to Move Pointers** | Based on condition - which pointer to advance? |

---

## Quick Checklist Before Using Two Pointers

- [ ] Is the array/list sorted or partially sorted?
- [ ] Am I looking for pairs/triplets?
- [ ] Can I use opposite direction or same direction approach?
- [ ] Will moving pointer help me skip unnecessary comparisons?
- [ ] Can I solve it with O(n) instead of O(n²)?

---

## Practice Tips

1. **Start simple:** Practice with Two Sum before complex problems
2. **Visualize:** Draw the array and trace through pointer movements
3. **Identify pattern:** Know when to use opposite vs same direction
4. **Why move?** Always understand why you're moving a specific pointer
5. **Verify:** Test with edge cases (empty array, single element, etc.)

---

## Time & Space Complexity Summary

| Problem | Time | Space |
|---------|------|-------|
| Two Sum | O(n) | O(1) |
| Reverse Array | O(n) | O(1) |
| Container With Most Water | O(n) | O(1) |
| Remove Duplicates | O(n) | O(1) |
| Valid Palindrome | O(n) | O(1) |
| Merge Sorted Array | O(m+n) | O(1) |
| Cycle Detection | O(n) | O(1) |

---

**Master two pointers and solve problems that seem O(n²) in just O(n)!** 🚀
