# Problem name: 11173 Grey Codes
# Problem url: https://uva.onlinejudge.org/external/111/11173.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	n_tests = int(readline())
	for i in range(n_tests):
		n, k = [int(x) for x in readline().split()]
		print(to_decimal(generate_grey_code(n, k)))

def to_decimal(code):
	decimal = 0
	for i in range(len(code)):
		if code[i] == 1:
			decimal += pow(2, len(code) - 1 - i)
	return decimal

def generate_grey_code(n, k):
	code = []
	generate_grey_code_rec(n, k, code)
	return code

def generate_grey_code_rec(n, k, code):
	if n == 1:
		code.append(k)
		return
	if k < pow(2, n) / 2: # upper half
		code.append(0)
		generate_grey_code_rec(n - 1, k, code)
	else:
		code.append(1)
		generate_grey_code_rec(n - 1, pow(2, n) - 1 - k, code)

if __name__=="__main__":
    main()
