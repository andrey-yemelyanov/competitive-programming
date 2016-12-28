
# Problem name: 162 Beggar My Neighbour
# Problem url: https://uva.onlinejudge.org/external/1/162.pdf
# Author: Andrey Yemelyanov

import sys
import math

def main():
    while True:
        deck = read_deck()
        if deck == None:
            break
        deal = deal_deck(deck)
        # print(deal)
        player1 = deal[0]
        player2 = deal[1]

def play(player1, player2):
    pass

def read_deck():
    line = sys.stdin.readline().strip()
    if line == "#":
        return None
    else:
        deck = [card for card in line.split()] # cards 1 - 13
        deck += [card for card in sys.stdin.readline().split()] # cards 14 - 26
        deck += [card for card in sys.stdin.readline().split()] # cards 27 - 39
        deck += [card for card in sys.stdin.readline().split()] # cards 40 - 52
        return deck

def deal_deck(deck):
    player1 = list(reversed([deck[i] for i in range(1, len(deck), 2)])) # dealer
    player2 = list(reversed([deck[i] for i in range(0, len(deck), 2)])) # non-dealer
    return (player1, player2)

if __name__=="__main__":
    main()
