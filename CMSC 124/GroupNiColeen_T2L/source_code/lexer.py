import re

class Token(object):
    """""Contains token type, value, and position"""
    def __init__(self, type, val, pos):
        self.type = type
        self.val = val
        self.pos = pos

    def __str__(self) -> str:
        """ returns the string representation of the Token
            Lexeme + Classification
        """
        if self.type == "Line delimiter":
            return "Lexeme: \\n\t\tClassification: %s" % (self.type)
        return 'Lexeme: %s\t\tClassification: %s' % (self.val, self.type)

class LexerError(Exception):
    def __init__(self, pos, symbol):
        self.pos = pos
        self.symbol = symbol
    def __str__(self) -> str:
        return f"LexerError at position {self.pos}, with symbol '{self.symbol}'"
        

class Lexer(object):
    """
    Lexer: recognize reserved keywords from object input
    """
    def __init__(self):
        """
        rules -> regex patterns used to match substrings and tokenize them
        """
        rules = [
        # KEYWORDS
        ('(?!<\w)HAI(?!\w)',            'Code Delimiter'),
        ('(?!<\w)KTHXBYE(?!\w)',        'Code Delimiter'),
        ('BTW .*\\n',                   'Single-line Comment'),
        ('OBTW[\s\S]*?TLDR',            'Multi-line Comment'),
        ('(?!<\w)I HAS A(?!\w)',        'Variable Declaration'),
        ('(?!<\w)ITZ(?!\w)',            'Variable Assignment'),
        ('(?!<\w)R(?!\w)',              'Variable Assignment'),
        ('(?!<\w)SUM OF(?!\w)',         'Math Operator'),
        ('(?!<\w)DIFF OF(?!\w)',        'Math Operator'),
        ('(?!<\w)PRODUKT OF(?!\w)',     'Math Operator'),
        ('(?!<\w)QUOSHUNT OF(?!\w)',    'Math Operator'),
        ('(?!<\w)MOD OF(?!\w)',         'Math Operator'),
        ('(?!<\w)BIGGR OF(?!\w)',       'Math Operator'),
        ('(?!<\w)SMALLR OF(?!\w)',      'Math Operator'),
        ('(?!<\w)BOTH OF(?!\w)',        'Boolean Operator'),
        ('(?!<\w)EITHER OF(?!\w)',      'Boolean Operator'),
        ('(?!<\w)WON OF(?!\w)',         'Boolean Operator'),
        ('(?!<\w)NOT(?!\w)',            'Boolean Operator'),
        ('(?!<\w)ANY OF(?!\w)',         'Boolean Operator'),
        ('(?!<\w)ALL OF(?!\w)',         'Boolean Operator'),
        ('(?!<\w)BOTH SAEM(?!\w)',      'Comparison Operator'),
        ('(?!<\w)DIFFRINT(?!\w)',       'Comparison Operator'),
        ('(?!<\w)SMOOSH(?!\w)',         'Concatenator'),
        ('(?!<\w)MAEK(?!\w)',           'Typecast Operator'),
        ('(?!<\w)A(?!\w)',              'Reserved [A] keyword'),
        ('(?!<\w)IS NOW A(?!\w)',       'Typecast Operator'),
        ('(?!<\w)VISIBLE(?!\w)',        'Output Keyword'),
        ('(?!<\w)GIMMEH(?!\w)',         'Input Keyword'),
        ('(?!<\w)O RLY\?(?!\w)',        'If-then Statement'),
        ('(?!<\w)YA RLY(?!\w)',         'If-then Statement'),
        ('(?!<\w)MEBBE(?!\w)',          'If-then Statement'),
        ('(?!<\w)NO WAI(?!\w)',         'If-then Statement'),
        ('(?!<\w)OIC(?!\w)',            'If-then Statement'),
        ('(?!<\w)WTF\?(?!\w)',           'Switch-case'),
        ('(?!<\w)OMG(?!\w)',            'Switch-Case'),
        ('(?!<\w)OMGWTF(?!\w)',         'Switch-Case'),
        ('(?!<\w)IM IN YR(?!\w)',       'Loop'),
        ('(?!<\w)UPPIN(?!\w)',          'Increment Operator'),
        ('(?!<\w)NERFIN(?!\w)',         'Decrement Operator'),
        ('(?!<\w)YR(?!\w)',             'Loop'),
        ('(?!<\w)TIL(?!\w)',            'Loop'),
        ('(?!<\w)WILE(?!\w)',           'Code Delimiter'),
        ('(?!<\w)IM OUTTA YR(?!\w)',    'Code Delimiter'),
        ('(?!<\w)AN(?!\w)',             'Conjunction'),
        ('(?!<\w)MKAY(?!\w)',           'Argument Delimiter'),
        ('(?!<\w)GTFO(?!\w)',           'Function Return'),
        ('(\\n)',                       'Line Delimiter'),

        # LITERALS
        ('(?!<\w)(-[0-9]*\.[0-9]+|[0-9]+\.[0-9]+)(?!\w)',                   'NUMBAR Literal'),
        ('(?!<\w)(0|(-?[1-9][0-9]*))(?!\w)',                                'NUMBR Literal'),
        ('(?!<\w)((WIN)|(FAIL))(?!\w)',                                     'TROOF Literal'),
        ('(?!<\w)((TROOF)|(NOOB)|(NUMBR)|(NUMBAR)|(YARN)|(TYPE))(?!\w)',    'TYPE Literal'),
        ('(?!<\w)\\"(:\\"|:\\)|:\\>|:o|::|[^\\"])*\\"(?!\w)',               'YARN Literal'),

        # IDENTIFIERS
        ('[a-zA-Z][a-zA-Z0-9_]*',                                           'Identifier'),
        
        # OTHERS
        ('(\\!)',                                                           'Suppress Newline'),
        ]
        # All the regex-patterns declared in the 'rules' are concatenated via groups
        # The groups are named according to current index
        # Group names are then mapped according to their type (as specified in 'rules')
        # regex named groups info may be found here: https://docs.python.org/3/library/re.html
        
        index = 1
        regex_groups = []
        self.group_type = {}


        for regex, type in rules:
            group_name = 'GROUP%s' % index
            regex_groups.append('(?P<%s>%s)' % (group_name, regex))
            self.group_type[group_name] = type
            index += 1
        print(regex_groups)
        self.regex = re.compile('|'.join(regex_groups)) # concatenate all the parts (groups)
        self.re_ws_skip = re.compile('[^ \\t]')         # skips spaces and tabs
            

    def input(self, buffer):

        """initialize the input at position 0"""
        self.buffer = buffer
        self.pos = 0


    def token(self):
        """
        returns created Token object
        """
        
        if self.pos >= len(self.buffer): # return None if the end of buffer is reached
            return None
        else:

            x = self.re_ws_skip.search(self.buffer, self.pos) # search for a match in the buffer

            if x:
                self.pos = x.start() # set pos at index of start of the substring matched
            else: return None 

            # once found, match with pattern
            m = self.regex.match(self.buffer, self.pos) # match a pattern from the regex generated from the 'rules'
 
            # once matched, determine the group name and type
            # return the generated Token object 
            if m:
                groupname = m.lastgroup                         # get the name of the matched group
                token_type = self.group_type[groupname]         # get the group type using the group name
                tok = Token(token_type, m.group(groupname), self.pos)   # create Token object
                self.pos = m.end()                              # update pos
                return tok                                      # return token

            raise LexerError(self.pos, x.group(0))
             

    def tokens(self):
        """generator function used to generate tokens"""
        while 1:
            tok = self.token()
            if tok is None: break
            yield tok               # generate token and continue to the next one
        

def run_lexer(input_, app):
    """runs the lexer by traversing through all the tokens
    returns: tokens if no errors are found, else return the error
    """
    lex = Lexer()
    lex.input(input_)

    tokens = []
    
    try:
        for tok in lex.tokens():
            if (tok.val == '\n'):
                lexeme_pair = ('\\n', tok.type)
            else:
                lexeme_pair = (tok.val, tok.type)
            tokens.append(lexeme_pair)

    except LexerError as e:
        app.printToConsole(e)
        app.printToConsole('\n')
        return e
    return tokens

    
    

    

# ADDITIONAL REFERENCES
# https://gist.github.com/eliben/5797351
# https://docs.python.org/3/library/re.html#re.search
 # https://www.geeksforgeeks.org/use-yield-keyword-instead-return-keyword-python/