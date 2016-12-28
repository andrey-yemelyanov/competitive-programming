# Problem name: 555 Bridge Hands
# Problem url: https://uva.onlinejudge.org/external/5/555.pdf
# Author: Andrey Yemelyanov

import sys
import math

def main():
    while True:
        dealer = sys.stdin.readline().strip()
        if dealer == "#":
            break
        deck_str = sys.stdin.readline().strip() + sys.stdin.readline().strip()
        deck = [code(deck_str[i] + deck_str[i + 1]) for i in range(0, len(deck_str), 2)]
        players = deal(deck, dealer)
        print("S:", " ".join([card(code) for code in players["S"]]))
        print("W:", " ".join([card(code) for code in players["W"]]))
        print("N:", " ".join([card(code) for code in players["N"]]))
        print("E:", " ".join([card(code) for code in players["E"]]))

def deal(deck, dealer):
    players = {"S" : [], "W": [], "N": [], "E" : []}
    current_player = player_to_the_left_of(dealer)
    for card in deck:
        players[current_player].append(card)
        current_player = player_to_the_left_of(current_player)
    for k in players.keys():
        players[k].sort()
    return players

def player_to_the_left_of(player):
    if player == "N":
        return "E"
    elif player == "E":
        return "S"
    elif player == "S":
        return "W"
    return "N"

rank_map = dict([(t[1], t[0]) for t in enumerate(["2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"])])
suit_map = {"C" : 0, "D" : 1, "S" : 2, "H" : 3}
def code(card):
    return suit_map[card[0]] * len(rank_map) + rank_map[card[-1]]

def card(code):
    suit_value = code // len(rank_map)
    suit = None
    for k,v in suit_map.items():
        if v == suit_value:
            suit = k
    rank_value = code % len(rank_map)
    rank = None
    for k,v in rank_map.items():
        if v == rank_value:
            rank = k
    return suit + rank

if __name__=="__main__":
    main()
