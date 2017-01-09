# Problem name: 457 Linear Cellular Automata
# Problem url: https://uva.onlinejudge.org/external/4/457.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	d = {
		0: ' ',
		1: '.',
		2: 'x',
		3: 'W'
	}
	n_tests = int(readline())
	for i in range(n_tests):
		readline() # consume empty line
		dna = [int(x) for x in readline().split()]
		populations = simulate(dna)
		for population in populations:
			print("".join([d[x] for x in population]))
		if i < n_tests - 1:
			print()

def simulate(dna):
	n_dishes = 40
	n_days = 50
	populations = []
	dishes = [0 for x in range(n_dishes)]
	dishes[19] = 1
	populations.append(dishes)
	for day in range(n_days - 1):
		new_dishes = [0 for x in range(n_dishes)]
		for dish in range(n_dishes):
			total_densities = dishes[dish]
			if dish == 0:
				total_densities += dishes[1]
			elif dish == n_dishes - 1:
				total_densities += dishes[n_dishes - 2]
			else:
				total_densities += dishes[dish - 1]
				total_densities += dishes[dish + 1]
			new_dishes[dish] = dna[total_densities]
		populations.append(new_dishes)
		dishes = new_dishes
	return populations

if __name__=="__main__":
    main()
