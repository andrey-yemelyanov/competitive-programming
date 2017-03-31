# Problem name: 417 Word Index
# Problem url: https://uva.onlinejudge.org/external/4/417.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	encoding = generate_word_encodings(5)
	for word in sys.stdin:
		word = word.strip()
		if word not in encoding:
			print(0)
		else:
			print(encoding[word])

def generate_word_encodings(max_word_len):
	code = 1
	encoding = dict()
	for word_len in range(max_word_len):
		for word in generate_words(word_len + 1):
			encoding[word] = code
			code += 1
	return encoding

alphabet = "abcdefghijklmnopqrstuvwxyz"
def generate_words_rec(word_len, word, words):
	if word_len == 0:
		words.append(word)
		return
	for c in alphabet:
		if len(word) == 0 or c > word[-1]:
			generate_words_rec(word_len - 1, word + c, words)

def generate_words(word_len):
	words = []
	generate_words_rec(word_len, "", words)
	return words

if __name__=="__main__":
    main()
