
# Problem name: 12148 Electricity
# Problem url: https://uva.onlinejudge.org/external/121/12148.pdf
# Author: Andrey Yemelyanov

import sys
import math
import datetime

def readline():
    return sys.stdin.readline().strip()

def main():
    while True:
        n_readings = int(readline())
        if n_readings == 0:
            break
        meter_readings = []
        for i in range(n_readings):
            reading = [int(x) for x in readline().split()]
            date = datetime.date(reading[2], reading[1], reading[0])
            consumption = reading[3]
            meter_readings.append((date, consumption))
        c = get_daily_consumption(meter_readings)
        print(len(c), sum(c))

def get_daily_consumption(meter_readings):
    c = []
    for i in range(len(meter_readings)):
        if i > 0:
            current_date = meter_readings[i][0]
            current_consumption = meter_readings[i][1]
            prev_date = meter_readings[i - 1][0]
            prev_consumption = meter_readings[i - 1][1]
            if prev_date + datetime.timedelta(days = 1) == current_date:
                c.append(current_consumption - prev_consumption)
    return c

if __name__=="__main__":
    main()
