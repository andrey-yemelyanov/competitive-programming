
# Problem name: 11223 O: dah dah dah!
# Problem url: https://uva.onlinejudge.org/external/112/11223.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
    return sys.stdin.readline().strip()

morse_code_table = {
    '.-' : 'A',
    '-...' : 'B',
    '-.-.' : 'C',
    '-..' : 'D',
    '.' : 'E',
    '..-.' : 'F',
    '--.' : 'G',
    '....' : 'H',
    '..' : 'I',
    '.---' : 'J',
    '-.-' : 'K',
    '.-..' : 'L',
    '--' : 'M',
    '-.' : 'N',
    '---' : 'O',
    '.--.' : 'P',
    '--.-' : 'Q',
    '.-.' : 'R',
    '...' : 'S',
    '-' : 'T',
    '..-' : 'U',
    '...-' : 'V',
    '.--' : 'W',
    '-..-' : 'X',
    '-.--' : 'Y',
    '--..' : 'Z',
    '-----' : '0',
    '.----' : '1',
    '..---' : '2',
    '...--' : '3',
    '....-' : '4',
    '.....' : '5',
    '-....' : '6',
    '--...' : '7',
    '---..' : '8',
    '----.' : '9',
    '.-.-.-' : '.',
    '--..--' : ',',
    '..--..' : '?',
    '.----.' : '\'',
    '-.-.--' : '!',
    '-..-.' : '/',
    '-.--.' : '(',
    '-.--.-' : ')',
    '.-...' : '&',
    '---...' : ':',
    '-.-.-.' : ';',
    '-...-' : '=',
    '.-.-.' : '+',
    '-....-' : '-',
    '..--.-' : '_',
    '.-..-.' : '"',
    '.--.-.' : '@'
}

def main():
    n_tests = int(readline())
    for i in range(n_tests):
        morse_code = readline().split(' ')
        print("Message #{0}".format(i + 1))
        print(" ".join(decode(morse_code)))
        if i < n_tests - 1:
            print()

def decode(morse_code):
    return [decode_word(word) for word in words(morse_code)]

def decode_word(word):
    return "".join([morse_code_table[x] for x in word])

def words(morse_code):
    start, end = 0, 0
    while True:
        if start >= len(morse_code):
            break
        if end == len(morse_code) or morse_code[end] == '':
            word = morse_code[start:end]
            end += 1
            start = end
            yield word
        else:
            end += 1

if __name__=="__main__":
    main()
