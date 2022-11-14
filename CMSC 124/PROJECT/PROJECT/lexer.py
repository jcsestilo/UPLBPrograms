from tokens import *
from collections import namedtuple

tokenDetails = namedtuple("Tokens", ["name", "value"])

def lexer(input: str) -> tokenDetails:
    pos = 0
    while pos < len(input):
        for token_id in Token:
           if 