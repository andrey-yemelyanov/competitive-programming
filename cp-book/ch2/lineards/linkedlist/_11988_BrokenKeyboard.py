# Problem name: 11988 Broken Keyboard (a.k.a. Beiju Text)
# Problem url: https://uva.onlinejudge.org/external/119/11988.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

class LinkedList:
	def __init__(self):
		self.head = None
		self.cursor = None

	def move_cursor_to_start(self):
		self.cursor = None

	def move_cursor_to_end(self):
		self.cursor = self.head
		while self.cursor != None:
			if self.cursor.next == None:
				break
			self.cursor = self.cursor.next

	def insert_after_cursor(self, char):
		new_node = Node(char)
		if self.cursor == None:
			new_node.next = self.head
			self.head = new_node
		else:
			new_node.next = self.cursor.next
			self.cursor.next = new_node
		self.cursor = new_node

	def print_list_rec(self, node):
		if node == None:
			return
		print(node, end = "")
		self.print_list_rec(node.next)
	def print_list(self):
		self.print_list_rec(self.head)

class Node:
	def __init__(self, char, next=None):
		self.char = char
		self.next = next
	def __str__(self):
		return str(self.char)

def main():
	for line in sys.stdin:
		line = line.strip()
		linked_list = LinkedList()
		for i in range(len(line)):
			char = line[i]
			if char != "[" and char != "]":
				linked_list.insert_after_cursor(char)
			elif char == "[":
				linked_list.move_cursor_to_start()

		linked_list.print_list()
		print()

if __name__=="__main__":
    main()
