import re
import os
import lexer

BOOL_OP = ['ALL OF', 'ANY OF', 'EITHER OF', 'WON OF', 'NOT']
COMPARISON_OP = ['DIFFRINT', 'BOTH SAEM']
UNARY_KEYWORDS = ['UPPIN', 'NERFIN']

COMMENT = ['BTW', 'OBTW', 'TLDR']
IF_ELSE = ['O RLY?', 'YA RLY', 'MEBBE', 'NO WAI', 'OIC']
CASE = ['WTF?', 'WTF', 'OMGWTF']
LOOPS = ['IM IN YR', 'YR', 'IM OUTTA YR']
TYPECAST = ['MAEK', 'IS NOW A']
BOOL_CONST = ['WIN', 'FAIL']

LIMITER = ['TIL', 'WILE']

TYPES = ['YARN', 'NUMBR', 'NUMBAR', 'TROOF', 'TYPE']
ARITH_OP = ['SUM OF', 'DIFF OF', 'PRODUKT OF', 'QUOSHUNT OF', 'MOD OF', 'BIGGR OF', 'SMALLR OF']
ASSIGNMENT_OP = ['R', 'ITZ']
RETURN = ['FOUND YR', 'GTFO']
OMG = ['OMG']

INPUT = ['GIMMEH']
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

# Node of a doubly linked list
class Symbol_table_Node:
    def __init__(self, name, line_numbers, type,scope):
        self.data = {
            "Name": name,
            "LINE_NUMBERS": line_numbers,
            "TYPE": type,
            "Scope": scope
        }  # Assign data
        self.next = None  # Initialize next as null
        self.prev = None
class SymbolTable:
    def __init__(self):
        self.head = None
    def push(self, name, line_numbers, type,scope):
            new_node = Symbol_table_Node(name, line_numbers, type,scope)


            new_node.next = self.head
            new_node.prev = None


            if self.head is not None:
                self.head.prev = new_node


            self.head = new_node

    def printList(self):
        node = self.head
        while (node != None):

            print(node.data)
            last = node
            node = node.next

    def FindData(self, name):
        flag = False
        node = self.head

        while (node != None):

            if (node.data['Name'] == name):
                return node.data
            last = node
            node = node.next
        if (flag == False):
            return False

class parser:
    def __init__(self,tok):
        self.tok=tok.head
        self.lookahead=None

    def nextToken(self):
        if(self.lookahead==None):
            return self.tok
        else:
            self.tok=self.tok.next
            return self.tok

    def start(self):
        data = self.lookahead.data['value']
        
        if (data == 'HAI'):
            self.match("HAI")
            self.match("\n")
            self.statement()
            self.match("KTHXBYE")
            if(data == "$"):
                return
        elif(data == "$"):
            return
        else:
            print("SYNTAX ERROR")

    def print_(self):
        data = self.lookahead.data['value']
        
        if (data == 'VISIBLE'):
            self.match('VISIBLE')
            self.print__()
            data = self.lookahead.data['value']
            if data == 'BTW':
                return
            self.match('\n')
    def print__(self):
            literals = ['NUMBR Literal', 'NUMBAR Literal', 'TYPE Literal', 'YARN Literal', 'TROOF Literal']
            data = self.lookahead.data['value']
            if(data == '\n'): return
            if (self.lookahead.data["TYPE"] == 'Identifier'):
                self.matchID(self.lookahead.data["TYPE"])
            elif (self.lookahead.data["TYPE"] in literals):
                self.match(self.lookahead.data["value"])
            elif (data in BOOL_OP):
                self.boolean()
            elif (data in ARITH_OP):
                self.math()
            elif (data in COMPARISON_OP):
                self.comparison()
            elif (data == 'I IZ'):
                self.function()
            elif (data == 'BTW'):
                return
            else:
                print("Syntax error in VISIBLE")
            self.print__()
            

            
    def statement(self):
        data = self.lookahead.data['value'] 
        if (data == 'KTHXBYE'): return   
        if(data == 'VISIBLE'):
            self.print_()
        elif(data == 'GIMMEH'):
            # input statement
            self.input_()
        elif(data in COMMENT):
            # single line comment
            self.comment()
        elif(data == 'SMOOSH'):
            # concatenation
            self.concatenation()
        elif(data in BOOL_OP):
            # bool statement
            self.boolean()
        elif(data in COMPARISON_OP):
            # comparison statement
            self.comparison()
        elif(data in ARITH_OP):
            # arithmetic statement (math)
            self.math()
        elif(data == 'I HAS A'):
            # declaration statement (declaration)
            self.declaration()
        # elif(data in TYPECAST):
        #     # typecast 
        #     self.selection()
        # elif(data == 'I IZ'):
        #     # function call 
        #     self.selection()
        elif(data == 'IM IN YR'):
            # loops
            self.loop()
        elif(data in IF_ELSE):
            self.if_then()
        elif(data in CASE):
            self.case()
        elif (data == '\n'):
            self.match(data)
        elif (self.lookahead.data['TYPE'] == 'Identifier'):
            self.assignment()
        elif (data == 'MAEK'):
            self.typecast()
        else:
            print("ERROR IN STATEMENT")
            return
        self.statement()

   
    # def function_(self):
    #     data = self.lookahead.data['value']
    #     if data == 'I IZ':
    #         self.match(data)
    #         if (self.lookahead.data['TYPE'] == 'Identifier'):
    #             self.matchID(self.lookahead.data["TYPE"])
    #         self.argument_()
            
    # def argument_(self):
    #     data = self.lookahead.data['value']
    #     if (data == 'yr'):
    #         self.match(data)
    #         if (self.lookahead.data['TYPE'] == 'Identifier'):
    #             self.matchID(self.lookahead.data["TYPE"])
    #         self.argument_()
                
            
                 

    def if_then(self): # i2 b yung if else , yessir ifthen
        data = self.lookahead.data['value']
        if (data in ARITH_OP): self.math()
        elif (data in BOOL_OP): self.boolean()
        elif (data in COMPARISON_OP): self.comparison()
        elif (data == 'I IZ'): self.function()
        else: 
            print("Error in if_then") 
            self.match('\nO RLY?\n')
        self.match('YA RLY\n')
        self.statement()
        self.match('\n')
        self.else_if()
        self.match('OIC')
    
    def else_if(self):
        data = self.lookahead.data['value']
        if (data == 'MEBBE'):
            self.match(data)
            if (data in ARITH_OP): self.maself.expr()
            self.statement()
            self.match('\n')
            self.else_if()
        elif (data == 'NO WAI'):
            self.match(data)
            self.match('\n')
            self.statement()
            self.match('\n')
        else: 
            print("Error in else_if")
    
    # if di niyo pala makita yung terminal pa-message lungs     okkk

    def case(self):
        data = self.lookahead.data['value']
        if(data == "WTF?" or data == "WTF"): # bat pala considered yung "WTF" dito?
            self.match("\n")
            self.omg()       
            self.statement() # for additional omg's, poide rin ata
            # required ba OMGWTF dito?
            self.match('OIC')
        else: print("Error in case")

    def omg_block(self):
        data = self.lookahead.data['value']
        if (data == 'OMG'):
            self.omg()
            if (self.lookahead.data['value'] != 'OMGWTF'): self.omg_block()
        else: print("Error in omg_block")
        
    def omg(self):
        data = self.lookahead.data['value']
        if (data == 'OMG'):
            self.match(data)
            self.literal()
            self.match('\n')
            self.statement()
            if (self.lookahead.data['value'] == 'GTFO'): self.match(self.lookahead.data['value'])
            self.match('\n')
        else: 
            print("Error in OMG")

    def declaration(self):
        if (self.lookahead.data['value'] == 'I HAS A'):
            self.match(self.lookahead.data['value'])
            if (self.lookahead.data['TYPE'] == 'Identifier'):
                self.matchID(self.lookahead.data["TYPE"])
                
            if (self.lookahead.data['value'] == 'ITZ'):
                self.match(self.lookahead.data['value'])
                data = self.lookahead.data['value']
                if (self.lookahead.data['TYPE'] in LITERALS): self.literal()
                elif (self.lookahead.data['TYPE'] == 'Identifier'):
                    self.matchID(self.lookahead.data["TYPE"])
                elif (data in ARITH_OP): self.math()
                elif (data in BOOL_OP): self.boolean()
                elif (data in EXPR_OP or data == 'I IZ'): 
                    self.expr()
                else: 
                    print("Error in declaration")
    
    def typecast(self):
        data =self.lookahead.data['value']
        if data == 'MAEK':
            self.match(data)
            
            if (self.lookahead.data['TYPE'] == 'Identifier'):
                self.matchID(self.lookahead.data["TYPE"])
            else:
                print('Error in Typecast')
            
            self.match('A')
            data =self.lookahead.data['value']
            if data in TYPES:
                self.match(data)
                print(data)
            else:
                print('Error in Typecast')
        elif(self.lookahead.data['TYPE'] == 'Identifier'):
            self.matchID(self.lookahead.data["TYPE"])
            self.match('IS NOW A')
            if data in TYPES:
                self.match(data)
            else:
                print('Error in Typecast')           

    def assignment(self):
        if (self.lookahead.data['TYPE'] == 'Identifier'):
            self.matchID(self.lookahead.data["TYPE"])
        self.match('R')
        data = self.lookahead.data['value']
        if (self.lookahead.data['TYPE'] in LITERALS): 
            self.literal()
        elif (data in ARITH_OP): 
            self.math()
        elif (data in BOOL_OP): 
            self.boolean()
        elif (data in COMPARISON_OP):
            self.comp_operand()
        elif (data in EXPR_OP or data == 'I IZ'): 
            self.expr().lookahead.data['TYPE'] = self.lookahead.data['value']
        if (dtype in LITERALS): 
            self.match(data)
        else: 
            print("Error in literal")
        
    def loop(self):
        if (self.lookahead.data['value'] == 'IM IN YR'):
            self.match(self.lookahead.data['value'])
            if (self.lookahead.data['TYPE'] == 'Identifier'):
                self.matchID(self.lookahead.data["TYPE"])
            self.unary()
            self.match('YR')
            if (self.lookahead.data['TYPE'] == 'Identifier'):
                      self.matchID(self.lookahead.data["TYPE"])
            self.limiter()
            self.bool_comp()
            self.match('\n')
            self.statement()
            self.match('\n')
            self.match('IM OUTTA YR')
            if (self.lookahead.data['TYPE'] == 'Identifier'):
                self.matchID(self.lookahead.data["TYPE"])
        else: 
            print("Error in loop")
        
    
    def unary(self):
        data =self.lookahead.data['value']
        print(data)
        if (data in UNARY_KEYWORDS):
            print(data)
            self.match(data)
        else: print("Error in unary")

    def limiter(self):
        if (self.lookahead.data['value'] in LIMITER):
            self.match(self.lookahead.data['value'])
        else: 
            print("Error in limiter")

    def bool_comp(self):
        if(self.lookahead.data['value'] in BOOL_OP): self.boolean()
        elif(self.lookahead.data['value'] in COMPARISON_OP): self.comparison()
        else: 
            print("Error in bool_comp")


    def input_(self):
        data = self.lookahead.data['value']
        if data == 'GIMMEH':
            self.match(data)
            if (self.lookahead.data['TYPE'] == 'Identifier'):
                self.matchID(self.lookahead.data["TYPE"])

    def concatenation(self):
        data = self.lookahead.data['value']
        if data == 'SMOOSH':
            self.match(data)
            self.concatenate()
            
    def concatenate(self):
        data = self.lookahead.data['value']
        if data != "\n":
            self.match(data)
            self.concatenate()


    def comment(self):
        data = self.lookahead.data['value']
        
        if (data == 'BTW'):
            self.match("BTW")
            self.string_()
            self.match("\n")
            data = self.lookahead.data['value']
            if data == 'OBTW':
                self.match(data)
                self.string_()
                self.match("\n")

    def string_(self):
        data = self.lookahead.data['value']
        self.match(data)
        if self.lookahead.data['value'] != "\n":
            self.string_()
        else:
            return

    def line_(self):
        data = self.lookahead.data['value']
        self.string_()
        data = self.lookahead.data['value']
        if data == "\n":
            self.match("\n")
            if data == "TLDR":
                self.match(data)
            else:
                self.line_()
           

    def comparison(self):
        if(self.lookahead.data['value'] in COMPARISON_OP):
            self.match(self.lookahead.data['value'])
            self.comp_operand()
            self.match('AN')
            data = self.lookahead.data['value']
            if (data in ['SMALLR OF', 'BIGGR OF']):
                self.match(data)
                self.comp_operand()
                self.match('AN')
            self.comp_operand()
            
        else: print("Error in comparison")
            
    def comp_operand(self):
        literals = ['NUMBR Literal', 'NUMBAR Literal', 'TYPE Literal', 'YARN Literal', 'TROOF Literal']
        data = self.lookahead.data['value']
        data = self.lookahead.data['value']
        if (self.lookahead.data["TYPE"] == 'Identifier'):
            self.matchID(self.lookahead.data["TYPE"])
        elif (self.lookahead.data["TYPE"] in literals):
            self.match(self.lookahead.data["value"])
        elif (data in BOOL_CONST):
            self.boolean_operand()
        elif (data in ARITH_OP):
            self.mathOp()
        elif (data == 'I IZ'):
            self.function()
        else:
            print("ERROR IN COMP_OPERAND")
                
    def boolean(self):
        if(self.lookahead.data['value'] in BOOL_OP):
            NEST = ['BOTH OF', 'EITHER OF', 'WON OF', 'NOT']
            data = self.lookahead.data['value']
            self.match(data)
            if (data in NEST):
                self.boolean_nest()
            elif (data in ['ALL OF', 'ANY OF']):
                self.boolean_operand()
                self.match('AN')
                self.troofs()
                self.match('MKAY')
            else: print("Error in boolean")
                
    def troofs(self):
        if(self.lookahead.data['value'] in BOOL_CONST):
            self.boolean_operand()
            data = self.lookahead.data['value']
            if (data == 'AN'):
                self.match('AN')
                self.troofs()
            elif data == 'MKAY':
                return
            else: print("Error in boolean_troof")
    
    def boolean_nest(self):
        if (self.lookahead.data['value'] in BOOL_OP):
            self.match(self.lookahead.data['value'])
            self.boolean_operand()
            self.match('AN')
            self.boolean_operand()
        else:
            print("Error in math")
        
    def boolean_operand(self):
        data = self.lookahead.data['value']
        if (self.lookahead.data['TYPE'] == 'Identifier'):
            self.match(data)
        elif (data in BOOL_CONST):
            self.match(data)
        elif (data in BOOL_OP):
            self.boolean_nest()
        else:
            print("Error in boolean operand")
    
    def math(self):
        if (self.lookahead.data['value'] in ARITH_OP):
            self.match(self.lookahead.data['value'])
            self.mathOp()
            self.match('AN')
            self.mathOp()
        else:
            print("Error in math")

    def mathOp(self):
        MATH_OP = ['NUMBR Literal', 'NUMBAR Literal', 'Identifier']

        if (self.lookahead.data['TYPE'] in MATH_OP):
            self.match(self.lookahead.data['value'])
        elif (self.lookahead.data['TYPE'] in ARITH_OP):
            self.math()
        else:
            print("Error in math operand")

    def match(self,t):
        if(self.lookahead.data['value'] == t):
            self.lookahead= self.nextToken()

        else:
            print("ERROR WITH SYNTAX NEAR TOKEN: ",self.lookahead.data['value'])
            exit()
    def matchID(self,type):
        
        if(type=='Identifier'):
            self.lookahead = self.nextToken()
        else:
            print("ERROR IN TOKEN: " , self.lookahead.data)
            print("EXPECTED TO BE IDENTIFIER ...")
            exit()


class Tokkens:

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

    # Utility function to print the linked list
    def printList(self):
        temp = self.head
        while (temp):
            print(temp.data),
            temp = temp.next

def lexer_():
    lexer_ = lexer.Lexer()
    # lexer.main()
    tok = Tokkens()
    # lagay ka sample input
    # sige hai muna HAHAHA tapos yung math
    str = """HAI
    I HAS A food ITZ "111.00033"
    I HAS A var ITZ 10
    VISIBLE food
    BTW this is how we do type casting
    VISIBLE food
    MAEK food A NUMBAR
    ASD R SUM OF X AN Y
    KTHXBYE"""

    # str = """HAI
    #         I HAS A NAME
    #         KTHXBYE"""

    # iba yang tok sa tokens natin ah
# ang sakit sa ulo ntiong 124
# hahahahaha gumana
# weh ano lumabas
# ng ano?

# sinend ko sa general
# may error ata dahil sa eof
# oki okiii, lagay siguro tayo flag next time
# para pag may error, di na sabihin na syntax is correct?
# okeii ito lang ata nasa baba un

# ano pala need i-present mamaya? syntax analyzer ba like aanalyze lang kung tama syntax
# or parser na mismo?
# ay slr lahat ata pati symbol table
# ibang segment na next week e
# dami pala ng hinihingi ngayon
# ikrr
    lexer_.input(str)
    for token in lexer_.tokens():
        tok.append(token.val, token.type)
    tok.append('$', "EOF")
    tok.printList()
    #print("\nSYMBOL TABLE: \n")
    #lexer.sym.printList()
    #print("\n")
    check = parser(tok)
    check.lookahead = check.nextToken()
    check.start()
# di natin nalagyan ng exit() sa kada else
# bat lalagyan exit AHHH pag nag-error? oks oks
# try natin math, duda ako eh HAHAHA ay jk mattry na ba natin
# 
    # here
    # a
    # paasa ampotchi akala ko HAHAHA
    if check.lookahead.data['value']  == '$':
        print("SYNTAX IS CORRECT... ")



if __name__ == '__main__':
    lexer_()
