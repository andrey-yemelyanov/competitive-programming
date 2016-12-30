# Problem name: 379 Hi-Q
# Problem url: https://uva.onlinejudge.org/external/3/379.pdf
# Author: Andrey Yemelyanov

import sys
import math

def main():
    sys.stdin.readline() # consume line with the number of test cases
    game_configs = []
    for line in sys.stdin:
        game_configs.extend([int(x) for x in line.split()])
    games = []
    game = set()
    for peg in game_configs:
        if peg != 0:
            game.add(peg)
        else:
            games.append(game)
            game = set()
    print("HI Q OUTPUT")
    for game in games:
        print(play(game))
    print("END OF OUTPUT")

def play(game):
    while moves_are_available(game):
        move = pick_best_move(game)
        make_a_move(move, game)
    return sum(game)

valid_moves = {
    1:  [(2,3), (4,9)],
    2:  [(5,10)],
    3:  [(2,1), (6,11)],
    4:  [(5,6), (9,16)],
    5:  [(10,17)],
    6:  [(5,4), (11,18)],
    7:  [(8,9), (14,21)],
    8:  [(9,10), (15,22)],
    9:  [(4,1),(8,7),(10,11),(16,23)],
    10: [(5,2),(9,8),(11,12),(17,24)],
    11: [(6,3),(10,9),(12,13),(18,25)],
    12: [(11,10),(19,26)],
    13: [(12,11),(20,27)],
    14: [(15,16)],
    15: [(16,17)],
    16: [(9,4),(15,14),(17,18),(23,28)],
    17: [(10,5),(16,15),(18,19),(24,29)],
    18: [(11,6),(17,16),(19,20),(25,30)],
    19: [(18,17)],
    20: [(19,18)],
    21: [(14,7),(22,23)],
    22: [(15,8),(23,24)],
    23: [(16,9),(22,21),(24,25),(28,31)],
    24: [(17,10),(23,22),(25,26),(29,32)],
    25: [(18,11),(24,23),(26,27),(30,33)],
    26: [(19,12),(25,24)],
    27: [(20,13),(26,25)],
    28: [(23,16),(29,30)],
    29: [(24,17)],
    30: [(25,18),(29,28)],
    31: [(28,23),(32,33)],
    32: [(29,24)],
    33: [(30,25),(32,31)]
}

def moves_are_available(game):
    for peg in game:
        for move in valid_moves[peg]:
            intermediate_hole = move[0]
            target_hole = move[1]
            if intermediate_hole in game and target_hole not in game: # intermediate hole has a peg and target hole is empty
                return True
    return False

def pick_best_move(game):
    # find all valid moves
    moves = []
    for peg in game:
        for move in valid_moves[peg]:
            intermediate_hole = move[0]
            target_hole = move[1]
            if intermediate_hole in game and target_hole not in game:
                moves.append((peg, intermediate_hole, target_hole))
    # sort moves first by target hole, then by source hole
    moves = sorted(sorted(moves, key = lambda move : move[0], reverse = True), key = lambda move : move[2], reverse = True)
    return moves[0]

def make_a_move(move, game):
    source_hole = move[0]
    intermediate_hole = move[1]
    target_hole = move[2]
    game.remove(source_hole)
    game.remove(intermediate_hole)
    game.add(target_hole)

if __name__=="__main__":
    main()
