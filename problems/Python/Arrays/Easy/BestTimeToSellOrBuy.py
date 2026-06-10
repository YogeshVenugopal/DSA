prices = [2,4,1]

minPrice = float('inf')
maxProfit = 0

for price in prices:
    minPrice = min(minPrice, price)
    maxProfit = max(maxProfit, price - minPrice)

print(maxProfit)