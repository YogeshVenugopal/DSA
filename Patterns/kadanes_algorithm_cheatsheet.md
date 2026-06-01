# 🧠 Kadane's Algorithm — Java Cheatsheet

> **Goal:** Find the **maximum sum of a contiguous subarray** within a given array.

---

## 🤔 The Core Idea (Think of it like this)

Imagine you're walking through the array and collecting money (positive numbers) or paying fines (negative numbers).

At each step, you ask yourself:

> *"Should I start fresh from here, or is it better to carry what I already have?"*

That's literally all Kadane's Algorithm does.

---

## 🔑 Two Key Variables

| Variable      | What it tracks                                      |
|---------------|-----------------------------------------------------|
| `currentSum`  | Best sum **ending at the current index**            |
| `maxSum`      | Best sum **seen so far** (your final answer)        |

---

## 📐 The Decision at Every Step

```
currentSum = max(arr[i], currentSum + arr[i])
```

Plain English:
- If `arr[i]` alone is bigger → **start fresh** from here
- If `currentSum + arr[i]` is bigger → **extend** the previous subarray

Then update `maxSum` if `currentSum` is the new best.

---

## ✅ Basic Java Code

```java
public class KadanesAlgorithm {

    public static int maxSubarraySum(int[] arr) {
        int currentSum = arr[0];  // Start from first element
        int maxSum     = arr[0];  // Best answer so far

        for (int i = 1; i < arr.length; i++) {
            // Should I start fresh, or keep going?
            currentSum = Math.max(arr[i], currentSum + arr[i]);

            // Did I just beat my best record?
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Max Sum = " + maxSubarraySum(arr)); // Output: 6
    }
}
```

---

## 🪜 Step-by-Step Walkthrough

**Array:** `[-2, 1, -3, 4, -1, 2, 1, -5, 4]`

| i | arr[i] | currentSum (before) | Decision                    | currentSum (after) | maxSum |
|---|--------|---------------------|-----------------------------|-------------------|--------|
| 0 | -2     | —                   | Start here                  | **-2**            | **-2** |
| 1 | 1      | -2                  | 1 > (-2+1=-1) → Start fresh | **1**             | **1**  |
| 2 | -3     | 1                   | -3 < (1-3=-2) → Extend      | **-2**            | 1      |
| 3 | 4      | -2                  | 4 > (-2+4=2) → Start fresh  | **4**             | **4**  |
| 4 | -1     | 4                   | -1 < (4-1=3) → Extend       | **3**             | 4      |
| 5 | 2      | 3                   | 2 < (3+2=5) → Extend        | **5**             | **5**  |
| 6 | 1      | 5                   | 1 < (5+1=6) → Extend        | **6**             | **6**  |
| 7 | -5     | 6                   | -5 < (6-5=1) → Extend       | **1**             | 6      |
| 8 | 4      | 1                   | 4 > (1+4=5) → Extend        | **5**             | 6      |

**✅ Answer: 6** → from subarray `[4, -1, 2, 1]`

---

## 🔍 Extended Version — Also Returns the Subarray

```java
public class KadanesWithSubarray {

    public static void maxSubarrayWithIndices(int[] arr) {
        int currentSum = arr[0];
        int maxSum     = arr[0];

        int start = 0, end = 0;       // Indices of the best subarray
        int tempStart = 0;            // Tracks where the current subarray started

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > currentSum + arr[i]) {
                currentSum = arr[i];
                tempStart  = i;       // Starting fresh from i
            } else {
                currentSum = currentSum + arr[i];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                start  = tempStart;
                end    = i;
            }
        }

        System.out.println("Max Sum = " + maxSum);
        System.out.print("Subarray: ");
        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        maxSubarrayWithIndices(arr);
        // Max Sum = 6
        // Subarray: 4 -1 2 1
    }
}
```

---

## ⚡ Complexity

| Type  | Value  | Why                                    |
|-------|--------|----------------------------------------|
| Time  | O(n)   | Single pass through the array          |
| Space | O(1)   | Only two variables used, no extra array|

---

## 🚧 Edge Cases to Remember

| Situation                        | Behavior                                      |
|----------------------------------|-----------------------------------------------|
| All negative numbers             | Returns the **least negative** (single element) |
| Single element                   | Returns that element                          |
| All positive numbers             | Returns sum of the entire array               |
| Array with zeros in the middle   | Handled correctly — zero doesn't hurt         |

**Example — All Negatives:**
```java
int[] arr = {-3, -1, -4, -2};
// Output: -1  ← the least bad option
```

---

## 🧩 Variants You Might See in Interviews

| Variant                                 | Hint                                              |
|-----------------------------------------|---------------------------------------------------|
| Max product subarray                    | Track both min and max (negatives flip signs)     |
| Circular subarray max sum               | `answer = max(kadane(arr), totalSum - minSubarray)` |
| Count of subarrays with sum = K         | Use prefix sums + HashMap                         |
| Max sum subarray of size exactly K      | Use sliding window instead                        |

---

## 🎯 Quick Recall (3-line mental model)

```
1. Start: currentSum = maxSum = arr[0]
2. Each step: currentSum = max(arr[i], currentSum + arr[i])
3. Each step: maxSum = max(maxSum, currentSum)
```

> **Start fresh or keep going. Track the best. That's it.**
