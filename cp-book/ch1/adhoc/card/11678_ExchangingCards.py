# Problem name: 11678 Cards' Exchange
# Problem url: https://uva.onlinejudge.org/external/116/11678.pdf
# Author: Andrey Yemelyanov

import sys
import math

def main():
    while True:
        A, B = [int(token) for token in sys.stdin.readline().split()]
        if A == 0 and B == 0:
            break
        alices_cards = set([int(token) for token in sys.stdin.readline().split()])
        bettys_cards = set([int(token) for token in sys.stdin.readline().split()])
        print(exchange(alices_cards, bettys_cards))

def exchange(alices_cards, bettys_cards):
    return min(
        len([card for card in alices_cards if card not in bettys_cards]), # n of Alice's cards that Betty does not have
        len([card for card in bettys_cards if card not in alices_cards]) # n of Betty's cards that Alice does not have
    )

if __name__=="__main__":
    main()
