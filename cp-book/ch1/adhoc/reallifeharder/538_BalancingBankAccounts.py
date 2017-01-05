# Problem name: 538 Balancing Bank Accounts
# Problem url: https://uva.onlinejudge.org/external/5/538.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	case = 1
	while True:
		n, t = [int(x) for x in readline().split()]
		if n == 0 and t == 0:
			break
		expense_report = {}
		for i in range(n):
			expense_report[readline()] = 0
		for i in range(t):
			transaction = readline().split()
			expense_report[transaction[0]] -= int(transaction[2])
			expense_report[transaction[1]] += int(transaction[2])
		transactions = balance_expenses(expense_report)
		print("Case #{0}".format(case))
		case += 1
		for t in transactions:
			print("{} {} {}".format(t[0], t[1], t[2]))
		print()

def balance_expenses(expense_report):
	balancing_transactions = []
	while not is_balanced(expense_report):
		balancing_transactions.append(balance(expense_report))
	return balancing_transactions

def balance(expense_report):
	pos = find_by_saldo(expense_report, lambda x: x > 0)
	neg = find_by_saldo(expense_report, lambda x: x < 0)
	from_person = pos[0]
	from_person_saldo = pos[1]
	to_person = neg[0]
	to_person_saldo = neg[1]
	balancing_amount = min(from_person_saldo, abs(to_person_saldo))
	expense_report[from_person] -= balancing_amount
	expense_report[to_person] += balancing_amount
	return (from_person, to_person, balancing_amount)

def find_by_saldo(expense_report, predicate):
	for person, saldo in expense_report.items():
		if predicate(saldo):
			return (person, saldo)

def is_balanced(expense_report):
	return all(saldo == 0 for saldo in expense_report.values())

if __name__=="__main__":
    main()
