
# Problem name: 860 Entropy Text Analyzer
# Problem url: https://uva.onlinejudge.org/external/8/860.pdf
# Author: Andrey Yemelyanov

import sys
import math
import re

def readline():
    return sys.stdin.readline().strip()

def main():
    text = ""
    for line in sys.stdin:
        line = line.strip()
        if line == "****END_OF_INPUT****":
            break
        if line == "****END_OF_TEXT****":
            #print(get_words(text))
            word_count = get_word_count(text)
            entropy = get_entropy(text, word_count)
            relative_entropy = get_relative_entropy(entropy, get_max_entropy(text, word_count))
            print("{} {:.1f} {}".format(word_count, entropy, relative_entropy))
            text = ""
        else:
            text += line + "\n"

def get_words(text):
    return [word.lower() for word in re.compile(r"[^,.:;!?\"()\s]+").findall(text)]

def get_word_count(text):
    return len(get_words(text))

def get_word_frequency(text):
    freq = dict()
    for word in get_words(text):
        if word not in freq:
            freq[word] = 0
        freq[word] += 1
    return freq

def get_entropy(text, word_count):
    freq = get_word_frequency(text)
    entropy = sum(p * (math.log(word_count, 10) - math.log(p, 10)) for p in freq.values()) / word_count
    return entropy

def get_max_entropy(text, word_count):
    return math.log(word_count, 10)

def get_relative_entropy(entropy, max_entropy):
    return round((entropy / max_entropy) * 100)

if __name__=="__main__":
    main()
