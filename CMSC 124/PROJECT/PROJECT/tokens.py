from enum import Enum
import re

ILLEGAL = 'ILLEGAL' # di ko alam pa para san 'to, lagay ko lang muna HAHAHA
EOF = 'EOF'

# create a class that supports equality based on name and is hashable
class NewEnum(Enum):
    # check the name of the Enum
    def __eq__(self, other):
        if isinstance(other, str): # check if instance of string
            return self.name == other
        else:
            return self.name == other.name # compare names of two Enum
    # make the class hashable
    def __hash__(self):
        return id(self.name)

# TOKENS
class Token(NewEnum):
    # identifiers
    VARIDENT = re.compile(r'^[a-zA-Z][a-zA-Z0-9_]*$')
    FUNCIDENT = re.compile(r'^[a-zA-Z][a-zA-Z0-9_]*$')
    LOOPIDENT = re.compile(r'^[a-zA-Z][a-zA-Z0-9_]*$')

    # keywords
    HAI = re.compile(r'^HAI$')
    KTHXBYE = re.compile(r'^KTHXBYE$')
    BTW = re.compile(r'^BTW$')
    OBTW = re.compile(r'^OBTW$')
    TLDR = re.compile(r'^TLDR$')
    IHASA = re.compile(r'^I HAS A $')
    ITZ = re.compile(r'^ITZ$')
    R = re.compile(r'^R$')
    SUMOF = re.compile(r'^SUM OF$')
    DIFFOF = re.compile(r'^DIFF OF$')
    PRODUKTOF = re.compile(r'^PRODUKT OF$')
    QUOSHUNTOF = re.compile(r'^QUOSHUNT OF$')
    MODOF = re.compile(r'^MOD OF$')
    BIGGROF = re.compile(r'^BIGGR OF$')
    SMALLROF = re.compile(r'^SMALLR OF$')
    BOTHOF = re.compile(r'^BOTH OF$')
    EITHEROF = re.compile(r'^EITHER OF$')
    WONOF = re.compile(r'^WON OF$')
    NOT = re.compile(r'^NOT$')
    ANYOF = re.compile(r'^ANY OF$')
    ALLOF = re.compile(r'^ALL OF$')
    BOTHSAEM = re.compile(r'^BOTH SAEM$')
    DIFFRINT = re.compile(r'^DIFFRINT$')
    SMOOSH = re.compile(r'^SMOOSH$')
    MAEK = re.compile(r'^MAEK$')
    A = re.compile(r'^A$')
    ISNOWA = re.compile(r'^IS NOW A$')
    VISIBLE = re.compile(r'^VISIBLE$')
    GIMMEH = re.compile(r'^GIMMEH$')
    ORLY = re.compile(r'^O RLY\?$')
    YARLY = re.compile(r'^YA RLY$')
    MEBBE = re.compile(r'^MEBBE$')
    NOWAI = re.compile(r'^NO WAI$')
    OIC = re.compile(r'^OIC$')
    WTF = re.compile(r'^WTF\?$')
    OMG = re.compile(r'^OMG$')
    OMGWTF = re.compile(r'^OMGWTF$')
    IMINYR = re.compile(r'^IM IN YR$')
    UPPIN = re.compile(r'^UPPIN$')
    NERFIN = re.compile(r'^NERFIN$')
    YR = re.compile(r'^YR$')
    TIL = re.compile(r'^TIL$')
    WILE = re.compile(r'^WILE$')
    IMOUTTAYR = re.compile(r'^IM OUTTA YR$')

    # added lexemes / keywords
    STRING = re.compile(r'(.*\s)$')
    NULL = re.compile(r'^()$')
    NEWLINE = re.compile(r'(\n)')
    AN = re.compile(r'^AN$')
    IIZ = re.compile(r'^I IZ$')
    MKAY = re.compile(r'^MKAY$')
    FOUNDYR = re.compile(r'^FOUND YR$')
    GTFO = re.compile(r'^GTFO$')
    HOWIZI = re.compile(r'^HOW IZ I$')
    IFUSAYSO = re.compile(r'^IF U SAY SO$')

    # literals
    NUMBRLTRL = re.compile(r' ^(0|(-?[1-9][0-9]*))$')
    NUMBARLTRL = re.compile(r'^(-[0-9]*\.[0-9]+|[0-9]+\.[0-9]+)$ ')
    YARNLTRL = re.compile(r'^\"(.*?)\"$ ') # dito yung mali natin right, uu
    # ito yung comment: * already means it can by empty, also what if there's a " in the middle?
    # ^ alisin yung question mark sa yarn?
    TROOFLTRL = re.compile(r'^(WIN)|(FAIL)$')
    TYPELTRL = re.compile(r'^(TROOF)|(NOOB)|(NUMBR)|(NUMBAR)|(YARN)|(TYPE)$')

