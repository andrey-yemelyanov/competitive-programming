# Problem name: 11824 A Minimum Land Price
# Problem url: https://uva.onlinejudge.org/external/118/11824.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

BUDGET = 5000000
def main():
	n_tests = int(readline())
	for i in range(n_tests):
		land_prices = []
		while True:
			price = int(readline())
			if price == 0:
				break
			land_prices.append(price)
		min_cost = get_min_cost(land_prices)
		if min_cost > BUDGET:
			print("Too expensive")
		else:
			print(min_cost)

def get_min_cost(land_prices):
	land_prices = sorted(land_prices, reverse=True)
	total_cost = 0
	for land in range(len(land_prices)):
		year = land + 1
		total_cost += 2 * pow(land_prices[land], year)
	return total_cost

if __name__=="__main__":
    main()
