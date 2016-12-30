# Problem name: 10903 Rock-Paper-Scissors Tournament
# Problem url: https://uva.onlinejudge.org/external/109/10903.pdf
# Author: Andrey Yemelyanov

import sys
import math

def main():
    first_case = True
    while True:
        line = sys.stdin.readline().strip().split()
        if line[0] == "0":
            break
        if not first_case:
            print()
        else:
            first_case = False
        n, k = [int(x) for x in line]
        games = []
        n_games = int(k * n * ((n - 1) / 2))
        for i in range(n_games):
            line = sys.stdin.readline().strip().split()
            games.append((int(line[0]), line[1], int(line[2]), line[3]))
        summary = summarize(games, n)
        for player in range(1, n + 1):
            if player in summary:
                print("{:.3f}".format(summary[player]))
            else:
                print("-")

TIE = None
def summarize(games, n_players):
    wins = dict([(player, 0) for player in range(1, n_players + 1)])
    losses = dict([(player, 0) for player in range(1, n_players + 1)])
    for game in games:
        outcome = get_game_outcome(game)
        if outcome != TIE:
            winner = outcome[0]
            loser = outcome[1]
            wins[winner] += 1
            losses[loser] += 1
    summary = {}
    for player in range(1, n_players + 1):
        total_games = wins[player] + losses[player]
        if total_games != 0:
            summary[player] = wins[player] / total_games
    return summary

def get_game_outcome(game):
    player1 = game[0]
    player1_move = game[1]
    player2 = game[2]
    player2_move = game[3]

    if player1_move == player2_move:
        return TIE

    if player1_move == "paper":
        if player2_move == "rock":
            return (player1, player2)
        if player2_move == "scissors":
            return (player2, player1)

    if player1_move == "rock":
        if player2_move == "paper":
            return (player2, player1)
        if player2_move == "scissors":
            return (player1, player2)

    if player1_move == "scissors":
        if player2_move == "rock":
            return (player2, player1)
        if player2_move == "paper":
            return (player1, player2)

if __name__=="__main__":
    main()
