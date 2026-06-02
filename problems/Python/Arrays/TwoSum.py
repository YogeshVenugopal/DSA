# inputs
nums = [2, 7, 11, 15]
target = 9

# Main functionality
seen = {}

for i, num in enumerate(nums):
    need = target - num

    if need in seen:
        print([seen[need], i])
    else:
        seen[num] = i
