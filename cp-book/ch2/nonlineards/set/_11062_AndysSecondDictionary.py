# Problem name: 11062 Andy's Second Dictionary
# Problem url: https://uva.onlinejudge.org/external/110/11062.pdf
# Author: Andrey Yemelyanov

import sys
import math
import re

def readline():
	return sys.stdin.readline().strip()

def read_lines():
	lines = []
	for line in sys.stdin:
		lines.append(line.strip())
	return lines

def get_words(text):
	return re.compile(r"[a-zA-Z-]+").findall(text)

def normalize_text(lines):
	lines = [s for s in lines if len(s) > 0]
	#print(lines)
	text = ""
	i = 0
	while(i < len(lines)):
		line = lines[i]
		if line[-1] != "-":
			text += line + " "
		else:
			text += line[0:len(line) - 1]
		i += 1
	#print(text)
	return text

def build_dictionary():
	return sorted(set([s.lower() for s in get_words(normalize_text(read_lines()))]))

def main():
	print("\n".join(build_dictionary()))

if __name__=="__main__":
    main()
