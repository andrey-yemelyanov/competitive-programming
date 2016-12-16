# Problem name: 12250 Language Detection
# Problem url: https://uva.onlinejudge.org/external/122/12250.pdf
# Author: Andrey Yemelyanov

import sys
import math

def main():
    mapping = {
     "HELLO": "ENGLISH",
     "HOLA": "SPANISH",
     "HALLO": "GERMAN",
     "BONJOUR": "FRENCH",
     "CIAO": "ITALIAN",
     "ZDRAVSTVUJTE": "RUSSIAN"
    }
    case = 1
    for word in sys.stdin:
        word = word.strip()
        if word == "#":
            break
        if word not in mapping:
            print("Case {0}: {1}".format(case, "UNKNOWN"))
        else:
            print("Case {0}: {1}".format(case, mapping[word]))
        case = case + 1

if __name__=="__main__":
    main()
