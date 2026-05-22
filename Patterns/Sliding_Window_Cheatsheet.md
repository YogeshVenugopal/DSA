# Sliding Window Cheatsheet

## What is Sliding Window?

Sliding Window is a technique where you **maintain a window (subarray) of elements and slide it across the array** to solve problems efficiently. Instead of recalculating for every subarray, you add and remove elements as the window moves.

**Key Idea:** Expand the window, then shrink it, while maintaining some property or state about the window's contents.

---

## When to Use Sliding Window?

✅ **Use Sliding Window when:**
- Finding longest/shortest substring or subarray
- Working with contiguous sequences
- Need to avoid recalculating from scratch
- Problems involve "at most" or "at least" conditions
- Trying to optimize from O(n²) to O(n) or O(n log n)
- Need to track frequency of elements in a range

❌ **Don't use when:**
- Array is not contiguous (scattered elements)
- Problem requires non-contiguous elements

---

## Two Types of Sliding Window

### 1. **Fixed Window Size**
Window size is constant. Simply slide it across the array.

```java
int windowSize = k;
for (int i = 0; i <= arr.length - windowSize; i++) {
    // Process window from i to i + windowSize - 1
}
```

### 2. **Dynamic Window Size**
Window size changes. Expand right boundary, shrink left boundary based on condition.

```java
int left = 0;
for (int right = 0; right < arr.length; right++) {
    // Expand window by adding arr[right]
    
    while (condition) {
        // Shrink window by removing arr[left]
        left++;
    }
    
    // Process current window
}
```

---

## Common Problems & Solutions

### Problem 1: Maximum Sum of Subarray (Fixed Window)
**Problem:** Find maximum sum of a subarray of size k.

```java
public static int maxSumSubarray(int[] arr, int k) {
    if (arr.length < k) return -1;
    
    // Calculate sum of first window
    int windowSum = 0;
    for (int i = 0; i < k; i++) {
        windowSum += arr[i];
    }
    int maxSum = windowSum;
    
    // Slide the window
    for (int i = k; i < arr.length; i++) {
        // Remove leftmost element of previous window
        windowSum = windowSum - arr[i - k];
        // Add new rightmost element
        windowSum = windowSum + arr[i];
        // Update maximum
        maxSum = Math.max(maxSum, windowSum);
    }
    return maxSum;
}

// Example
int[] arr = {1, 4, 2, 10, 2, 3, 1, 0, 20};
int result = maxSumSubarray(arr, 4);
// Output: 24 (subarray [10, 2, 3, 1, 0, 20] ... adjust for size 4)
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1)

---

### Problem 2: Longest Substring Without Repeating Characters
**Problem:** Find the length of longest substring with no duplicate characters.

```java
public static int lengthOfLongestSubstring(String s) {
    if (s.length() == 0) return 0;
    
    // Map to store character and its last seen index
    HashMap<Character, Integer> charIndex = new HashMap<>();
    int maxLength = 0;
    int left = 0;
    
    for (int right = 0; right < s.length(); right++) {
        char c = s.charAt(right);
        
        // If character is found and is in current window
        if (charIndex.containsKey(c) && charIndex.get(c) >= left) {
            // Move left pointer to skip the duplicate
            left = charIndex.get(c) + 1;
        }
        
        // Update character's latest position
        charIndex.put(c, right);
        
        // Update max length
        maxLength = Math.max(maxLength, right - left + 1);
    }
    return maxLength;
}

// Example
String s = "abcabcbb";
int result = lengthOfLongestSubstring(s);
// Output: 3 (substrings: "abc")

String s2 = "dvdf";
int result2 = lengthOfLongestSubstring(s2);
// Output: 3 (substring: "vdf")
```

**Time Complexity:** O(n)  
**Space Complexity:** O(min(n, m)) where m is charset size

---

### Problem 3: Minimum Window Substring
**Problem:** Find smallest substring containing all characters of a target string.

```java
public static String minWindow(String s, String t) {
    if (s == null || s.length() == 0 || t == null || t.length() == 0) {
        return "";
    }
    
    // Dictionary which keeps count of all unique characters in t
    HashMap<Character, Integer> dictT = new HashMap<>();
    for (char c : t.toCharArray()) {
        dictT.put(c, dictT.getOrDefault(c, 0) + 1);
    }
    
    int required = dictT.size();
    int left = 0, right = 0;
    int formed = 0;  // Number of unique characters in current window with desired frequency
    
    // Dictionary to keep count of characters in current window
    HashMap<Character, Integer> windowCounts = new HashMap<>();
    
    // ans tuple of the form (window length, left, right)
    int[] ans = {Integer.MAX_VALUE, 0, 0};
    
    while (right < s.length()) {
        char c = s.charAt(right);
        windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);
        
        // If frequency of current character matches desired count, increment formed
        if (dictT.containsKey(c) && windowCounts.get(c).equals(dictT.get(c))) {
            formed++;
        }
        
        // Try to contract the window until it no longer satisfies the condition
        while (left <= right && formed == required) {
            char c1 = s.charAt(left);
            
            // Save the smallest window
            if (right - left + 1 < ans[0]) {
                ans[0] = right - left + 1;
                ans[1] = left;
                ans[2] = right;
            }
            
            windowCounts.put(c1, windowCounts.get(c1) - 1);
            if (dictT.containsKey(c1) && windowCounts.get(c1) < dictT.get(c1)) {
                formed--;
            }
            
            left++;
        }
        
        right++;
    }
    
    return ans[0] == Integer.MAX_VALUE ? "" : s.substring(ans[1], ans[2] + 1);
}

// Example
String s = "ADOBECODEBANC";
String t = "ABC";
String result = minWindow(s, t);
// Output: "BANC"
```

**Time Complexity:** O(|S| + |T|)  
**Space Complexity:** O(|S| + |T|)

---

### Problem 4: Maximum Consecutive Ones III (K Flips)
**Problem:** Find maximum consecutive 1s if you can flip at most k 0s to 1s.

```java
public static int longestOnes(int[] nums, int k) {
    int left = 0;
    int maxLength = 0;
    int zeroCount = 0;  // Count of zeros in current window
    
    for (int right = 0; right < nums.length; right++) {
        if (nums[right] == 0) {
            zeroCount++;
        }
        
        // If zeros exceed k, shrink window from left
        while (zeroCount > k) {
            if (nums[left] == 0) {
                zeroCount--;
            }
            left++;
        }
        
        // Update max length
        maxLength = Math.max(maxLength, right - left + 1);
    }
    return maxLength;
}

// Example
int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
int k = 2;
int result = longestOnes(nums, k);
// Output: 6 (flip 0s at positions 6,7 or 9,10 to get 6 consecutive 1s)
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1)

---

### Problem 5: Fruits Into Baskets
**Problem:** Pick fruits from an array where you can hold at most 2 types. Find max fruits.

```java
public static int totalFruit(int[] fruits) {
    int left = 0;
    int maxFruits = 0;
    HashMap<Integer, Integer> fruitCount = new HashMap<>();
    
    for (int right = 0; right < fruits.length; right++) {
        // Add current fruit to basket
        fruitCount.put(fruits[right], fruitCount.getOrDefault(fruits[right], 0) + 1);
        
        // If more than 2 types of fruits, remove from left
        while (fruitCount.size() > 2) {
            int leftFruit = fruits[left];
            fruitCount.put(leftFruit, fruitCount.get(leftFruit) - 1);
            
            // Remove if count becomes 0
            if (fruitCount.get(leftFruit) == 0) {
                fruitCount.remove(leftFruit);
            }
            left++;
        }
        
        // Update max
        maxFruits = Math.max(maxFruits, right - left + 1);
    }
    return maxFruits;
}

// Example
int[] fruits = {1, 2, 1};
int result = totalFruit(fruits);
// Output: 3 (can pick all: type 1 and type 2)

int[] fruits2 = {0, 1, 2, 2};
int result2 = totalFruit(fruits2);
// Output: 3 (can pick [1, 2, 2])
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1) (max 2 fruit types)

---

### Problem 6: Permutation in String (Pattern Matching)
**Problem:** Check if one string's permutation is a substring of another.

```java
public static boolean checkInclusion(String s1, String s2) {
    if (s1.length() > s2.length()) return false;
    
    // Count characters in s1
    int[] s1Count = new int[26];
    for (char c : s1.toCharArray()) {
        s1Count[c - 'a']++;
    }
    
    // Sliding window over s2
    int[] windowCount = new int[26];
    int left = 0;
    
    for (int right = 0; right < s2.length(); right++) {
        // Add character to right of window
        windowCount[s2.charAt(right) - 'a']++;
        
        // Maintain window size = s1.length()
        if (right - left + 1 > s1.length()) {
            windowCount[s2.charAt(left) - 'a']--;
            left++;
        }
        
        // Check if window matches s1
        if (arraysEqual(s1Count, windowCount)) {
            return true;
        }
    }
    return false;
}

private static boolean arraysEqual(int[] arr1, int[] arr2) {
    for (int i = 0; i < 26; i++) {
        if (arr1[i] != arr2[i]) return false;
    }
    return true;
}

// Example
String s1 = "ab";
String s2 = "eidbaooo";
boolean result = checkInclusion(s1, s2);
// Output: true (permutation "ba" exists in s2)
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1) (fixed array size 26)

---

### Problem 7: Sliding Window Maximum
**Problem:** Find maximum in each window of size k sliding across array.

```java
public static int[] maxSlidingWindow(int[] nums, int k) {
    if (nums.length == 0 || k == 0) return new int[0];
    
    // Deque to store indices
    Deque<Integer> deque = new LinkedList<>();
    int[] result = new int[nums.length - k + 1];
    int resultIndex = 0;
    
    for (int i = 0; i < nums.length; i++) {
        // Remove indices outside current window
        while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
            deque.pollFirst();
        }
        
        // Remove elements smaller than current element
        while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
            deque.pollLast();
        }
        
        // Add current index
        deque.addLast(i);
        
        // The maximum is at front of deque
        if (i >= k - 1) {
            result[resultIndex++] = nums[deque.peekFirst()];
        }
    }
    return result;
}

// Example
int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
int k = 3;
int[] result = maxSlidingWindow(nums, k);
// Output: [3, 3, 5, 5, 6, 7]
```

**Time Complexity:** O(n)  
**Space Complexity:** O(k)

---

## Key Patterns to Recognize

### Pattern 1: At Most/At Least Problems
```
"Find longest substring with AT MOST k distinct characters"
→ Use dynamic window with left pointer shrinking when condition breaks
```

### Pattern 2: Fixed Window Size
```
"Find maximum sum of k consecutive elements"
→ Use fixed window, slide it, calculate incrementally
```

### Pattern 3: Target Sum/Count
```
"Find minimum window containing all required characters"
→ Expand right until found, shrink left to minimize
```

### Pattern 4: Frequency Matching
```
"Check if pattern exists as substring"
→ Track frequencies in window, compare with target
```

---

## Sliding Window Template

```java
public static void slidingWindow(String s) {
    int left = 0;
    HashMap<Character, Integer> window = new HashMap<>();
    HashMap<Character, Integer> need = new HashMap<>();  // What we need
    
    // Initialize need with requirements
    
    for (int right = 0; right < s.length(); right++) {
        // 1. Expand window: add s[right]
        window.put(s.charAt(right), window.getOrDefault(s.charAt(right), 0) + 1);
        
        // 2. Shrink window when condition met
        while (windowValid(window, need)) {
            // Update result if needed
            
            // Remove s[left]
            window.put(s.charAt(left), window.get(s.charAt(left)) - 1);
            if (window.get(s.charAt(left)) == 0) {
                window.remove(s.charAt(left));
            }
            left++;
        }
    }
}
```

---

## Key Takeaways

| Aspect | Details |
|--------|---------|
| **Best For** | Substrings, subarrays, consecutive sequences |
| **Time Complexity** | Usually O(n) instead of O(n²) or O(n³) |
| **Space Complexity** | Usually O(k) or O(1) |
| **When to Use** | Contiguous elements, optimization problems |
| **Two Pointers Needed** | Left (shrink) & Right (expand) |

---

## Quick Checklist Before Using Sliding Window

- [ ] Is the problem about contiguous subarrays/substrings?
- [ ] Can I maintain a state (count, sum, etc.) as I slide?
- [ ] Is the window size fixed or dynamic?
- [ ] Do I need to track characters/elements in the window?
- [ ] Can I solve it in O(n) with sliding instead of O(n²)?

---

## Practice Tips

1. **Identify the window:** What property defines the valid window?
2. **Track the state:** What do you maintain in the window?
3. **Expand first:** Move right pointer to expand window
4. **Shrink when needed:** Move left pointer to shrink when condition breaks
5. **Process:** Store result before shrinking
6. **Test edge cases:** Empty string, k > length, all same characters

---

## Time & Space Complexity Summary

| Problem | Time | Space |
|---------|------|-------|
| Max Sum Subarray | O(n) | O(1) |
| Longest Substring No Repeat | O(n) | O(min(n,m)) |
| Minimum Window | O(n) | O(min(n,m)) |
| Max Consecutive Ones | O(n) | O(1) |
| Fruits Into Baskets | O(n) | O(1) |
| Permutation Pattern | O(n) | O(1) |
| Sliding Window Maximum | O(n) | O(k) |

---

## Common Mistakes to Avoid

❌ **Mistake 1:** Growing window without shrinking (not solving dynamic problems)  
✅ **Fix:** Use while loop to shrink when condition breaks

❌ **Mistake 2:** Shrinking too much (missing valid windows)  
✅ **Fix:** Carefully check when to start shrinking

❌ **Mistake 3:** Not tracking state correctly (using wrong data structure)  
✅ **Fix:** Use HashMap for frequencies, Deque for max/min

❌ **Mistake 4:** Forgetting to update result at right time  
✅ **Fix:** Update before shrinking (we might shrink away the answer)

---

**Master sliding window and efficiently solve substring/subarray problems!** 🚀
