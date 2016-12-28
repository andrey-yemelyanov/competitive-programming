
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
        winner = play(player1, player2)
        if winner == player1:
            print("1", end="")
        else:
            print("2", end="")
        print("{0:>3}".format(len(winner)))

def play(player1, player2):
    heap = []
    heap.append(player2.pop()) # player2 starts the game
    current_player = player1
    while True:
        if len(current_player) == 0:
            return switch_player(player1, player2, current_player) # return winner
        top_card = heap[-1]
        if rank(top_card) <= 10: # non-face card
            heap.append(current_player.pop())
            current_player = switch_player(player1, player2, current_player)
        else: # must cover the 'face' card - pay penalty
            penalty = rank(top_card) - 10
            for i in range(penalty):
                if len(current_player) == 0:
                    return switch_player(player1, player2, current_player) # return winner
                heap.append(current_player.pop())
                top_card = heap[-1]
                if rank(top_card) > 10: # another face card, the other player must now pay the penalty
                    current_player = switch_player(player1, player2, current_player)
                    break
            else: # the other player takes the entire heap and starts the game again
                other_player = switch_player(player1, player2, current_player)
                for card in heap:
                    other_player.insert(0, card)
                heap.clear()
                heap.append(other_player.pop())

def switch_player(player1, player2, current_player):
    if current_player == player1:
        return player2
    else:
        return player1

rank_map = dict([(t[1], t[0]) for t in enumerate(["2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"], start = 2)])
def rank(card):
    return rank_map[card[-1:]]

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
    player1 = [deck[i] for i in range(1, len(deck), 2)] # dealer
    player2 = [deck[i] for i in range(0, len(deck), 2)] # non-dealer
    return (player1, player2)

if __name__=="__main__":
    main()
