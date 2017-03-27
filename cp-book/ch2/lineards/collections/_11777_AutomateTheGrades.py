# Problem name: 11777 Automate the Grades
# Problem url: https://uva.onlinejudge.org/external/117/11777.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	n_tests = int(readline())
	for test in range(n_tests):
		print("Case {}: {}".format((test + 1), get_final_grade(*[int(x) for x in readline().split()])))

def get_final_grade(term1, term2, final, attendance, class_test1, class_test2, class_test3):
	class_test_grade = get_class_test_grade(class_test1, class_test2, class_test3)
	total_grade = term1 + term2 + final + attendance + class_test_grade
	return get_letter_grade(total_grade)

def get_class_test_grade(class_test1, class_test2, class_test3):
	sorted_grades = [class_test1, class_test2, class_test3]
	sorted_grades.sort()
	return (sorted_grades[-1] + sorted_grades[-2]) / 2

def get_letter_grade(total_grade):
	if total_grade >= 90:
		return "A"
	elif total_grade >= 80 and total_grade < 90:
		return "B"
	elif total_grade >= 70 and total_grade < 80:
		return "C"
	elif total_grade >= 60 and total_grade < 70:
		return "D"
	return "F"

if __name__=="__main__":
    main()
