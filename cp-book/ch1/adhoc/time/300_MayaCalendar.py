# Problem name: 300 Maya Calendar
# Problem url: https://uva.onlinejudge.org/external/3/300.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

haab_month_offset = {
	"pop": 0,
	"no": 20,
	"zip": 40,
	"zotz": 60,
	"tzec": 80,
	"xul": 100,
	"yoxkin": 120,
	"mol": 140,
	"chen": 160,
	"yax": 180,
	"zac": 200,
	"ceh": 220,
	"mac": 240,
	"kankin": 260,
	"muan": 280,
	"pax": 300,
	"koyab": 320,
	"cumhu": 340,
	"uayet": 360
}
def main():
	n_tests = int(readline())
	print(n_tests)
	for i in range(n_tests):
		haab_date = readline().split()
		haab_date[2] = int(haab_date[2])
		haab_date[0] = int(haab_date[0].replace('.', ''))
		print("{0} {1} {2}".format(*to_tzolkin(haab_to_days(*haab_date))))

def haab_to_days(day, month, year):
	haab_year_len = 365
	return year * haab_year_len + haab_month_offset[month] + day + 1

def to_tzolkin(n_days):
	tzolkin_days = ['imix', 'ik', 'akbal', 'kan', 'chicchan', 'cimi', 'manik', 'lamat', 'muluk', 'ok', 'chuen', 'eb', 'ben', 'ix', 'mem', 'cib', 'caban', 'eznab', 'canac', 'ahau']
	n_periods = 13
	period_len = 20
	year_len = n_periods * period_len
	tzolkin_year = n_days // year_len
	day_of_the_year = n_days % year_len
	tzolkin_number = day_of_the_year % n_periods
	if tzolkin_number == 0:
		tzolkin_number = n_periods
	tzolkin_name_of_the_day = tzolkin_days[(day_of_the_year % period_len) - 1]
	if day_of_the_year == 0:
		return (n_periods, tzolkin_days[-1], tzolkin_year - 1)
	else:
		return (tzolkin_number, tzolkin_name_of_the_day, tzolkin_year)

if __name__=="__main__":
    main()
