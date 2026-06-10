def solution(nums):
    maxSum = nums[0]
    currentSum = nums[0]

    for i in range(1, len(nums)):
        currentSum = currentSum if currentSum + nums[i] < currentSum else currentSum + nums[i]
        maxSum = maxSum if maxSum > currentSum else currentSum

    return maxSum

nums = [-2,1,-3,4,-1,2,1,-5,4]
print(solution(nums))
