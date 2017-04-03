# Problem name: 11348 Exhibition
# Problem url: https://uva.onlinejudge.org/external/113/11348.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	K = int(readline())
	for t in range(K):
		N = int(readline())
		person_to_stamps = dict()
		for person in range(N):
			person_to_stamps[person] = set([int(x) for x in readline().split()][1:])
		print("Case {}: {}".format(t + 1, " ".join("{:.6f}%".format(x * 100) for x in distribute_income(person_to_stamps))))

def distribute_income(person_to_stamps):
	unique_stamps = get_unique_stamps(map_stamps_to_person(person_to_stamps))
	#print(unique_stamps)
	distr = [0 for x in range(len(person_to_stamps))]
	for person in range(len(person_to_stamps)):
		for stamp in person_to_stamps[person]:
			if stamp in unique_stamps:
				distr[person] += 1
	if len(unique_stamps) > 0:
		return [x / len(unique_stamps) for x in distr]
	return distr

def map_stamps_to_person(person_to_stamps):
	stamps_to_person = dict()
	for person in person_to_stamps.keys():
		for stamp in person_to_stamps[person]:
			if stamp not in stamps_to_person:
				stamps_to_person[stamp] = []
			stamps_to_person[stamp].append(person)
	#print(stamps_to_person)
	return stamps_to_person;

def get_unique_stamps(stamps_to_person):
	return {stamp for stamp, persons in stamps_to_person.items() if len(persons) == 1}

if __name__=="__main__":
    main()
