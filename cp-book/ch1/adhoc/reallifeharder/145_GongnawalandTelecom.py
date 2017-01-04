# Problem name: 145 Gondwanaland Telecom
# Problem url: https://uva.onlinejudge.org/external/1/145.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	while True:
		record = readline()
		if record == "#":
			break
		charging_step, phone_num, start_hour, start_minute, end_hour, end_minute = [x for x in record.split()]
		breakdown = break_down_into_categories(int(start_hour), int(start_minute), int(end_hour), int(end_minute))
		total_charge = (breakdown[day_rate] * schedule[charging_step][day_rate]
						+ breakdown[evening_rate] * schedule[charging_step][evening_rate]
						+ breakdown[night_rate] * schedule[charging_step][night_rate])
		print("{0:>10}{1:>6}{2:>6}{3:>6}{4:>3}{5:>8.2f}".format(phone_num, breakdown[day_rate], breakdown[evening_rate], breakdown[night_rate], charging_step, total_charge))

schedule = {
	'A':[0.1,0.06,0.02],
	'B':[0.25,0.15,0.05],
	'C':[0.53,0.33,0.13],
	'D':[0.87,0.47,0.17],
	'E':[1.44,0.8,0.3]
	}

day_rate, evening_rate, night_rate = 0, 1, 2

def rate_type_for_minute_ending_at(hour, minute):
	if (hour > 8 and hour < 18) or (hour == 8 and minute > 0) or (hour == 18 and minute == 0):
		return day_rate
	if (hour > 18 and hour < 22) or (hour == 18 and minute > 0) or (hour == 22 and minute == 0):
		return evening_rate
	return night_rate

def break_down_into_categories(start_hour, start_minute, end_hour, end_minute):
	breakdown = {day_rate:0, evening_rate:0, night_rate:0}
	hour, minute = increment_by_one_minute(start_hour, start_minute)
	while True:
		rate_type = rate_type_for_minute_ending_at(hour, minute)
		breakdown[rate_type] += 1
		if hour == end_hour and minute == end_minute:
			break
		hour, minute = increment_by_one_minute(hour, minute)
	return breakdown

def increment_by_one_minute(hour, minute):
	if minute + 1 == 60:
		if hour + 1 == 24:
			return (0, 0)
		else:
			return (hour + 1, 0)
	return (hour, minute + 1)

if __name__=="__main__":
    main()
