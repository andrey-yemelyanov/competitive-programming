# Problem name: 101 The Blocks Problem
# Problem url: https://uva.onlinejudge.org/external/1/101.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	n_blocks = int(readline())
	blocks = [[x] for x in range(n_blocks)]
	while True:
		command = readline().split()
		if command[0] == "quit":
			break
		a = int(command[1])
		b = int(command[3])
		if command[0] == "move":
			if command[2] == "onto":
				move_a_onto_b(a, b, blocks)
			else:
				move_a_over_b(a, b, blocks)
		elif command[0] == "pile":
			if command[2] == "onto":
				pile_a_onto_b(a, b, blocks)
			else:
				pile_a_over_b(a, b, blocks)
	for block_id, stack in enumerate(blocks):
		print("{0}: {1}".format(block_id, " ".join(str(x) for x in stack)).strip())

"""
Puts the pile of blocks consisting of block a, and any blocks
that are stacked above block a, onto the top of the stack containing block b. The blocks stacked
above block a retain their original order when moved
"""
def pile_a_over_b(a, b, blocks):
	if a == b:
		return

	a_coord = find_block(a, blocks)
	b_coord = find_block(b, blocks)

	stack_a = a_coord[0]
	stack_b = b_coord[0]
	pos_a = a_coord[1]
	pos_b = b_coord[1]

	if stack_a == stack_b: # a and b cannot be on the same stack
		return

	# remove top blocks on a, including a
	blocks_on_a = remove_blocks(blocks[stack_a], pos_a)

	# move a with blocks on top of it to b
	for block in blocks_on_a:
		blocks[stack_b].append(block)

"""
Moves the pile of blocks consisting of block a, and any blocks
that are stacked above block a, onto block b. All blocks on top of block b are moved to their
initial positions prior to the pile taking place. The blocks stacked above block a retain their order
when moved
"""
def pile_a_onto_b(a, b, blocks):
	if a == b:
		return

	a_coord = find_block(a, blocks)
	b_coord = find_block(b, blocks)

	stack_a = a_coord[0]
	stack_b = b_coord[0]
	pos_a = a_coord[1]
	pos_b = b_coord[1]

	if stack_a == stack_b: # a and b cannot be on the same stack
		return

	# remove top blocks on a, including a
	blocks_on_a = remove_blocks(blocks[stack_a], pos_a)
	# remove top blocks on b
	blocks_on_b = remove_blocks(blocks[stack_b], pos_b + 1)

	# move top blocks on b to initial positions
	for block in blocks_on_b:
		blocks[block].append(block)

	# move a with blocks on top of it to b
	for block in blocks_on_a:
		blocks[stack_b].append(block)

"""
Puts block a onto the top of the stack containing block b, after
returning any blocks that are stacked on top of block a to their initial positions
"""
def move_a_over_b(a, b, blocks):
	if a == b:
		return

	a_coord = find_block(a, blocks)
	b_coord = find_block(b, blocks)

	stack_a = a_coord[0]
	stack_b = b_coord[0]
	pos_a = a_coord[1]
	pos_b = b_coord[1]

	if stack_a == stack_b: # a and b cannot be on the same stack
		return

	# remove top blocks from a
	blocks_on_a = remove_blocks(blocks[stack_a], pos_a + 1)

	# remove a from its stack
	del blocks[stack_a][pos_a]

	# move top blocks on a to initial positions
	for block in blocks_on_a:
		blocks[block].append(block)

	# move a on top of b stack
	blocks[stack_b].append(a)

"""
Puts block a onto block b after returning any blocks that are
stacked on top of blocks a and b to their initial positions
"""
def move_a_onto_b(a, b, blocks):
	if a == b:
		return

	a_coord = find_block(a, blocks)
	b_coord = find_block(b, blocks)

	stack_a = a_coord[0]
	stack_b = b_coord[0]
	pos_a = a_coord[1]
	pos_b = b_coord[1]

	if stack_a == stack_b: # a and b cannot be on the same stack
		return

	# remove top blocks from a and b
	blocks_on_a = remove_blocks(blocks[stack_a], pos_a + 1)
	blocks_on_b = remove_blocks(blocks[stack_b], pos_b + 1)

	# remove a from its stack
	del blocks[stack_a][pos_a]

	# move top blocks on a and b to initial positions
	for block in blocks_on_a:
		blocks[block].append(block)
	for block in blocks_on_b:
		blocks[block].append(block)

	# move a on top of b
	blocks[stack_b].append(a)

def remove_blocks(stack, from_pos):
	blocks_on_top = stack[from_pos:]
	del stack[from_pos:]
	return blocks_on_top

def find_block(block, blocks):
	for row in range(len(blocks)):
		for col in range(len(blocks[row])):
			if(blocks[row][col] == block):
				return (row, col)
	return None

if __name__=="__main__":
    main()
