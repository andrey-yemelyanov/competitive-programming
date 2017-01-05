# Problem name: 448 OOPS!
# Problem url: https://uva.onlinejudge.org/external/4/448.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	machine_code = []
	for line in sys.stdin:
		machine_code.extend([x for x in line.strip()])
	for line in translate_to_assembly(machine_code):
		print(line)

def translate_to_assembly(machine_code):
	return [translate_instruction(instruction) for instruction in instructions(machine_code)]

OPERAND_LEN = 4
def instructions(machine_code):
	instruction_pointer = 0
	while True:
		if instruction_pointer >= len(machine_code):
			break
		opcode = machine_code[instruction_pointer]
		n_operands = instruction_set[opcode][1]
		start = instruction_pointer
		instruction_pointer += n_operands * OPERAND_LEN + 1
		yield machine_code[start:instruction_pointer]

def translate_instruction(instruction):
	opcode = instruction[0]
	symbolic_name = instruction_set[opcode][0]
	return "{0} {1}".format(symbolic_name, ",".join(
												[
													translate_operand(operand)
													for operand in read_operands(instruction)
												]))

def translate_operand(operand):
	# extract mode from the leftmost 2 bits
	mode = operand >> 14
	prefix = mode_prefix[mode]
	# extract value from the rightmost 14 bits
	value = operand & ~(0b11 << 14)
	return "{0}{1}".format(prefix,value)

# returns a list of operands as integers
def read_operands(instruction):
	# print(instruction)
	opcode = instruction[0]
	n_operands = instruction_set[opcode][1]
	pointer = 1
	operands = []
	for i in range(n_operands):
		operands.append(int("".join(instruction[pointer:pointer + OPERAND_LEN]), 16))
		pointer += OPERAND_LEN
	# print("Operands:", operands)
	return operands

# instruction set maps opcode to a tuple (symbolic name, number of operands)
instruction_set = {
	"0": ("ADD", 2),
	"1": ("SUB", 2),
	"2": ("MUL", 2),
	"3": ("DIV", 2),
	"4": ("MOV", 2),
	"5": ("BREQ", 1),
	"6": ("BRLE", 1),
	"7": ("BRLS", 1),
	"8": ("BRGE", 1),
	"9": ("BRGR", 1),
	"A": ("BRNE", 1),
	"B": ("BR", 1),
	"C": ("AND", 3),
	"D": ("OR", 3),
	"E": ("XOR", 3),
	"F": ("NOT", 1)
}

mode_prefix = {
	0: "R",
	1: "$",
	2: "PC+",
	3: ""
}

if __name__=="__main__":
    main()
