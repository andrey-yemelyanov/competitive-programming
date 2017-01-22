
# Problem name: 10188 Automated Judge Script
# Problem url: https://uva.onlinejudge.org/external/101/10188.pdf
# Author: Andrey Yemelyanov

import sys
import math
import re

def readline():
    return sys.stdin.readline()

WRONG_ANSWER = "Wrong Answer"
PRES_ERR = "Presentation Error"
ACC = "Accepted"
def main():
    run = 1
    while True:
        n_solutions = int(readline())
        if n_solutions == 0:
            break
        solutions = []
        for i in range(n_solutions):
            solutions.append(readline())
        n_inputs = int(readline())
        inputs = []
        for i in range(n_inputs):
            inputs.append(readline())
        print("Run #{0}: {1}".format(run, get_verdict(solutions, inputs)))
        run += 1

def get_verdict(solutions, inputs):
    if solutions == inputs:
        return ACC
    solution_digits = "".join([extract_digits(x) for x in solutions])
    input_digits = "".join([extract_digits(x) for x in inputs])
    if solution_digits == input_digits:
        return PRES_ERR
    return WRONG_ANSWER

def extract_digits(input):
    return "".join(re.findall(r"\d+", input))

if __name__=="__main__":
    main()
