arr = [1, 0, 2, 0, 0, 3, 0, 4, 0, 5]

slow = 0
for fast in range(len(arr)):
    if arr[fast] != 0:
        arr[fast], arr[slow] = arr[slow], arr[fast]
        slow += 1
print(*arr)