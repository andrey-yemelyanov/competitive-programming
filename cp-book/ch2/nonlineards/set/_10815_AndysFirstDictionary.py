# Problem name: 10815 Andy's First Dictionary
# Problem url: https://uva.onlinejudge.org/external/108/10815.pdf
# Author: Andrey Yemelyanov

import sys
import math
import re

def readline():
	return sys.stdin.readline().strip()

def read_text():
	text = ""
	for line in sys.stdin:
		text += line
	return text

word_pattern = re.compile(r"[a-zA-Z]+")
def get_words(text):
	return word_pattern.findall(text)

def main():
	sorted_set = sorted(set([s.lower() for s in get_words(read_text())]))
	print("\n".join(sorted_set))

if __name__=="__main__":
    main()
