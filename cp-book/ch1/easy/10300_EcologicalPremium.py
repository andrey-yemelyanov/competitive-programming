
# Problem name: 10300 Ecological Premium
# Problem url: https://uva.onlinejudge.org/external/103/10300.pdf
# Author: Andrey Yemelyanov

import sys
import math

def main():
    n_tests = int(sys.stdin.readline())
    for i in range(n_tests):
        n_farmers = int(sys.stdin.readline())
        total_premium = 0
        for j in range(n_farmers):
            farm_size, n_animals, env_factor = [int(x) for x in sys.stdin.readline().strip().split()]
            premium = ((farm_size / n_animals) * env_factor) * n_animals
            total_premium = total_premium + premium
        print(int(total_premium))

if __name__=="__main__":
    main()
