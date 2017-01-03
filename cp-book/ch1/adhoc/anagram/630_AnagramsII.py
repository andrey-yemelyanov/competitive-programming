# Problem name: 630 Anagrams (II)
# Problem url: https://uva.onlinejudge.org/external/6/630.pdf
# Author: Andrey Yemelyanov

import sys
import math

def main():
    n_tests = int(sys.stdin.readline().strip())
    for i in range(n_tests):
        sys.stdin.readline()
        n_words = int(sys.stdin.readline().strip())
        anagrams_dict = {}
        for j in range(n_words):
            word = sys.stdin.readline().strip()
            sorted_word = "".join(sorted(word))
            if sorted_word not in anagrams_dict:
                anagrams_dict[sorted_word] = []
            anagrams_dict[sorted_word].append(word)
        # print(anagrams_dict)
        while True:
            test_word = sys.stdin.readline().strip()
            sorted_test_word = "".join(sorted(test_word))
            if test_word == "END":
                break
            print("Anagrams for: {0}".format(test_word))
            if sorted_test_word not in anagrams_dict:
                print("No anagrams for: {0}".format(test_word))
            else:
                anagrams = anagrams_dict[sorted_test_word]
                for i, anagram in enumerate(anagrams):
                    print("{0:>3}) {1}".format(i + 1, anagram))
        if i < n_tests - 1:
            print()

if __name__=="__main__":
    main()
