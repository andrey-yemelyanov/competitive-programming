
# Problem name: 947 Master Mind Helper
# Problem url: https://uva.onlinejudge.org/external/9/947.pdf
# Author: Andrey Yemelyanov

import sys
import math

def main():
    n_tests = int(sys.stdin.readline().strip())
    for i in range(n_tests):
        guess, n, k = [int(x) for x in sys.stdin.readline().split()]
        # print([int(x) for x in str(guess)], n, k)
        answer = count_secret_codes([int(x) for x in str(guess)], n, k)
        print(answer)

def count_secret_codes(guess, n, k):
    return count_secret_codes_recursive(0, [], len(guess), guess, n, k)

def count_secret_codes_recursive(i, code, code_length, guess, n, k):
    if i == code_length:
        if secret_code_consistent_with_guess(code, guess, n, k):
            return 1
        return 0
    n_codes = 0
    for j in range(1, 10):
        code.append(j)
        n_codes += count_secret_codes_recursive(i + 1, code, code_length, guess, n, k)
        code.pop()
    return n_codes

def secret_code_consistent_with_guess(secret_code, guess, n, k):
    return n_digits_in_common(secret_code, guess) == (n + k) and n_digits_in_the_same_position(secret_code, guess) == n

def n_digits_in_common(secret_code, guess):
    guess_dict = {}
    for digit in guess:
        if digit not in guess_dict:
            guess_dict[digit] = 1
        else:
            guess_dict[digit] += 1
    n = 0
    for digit in secret_code:
        if digit in guess_dict:
            n += 1
            if guess_dict[digit] == 1:
                del guess_dict[digit]
            else:
                guess_dict[digit] -= 1
    return n

def n_digits_in_the_same_position(secret_code, guess):
    n = 0
    for i in range(len(secret_code)):
        if secret_code[i] == guess[i]:
            n += 1
    return n

if __name__=="__main__":
    main()
