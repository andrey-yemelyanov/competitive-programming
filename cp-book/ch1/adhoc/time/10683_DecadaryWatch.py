# Problem name: 10683 The decadary watch
# Problem url: https://uva.onlinejudge.org/external/106/10683.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	for line in sys.stdin:
		traditional_time = line.strip()
		print("{0}{1:02d}{2:02d}{3:02d}".format(*to_decimal_time(to_decimal_milliseconds(to_traditional_milliseconds(traditional_time)))))

def to_traditional_milliseconds(traditional_time):
	HH = int(traditional_time[0:2])
	MM = int(traditional_time[2:4])
	SS = int(traditional_time[4:6])
	CC = int(traditional_time[6:])
	return HH * 60 * 60 * 100 + MM * 60 * 100 + SS * 100 + CC

def to_decimal_milliseconds(traditional_milliseconds):
	ms_in_a_day_traditional = 24 * 60 * 60 * 100
	ms_in_a_day_decimal = 10 * 100 * 100 * 100
	return (traditional_milliseconds * ms_in_a_day_decimal) // ms_in_a_day_traditional

def to_decimal_time(decimal_milliseconds):
	milliseconds_in_one_hour = 100 * 100 * 100
	H = decimal_milliseconds // milliseconds_in_one_hour
	decimal_milliseconds = decimal_milliseconds % milliseconds_in_one_hour
	milliseconds_in_one_minute = 100 * 100
	MM = decimal_milliseconds // milliseconds_in_one_minute
	decimal_milliseconds = decimal_milliseconds % milliseconds_in_one_minute
	milliseconds_in_one_second = 100
	SS = decimal_milliseconds // milliseconds_in_one_second
	CC = decimal_milliseconds % milliseconds_in_one_second
	return (H, MM, SS, CC)

if __name__=="__main__":
    main()
