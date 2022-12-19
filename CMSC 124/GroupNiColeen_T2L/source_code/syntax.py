
import copy


BOOL_OP = ['ALL OF', 'ANY OF', 'EITHER OF', 'WON OF', 'NOT','BOTH OF']
NEST = ['BOTH OF', 'EITHER OF', 'WON OF', 'NOT']
COMPARISON_OP = ['DIFFRINT', 'BOTH SAEM']
BOOL_CONST = ['WIN', 'FAIL']
TYPES = ['YARN', 'NUMBR', 'NUMBAR', 'TROOF', 'TYPE']
ARITH_OP = ['SUM OF', 'DIFF OF', 'PRODUKT OF', 'QUOSHUNT OF', 'MOD OF', 'BIGGR OF', 'SMALLR OF']
LITERALS = ['NUMBR Literal', 'NUMBAR Literal', 'TROOF Literal', 'YARN Literal', 'TYPE Literal']
EXPR_OP = ARITH_OP + BOOL_OP + COMPARISON_OP


class Node:

    # Function to initialise the node object
    def __init__(self, value, type):
        self.data = {
            "value": value,
            "TYPE": type
        }  # Assign data
        self.next = None  # Initialize next as null

class parser:
    def __init__(self, tok):
        self.tok=tok.head
        self.lookahead=None
        self.lookbehind=None
        self.hasError = False
        self._syntax_errors = []

    def nextToken(self):
        if(self.lookahead==None):
            return self.tok
        else:
            self.tok=self.tok.next
            return self.tok

    def start(self):
        while self.lookahead.data['TYPE'] == 'Single-line Comment' or self.lookahead.data['value'] == '\n':
            self.match(self.lookahead.data['value'])

        self.match('HAI')
        if not self.hasError:   self.match('\n')
        if not self.hasError:   self.statement()
        if not self.hasError:   self.match('KTHXBYE')

        if self.lookahead.data['value'] == "$":
            return
        

    # check syntax for each keyword
    def statement(self):
        data = self.lookahead.data['value'] 
        try:
            if (data == 'KTHXBYE'):
                return   
            if(data == 'VISIBLE'):
                self.print_()
            elif(data == 'GIMMEH'):
                self.input_()
            elif(self.lookahead.data['TYPE'] == 'Single-line Comment'):
                self.match(data)
            elif(data == 'SMOOSH'):
                self.concatenation()
            elif(data == 'I HAS A'):
                self.declaration()
            elif (data == 'O RLY?'):
                self.if_then()
            elif(data in EXPR_OP):
                self.expr()
                if (self.lookahead.next.data['value'] == 'O RLY?'):
                    self.if_then()
            elif(data == 'WTF?'):
                self.case()
            elif(data == 'IM IN YR'):
                self.loop()
            elif(data == 'GTFO'):
                self.match('GTFO')
            elif (self.lookahead.data['TYPE'] == 'Multi-line Comment'):
                    if (self.lookbehind.data['value'] == '\n'):
                        self.match(self.lookahead.data['value'])
                        self.match('\n')
            elif (data == '\n'):
                self.match(data)
            elif (self.lookahead.data['TYPE'] == 'Identifier'):
                self.match(self.lookahead.data['value'])
                data = self.lookahead.data['value']
                if (data == 'R'): 
                    self.assignment()
                elif (data == 'IS NOW A'):
                    self.typecast_()
            elif (data == 'MAEK'):
                self.typecast()
            else:
                return

            if self.hasError == False: self.statement()
        except Exception:
            self.raiseError()

    def print_(self):
        self.match('VISIBLE')
        self.print__()
        if self.lookahead.data['TYPE'] == 'Single-line Comment': 
            return
        if self.lookahead.data['value'] == '!':
            self.match('!')
        self.match('\n')

    def print__(self):
            data = self.lookahead.data['value']
            if(data == '\n'): return
            if (self.lookahead.data["TYPE"] == 'Identifier'):
                self.matchID(self.lookahead.data["TYPE"])
            elif (self.lookahead.data["TYPE"] in LITERALS):
                self.match(self.lookahead.data['value'])
            elif (data in EXPR_OP):
                self.expr()
            elif (data == 'SMOOSH'):
                self.concatenation()
            elif (self.lookahead.data['TYPE'] == 'Single-line Comment'):
                return
            # else:
            #     print(self.lookahead.data['value'])
            #     return
            data = self.lookahead.data['value']
            if (data == 'AN'):
                self.match(data)
            self.print__()
           
    def expr(self):
        data = self.lookahead.data['value']
        if (data in ARITH_OP): 
            self.math()
        elif (data in BOOL_OP): 
            self.boolean()
        elif (data in COMPARISON_OP): 
            self.comparison()
        else: 
           self.raiseError()

    def if_then(self):
        self.match('\n')
        self.match('O RLY?')
        self.match('\n')
        self.match('YA RLY')
        self.match('\n')
        self.statement()
        if (self.lookahead.data['value'] != 'OIC'):
            self.else_if()
        self.match('OIC')
    
    def else_if(self):
        data = self.lookahead.data['value']
        if (data == 'MEBBE'):
            self.match(data)
            self.expr()
            self.match('\n')
            self.statement()
            self.else_if()
        elif (data == 'NO WAI'):
            self.match(data)
            self.match('\n')
            self.statement()            
        else:
            self.raiseError()
    
    def case(self):
        self.match('WTF?')
        self.match('\n')
        self.omg_block()
        self.match('OMGWTF')
        self.match('\n')
        self.statement()
        self.match('OIC')

    def omg_block(self):
        self.match('OMG')
        self.literal()
        self.match('\n')
        self.statement()
        if (self.lookahead.data['value'] != 'OMGWTF'):
            self.omg_block()

    def declaration(self):
        self.match('I HAS A')
        self.matchID(self.lookahead.data['TYPE'])
        if (self.lookahead.data['value'] == 'ITZ'):
            self.match('ITZ')
            datatype = self.lookahead.data['TYPE']
            if (datatype in LITERALS):
                self.literal()
            elif (self.lookahead.data['value'] in EXPR_OP):
                self.expr()
            else:
                self.matchID(datatype)

    def assignment(self):
        self.match('R')
        data = self.lookahead.data['value']
        if (self.lookahead.data['TYPE'] in LITERALS): 
            self.literal()
        elif (self.lookahead.data['TYPE'] == 'Identifier'):
            self.matchID(self.lookahead.data['TYPE'])
        elif (data in EXPR_OP): 
            self.expr()
        elif (data == 'MAEK'): 
            self.typecast()
        elif (data == 'SMOOSH'): 
            self.concatenation()
  
    def typecast(self):
        self.match('MAEK')
        self.matchID(self.lookahead.data['TYPE'])
        if self.lookahead.data['value'] == 'A':
            self.match('A')
        if (self.lookahead.data['value'] in TYPES):
            self.match(self.lookahead.data['value'])
        else:
            self.raiseError()
            
    def typecast_(self):
        self.match('IS NOW A')
        data =self.lookahead.data['value']
        if data in TYPES:
            self.match(data)
        else:
            self.raiseError()         

    def literal(self):
        if (self.lookahead.data['TYPE'] in LITERALS): 
            self.match(self.lookahead.data['value'])
        else:
            self.raiseError()
        
    def loop(self):
        self.match('IM IN YR')
        self.matchID(self.lookahead.data['TYPE'])
        self.unary()
        self.match('YR')
        self.matchID(self.lookahead.data['TYPE'])
        self.limiter()
        self.bool_comp()
        self.match('\n')
        self.statement()
        self.match('IM OUTTA YR')
        self.matchID(self.lookahead.data['TYPE'])
        
    def unary(self):
        data =self.lookahead.data['value']
        if (data in ['UPPIN', 'NERFIN']):
            self.match(data)
        else: 
            self.raiseError()

    def limiter(self):
        if (self.lookahead.data['value'] in ['TIL', 'WILE']):
            self.match(self.lookahead.data['value'])
        else:
            self.raiseError()

    def bool_comp(self):
        if(self.lookahead.data['value'] in BOOL_OP): 
            self.boolean()
        elif(self.lookahead.data['value'] in COMPARISON_OP): 
            self.comparison()
        else:
            self.raiseError()

    def input_(self):
        self.match('GIMMEH')
        self.matchID(self.lookahead.data["TYPE"])

    def concatenation(self):
        self.match('SMOOSH')
        self.concatenate()

    def concatenate(self):
        data = self.lookahead.data['value']
        if data != "\n":
            self.match(data)
            self.concatenate()

    def comment(self):
        data = self.lookahead.data['value']
        if (data == 'BTW'):
            self.match(data)
            self.string_()
            self.match("\n")
        elif (data == 'OBTW'):
            self.match(data)
            self.line_()
            self.match('TLDR')
            self.match("\n")

    def string_(self):
        self.match(self.lookahead.data['value'])
        if self.lookahead.data['value'] != "\n":
            self.string_()
        else:
            return

    def line_(self):
        self.string_()
        self.match('\n')
        if self.lookahead.data['value'] == 'TLDR':
            return
        else:
            self.line_()
           
    def comparison(self):
        INEQUALITY = ['SMALLR OF', 'BIGGR OF']
        prev_op = self.lookahead.data['value']
        if prev_op in COMPARISON_OP+INEQUALITY:
            self.match(prev_op)
            self.comp_operand()
            self.match('AN')

            next_op = self.lookahead.data['value']

            if (prev_op in COMPARISON_OP and next_op in INEQUALITY) or (prev_op in INEQUALITY and next_op in COMPARISON_OP):
                self.match(next_op)
                self.comp_operand()
                self.match('AN')
            self.comp_operand()
        else:
            self.raiseError()

    def comp_operand(self):
        data = self.lookahead.data['value']
        datatype = self.lookahead.data['TYPE']
        
        if (datatype == 'Identifier'):
            self.matchID(datatype)
        elif (datatype in LITERALS):
            self.match(data)
        elif (data in EXPR_OP):
            self.expr()
        else:
            self.raiseError()
                            
    def troofs(self):
        dtype = self.lookahead.data['TYPE']
        if(self.lookahead.data['value'] in (BOOL_CONST+NEST+['0','1']) or dtype == 'Identifier'):
            self.boolean_operand()
            data = self.lookahead.data['value']
            if (data == 'AN'):
                self.match('AN')
                self.troofs()
            elif (data == 'MKAY'): 
                return
            else:
                self.raiseError()
        else: 
            self.raiseError()

    def boolean(self):
        if(self.lookahead.data['value'] in BOOL_OP):
            data = self.lookahead.data['value']
            if (data in NEST):
                self.boolean_nest()
            elif (data in ['ALL OF', 'ANY OF']):
                self.match(data)
                self.boolean_operand()
                self.match('AN')
                self.troofs()
                self.match('MKAY')
        else: 
            self.raiseError()

    def boolean_nest(self):
        data = self.lookahead.data['value']
        self.match(self.lookahead.data['value'])
        self.boolean_operand()
        if (data == 'NOT'): return
        self.match('AN')
        self.boolean_operand()
 
    def boolean_operand(self):
        data = self.lookahead.data['value']
        if (self.lookahead.data['TYPE'] == 'Identifier' or data in (['0', '1']+BOOL_CONST)):
            self.match(data)
        elif (data in NEST):
            self.boolean_nest()
        else:
            self.raiseError()
    
    def math(self):
        if (self.lookahead.data['value'] in ARITH_OP):
            self.match(self.lookahead.data['value'])
            self.math_op()
            self.match('AN')
            self.math_op()
        else:
            self.raiseError()
            
    def math_op(self):
        MATH_OPERANDS = ['NUMBR Literal', 'NUMBAR Literal', 'Identifier', 'YARN Literal']
        data = self.lookahead.data['value']
        dtype = self.lookahead.data['TYPE']
        
        if (dtype in MATH_OPERANDS):
            self.match(self.lookahead.data['value'])
        elif (self.lookahead.data['value'] in ARITH_OP):
            self.math()
        elif (data in ['WIN', 'FAIL']):
            self.match(self.lookahead.data['value'])
        else:
            self.raiseError()

    def raiseError(self, t=None):
        """creates error statement/prompt

        Args:
            t (str, optional): expected token. Defaults to None.
        """
        tok = self.lookahead.data['value']
        if (tok == '\n'): 
            tok = self.lookbehind.data['value']
        elif (tok == '$'):
            tok = 'end of file'
        error_stmt = "\nSyntaxError near: '" + tok.replace('\n', ' ') + "';"
        if (t != None):
            if (t == '\n'): t = "\\n"
            error_stmt += " expected: '" + t + "'"
        self.hasError = True
        self._syntax_errors.append(error_stmt)
    
    def match(self,t):
        """try to match next token to be read.
            if unsuccessful, raise error

        Args:
            t (str): token to be matched
        """
        if(self.lookahead.data['value'] == t):
            self.lookbehind = self.lookahead
            self.lookahead = self.nextToken()
        
        else:
            self.raiseError(t)

    def matchID(self,type):
        """tries to match an identifier

        Args:
            type (str): type of token that needs to be matched
        """
        if(type=='Identifier'):
            self.lookahead = self.nextToken()
        else:
            self.raiseError('identifier')


class Nodes:

    # Function to initialize head
    def __init__(self):
        self.head = None

    # This function is defined in Linked List class
    # Appends a new node at the end. This method is
    # defined inside LinkedList class shown above */
    def append(self, value, type):

        # 1. Create a new node
        # 2. Put in the data
        # 3. Set next as None
        new_node = Node(value, type)

        # 4. If the Linked List is empty, then make the
        # new node as head
        if self.head is None:
            self.head = new_node
            return

        # 5. Else traverse till the last node
        last = self.head
        while (last.next):
            last = last.next

        # 6. Change the next of last node
        last.next = new_node

def analyze_syntax(lexemes, app):
    """analyzes syntax and returns errors found if any"""
    tok = Nodes()

    for token in lexemes:
        if (token[0] == '\\n'):
            tok.append('\n', token[1])
        else: tok.append(token[0], token[1])
    tok.append('$', "EOF")
    parser_ = parser(tok)
    parser_.lookahead = parser_.nextToken()
    parser_.start()

    if parser_.hasError == True:
        errors = ''.join(parser_._syntax_errors)
        app.printToConsole(errors)
        return False
    return True


                



