# Problem name: 11356 Dates
# Problem url: https://uva.onlinejudge.org/external/113/11356.pdf
# Author: Andrey Yemelyanov

import sys
import math
import datetime
import calendar

def readline():
	return sys.stdin.readline().strip()

def main():
	months =  {
		"January": 1,
		"February": 2,
		"March": 3,
		"April": 4,
		"May": 5,
		"June": 6,
		"July": 7,
		"August": 8,
		"September": 9,
		"October": 10,
		"November": 11,
		"December": 12
	}
	n_tests = int(readline())
	for i in range(n_tests):
		year, month, day = [x for x in readline().split("-")]
		K = int(readline())
		date = datetime.date(int(year), months[month], int(day))
		new_date = date + datetime.timedelta(K)
		print("Case {0}: {1:04d}-{2}-{3:02d}".format(i + 1, new_date.year, calendar.month_name[new_date.month], new_date.day))

if __name__=="__main__":
    main()
