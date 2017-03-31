# Problem name: 10282 Babelfish
# Problem url: https://uva.onlinejudge.org/external/102/10282.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	dictionary = dict()
	for line in sys.stdin:
		line = line.strip()
		if line == "":
			break
		line = line.split()
		dictionary[line[1]] = line[0]
	for line in sys.stdin:
		word = line.strip()
		if word not in dictionary:
			print("eh")
		else:
			print(dictionary[word])

if __name__=="__main__":
    main()
