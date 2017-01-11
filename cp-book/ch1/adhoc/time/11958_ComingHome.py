# Problem name: 11958 Coming Home
# Problem url: https://uva.onlinejudge.org/external/119/11958.pdf
# Author: Andrey Yemelyanov

import sys
import math
import datetime

def readline():
	return sys.stdin.readline().strip()

def main():
	n_tests = int(readline())
	for i in range(n_tests):
		line = readline().split()
		n_buses = int(line[0])
		current_hour, current_minute = [int(x) for x in line[1].split(":")]
		current_time = datetime.datetime(1, 1, 1, hour = current_hour, minute = current_minute)
		# print(current_time)
		bus_info = []
		for j in range(n_buses):
			line = readline().split()
			bus_travel_time = int(line[1])
			hour, minute = [int(x) for x in line[0].split(":")]
			if datetime.time(hour = current_hour, minute = current_minute) <= datetime.time(hour = hour, minute = minute):
				bus_arrival_time = datetime.datetime(1, 1, 1, hour = hour, minute = minute)
			else:
				bus_arrival_time = datetime.datetime(1, 1, 2, hour = hour, minute = minute)
			bus_info.append((bus_arrival_time, bus_travel_time))
		# print(bus_info)
		print("Case {0}: {1}".format(i + 1, get_min_travel_time(current_time, bus_info)))

def get_min_travel_time(current_time, bus_info):
	min_travel_time = 10 * 24 * 60
	for bus in bus_info:
		home_arrival_time = bus[0] + datetime.timedelta(minutes = bus[1])
		total_travel_time = int((home_arrival_time - current_time).total_seconds()) // 60
		# print(current_time, home_arrival_time, home_arrival_time - current_time)
		min_travel_time = min(min_travel_time, total_travel_time)
	# print()
	return min_travel_time

if __name__=="__main__":
    main()
