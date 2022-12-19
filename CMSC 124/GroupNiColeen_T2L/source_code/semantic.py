from collections import deque
import re

# SOME LEXEME CLASSIFICATIONS
LITERALS = ['NUMBR Literal', 'NUMBAR Literal', 'TROOF Literal', 'YARN Literal', 'TYPE Literal']
ARITH_OP = ['SUM OF', 'DIFF OF', 'PRODUKT OF', 'QUOSHUNT OF', 'MOD OF', 'BIGGR OF', 'SMALLR OF']
COMPARISON_OP = ['DIFFRINT', 'BOTH SAEM']
BIN_BOOL_OP = ['EITHER OF', 'WON OF','BOTH OF']
INF_OP = ['ALL OF', 'ANY OF', 'SMOOSH']
UNARY_OP = ['NOT']
TYPECAST_OP = ['MAEK', 'IS NOW A']
TYPES = ['YARN', 'NUMBR', 'NUMBAR', 'TROOF', 'TYPE']

BINARY_OP = ARITH_OP + COMPARISON_OP + BIN_BOOL_OP
OPERATORS = BINARY_OP + INF_OP + UNARY_OP + TYPECAST_OP

# value of the first element and type is the second element in the tuple
VALUE = 0
TYPE = 1


class SemanticAnalyzer:
    """
        analyzes lexemes semantically
        attributes:
        lexemes -> lexemes
        app -> tkinter window
        curr_token -> lexeme to be read
        symbol_table -> table of variable names, values, and types
        stop -> boolean; to determine whether the analyzer should stop.
        break_loop -> boolean; determine if break should be implemented inside a loop
    """

    def __init__(self, lexemes, app):
        # convert list of lexemes to deque to popleft()
        # since deque.popleft() is faster than list.pop(0) 
        self.lexemes = deque(lexemes)
        self.app = app
        self.curr_token = self.lexemes[0]  
        self.symbol_table = {}
        self.stop = False
        self.break_loop = False

    
    def start_program(self):
        """starts the execution of the code"""        
        while self.curr_token[VALUE] != 'HAI': self.advance()
        self.advance()
        while self.curr_token[VALUE] != 'KTHXBYE' and self.stop == False:
            if self.curr_token[VALUE] != '\n': 
                self.statement(self.curr_token)
            else: self.advance()
        
   
    def get_user_input(self):
        """get input from the user through app text field"""
        self.app.input_entry.focus_set()
        var_name = self.curr_token[VALUE]
        value = self.app.getConsoleInput()
        if value != None:
            self.add_symbol(var_name, value, 'YARN Literal')
        else: 
            self.app.printToConsole('\nError: expected an input.\nExecution terminated.\n')
            self.stop = True

    def print_output(self):
        """prints text to output to console""" 
        text = ''
        supp_newline = False
        while self.curr_token[VALUE] != '\n':
            if self.curr_token[VALUE] == '!': supp_newline = True                   # supress newline                                 
            result = self.eval()
            if result: val = result[VALUE]
            if result[TYPE] == 'NUMBAR Literal':
                val = self.LOLtypecast_('YARN', var_value=float(val))[VALUE]
            text = text + self.convert_string(val)

        if supp_newline == False: text = text + '\n'                                # add newline
        self.app.printToConsole(text)

    def declare_var(self):
        """declare a variable. 
        by default, a variable is assigned as NOOB type
        """ 
        var_name = self.advance()[VALUE]
        self.add_symbol(var_name, '', 'NOOB')
        if self.curr_token[VALUE] == 'ITZ':
            self.advance()
            result = self.eval()
            if result:
                self.add_symbol(var_name, result[VALUE], result[TYPE])
    
    def convert_string(self, txt):
        """converts text to YARN Literal

        Args:
            txt (str): text to be converted

        Returns:
            str: YARN Literal
        """
        # dictionary of substrings and their replacements
        rep = {
            'AN'    : '',
            ':)'    : '\n',
            ':>'    : '\t',
            ':o'    : '\u2022',
            ':"'    : '"',
            '::'    : ':'
        }

        rep = dict((re.escape(k), v) for k, v in rep.items())               # escape characters that need escaping for python regex
        pattern = re.compile("|".join(rep.keys()))                          # join via logical OR
        txt = pattern.sub(lambda m: rep[re.escape(m.group(0))], str(txt))   # replace matched substrings
        
        return txt
    
    def if_then(self): 
        while self.curr_token[VALUE] == '\n': 
            self.advance()
        while self.curr_token[VALUE] != 'OIC': # while we are not reaching the end of the if-then block
            data = self.advance()[VALUE]
            if self.get_var_details('IT')[VALUE] == 'WIN':
                # evaluate the statements until we reach the else, else if, or the end of the if-then block
                while self.curr_token[VALUE] not in ['NO WAI', 'MEBBE', 'OIC']:
                    self.statement(self.curr_token)
                while self.curr_token[VALUE] != 'OIC': self.advance()               # disregard statements that are not to be executed anymore
            elif data == 'MEBBE':
                # else if statement
                if self.eval()[VALUE] == 'WIN':
                    self.statement(self.curr_token)
                    while self.curr_token[VALUE] not in ['NO WAI', 'MEBBE', 'OIC']:
                        self.statement(self.curr_token) 
                    while self.curr_token[VALUE] != 'OIC': self.advance()
            elif data == 'NO WAI':
                # else statement
                while self.curr_token[VALUE] not in ['NO WAI', 'MEBBE', 'OIC']:
                    self.statement(self.curr_token)
                while self.curr_token[VALUE] != 'OIC':
                    self.advance()
            else:
                # advance when nothing matched
                while self.curr_token[VALUE] not in ['NO WAI', 'MEBBE', 'OIC']:
                    self.advance()             
        self.advance()
        
    def switch_case(self):
        while self.curr_token[VALUE] == '\n': self.advance()
        while self.curr_token[VALUE] != 'OIC':
            # proceed matching and evaluation until 'OIC'
            if self.advance()[VALUE] == 'OMGWTF':
                self.advance()
                while self.curr_token[VALUE] != 'OIC':
                    self.statement(self.curr_token)
            else:
                var, type = self.eval()
                self.advance()
                if var == self.get_var_details('IT')[VALUE]:
                    # evaluate each statement until switch-case keywords are encountered
                    while self.curr_token[VALUE] not in ['OMG', 'OMGWTF', 'GTFO', 'OIC']:           
                        self.statement(self.curr_token)
                    
                    while self.curr_token[VALUE] not in ['GTFO', 'OIC']:
                        if self.curr_token[VALUE] in ['OMG', 'OMGWTF']:
                            
                            while self.curr_token[VALUE] != '\n':
                                self.advance()
                            self.advance()
                        
                        if self.curr_token[VALUE] not in ['OMG', 'OMGWTF', 'OIC']:
                            self.statement(self.curr_token)
                    break
                else:
                    while self.curr_token[VALUE] not in ['OMG', 'OMGWTF', 'OIC']:
                        self.advance()
        
       
        while self.curr_token[VALUE] != 'OIC': self.advance() 
        self.advance() 

    # loop statement
    def loop(self): 
        loop_ident = self.advance()[VALUE] # get the identifier of the loop
        if self.curr_token[VALUE] in ['UPPIN','NERFIN']: # operation (the first line)
            loop_optr = self.advance()[VALUE] # get the loop operation
            self.advance() # 'YR'
            loop_var = self.advance()[VALUE] # get the loop variable
            loop_cond = self.advance()[VALUE] # get the condition
        else:
            loop_optr = None
        loop_statements = deque([])
        while not (self.curr_token[VALUE] == 'IM OUTTA YR' and self.lexemes[1][0] == loop_ident):
            loop_statements.appendleft(self.advance()) # append to the left part of the deque
        
        while True:
            self.lexemes.extendleft(loop_statements)
            self.curr_token = self.lexemes[0]
            if loop_optr != None:
                x = self.eval()
                
                # check for the loop break
                if loop_cond == 'WILE' and x[VALUE] == 'FAIL':
                    break
                elif loop_cond == 'TIL' and x[VALUE] == 'WIN':
                    break
                elif self.break_loop == True: # encountered GTFO
                    self.break_loop = False
                    break
            
            while not (self.curr_token[VALUE] == 'IM OUTTA YR' and self.lexemes[1][0] == loop_ident):
                if self.break_loop == True:
                    self.advance()          # disregard statement
                    break
                self.statement(self.curr_token)

            var_value = int(self.to_python_data(self.get_var_details(loop_var)))
            if loop_optr == 'UPPIN':
                self.add_symbol(loop_var, var_value + 1, 'NUMBR Literal')
            elif loop_optr == 'NERFIN':
                self.add_symbol(loop_var, var_value - 1, 'NUMBR Literal')

        while not (self.curr_token[VALUE] == 'IM OUTTA YR' and self.lexemes[1][0] == loop_ident):
            self.advance()
        self.advance() # pop IM OUTTA YR
        self.advance() # pop loop_ident
    
    def assignment(self, var_name):
        """used to assign a value to a variable and add variable to symbol table.

        Args:
            var_name (str): variable name
        """        
        value, type = self.eval()
        self.add_symbol(var_name, value, type)
        
    def LOLtypecast_(self, new_type, var_name=None, var_value=''):
        """casts  different type to variable

        Args:
            new_type (str): variable type to be casted
            var_name (str, optional): variable name. Defaults to None.
            var_value (any, optional): variable value. Defaults to ''.

        Returns:
            tuple: variable value and type
        """
        var_type = None
        if var_name:
            var_value = self.symbol_table[var_name][VALUE]
            var_type = self.symbol_table[var_name][TYPE]
            
            if var_type == new_type: return

        # for variables/values with NOOB type
        if var_type == 'NOOB' or var_value == None:
            if new_type in ['NUMBR', 'NUMBAR']:
                var_value = 0
            elif new_type == 'YARN':
                var_value = ''
            elif new_type == 'TROOF':
                var_value = False    

        match new_type:
            case 'NUMBR':
                var_value = int(float(var_value))
                var_type = 'NUMBR Literal'
            case 'NUMBAR':
                var_value = float(var_value)
                var_type = 'NUMBAR Literal'
            case 'YARN':
                if type(var_value) == float: # if casting from NUMMBAR to YARN
                    if(len(str(var_value).split(".")[1]) == 1): # if the decimal place is only 1
                        var_value = f'{var_value:.1f}' # format to one decimal place
                    else:
                        var_value = f'{var_value:.2f}' # else, format to two decimal places
                else: 
                    var_value = str(var_value)
                var_type = 'YARN Literal'
            case 'TROOF':
                var_value = {True: 'WIN', False: 'FAIL'}[bool(var_value)]
                var_type = 'TROOF Literal'
            
        if var_name: self.add_symbol(var_name, var_value, var_type)         # if a variable name is specificed, update variable details
        else: return (var_value, var_type)                                  # else, return resulting value and type
                
    def to_python_data(self, data):
        """convert LOLCODE data to Python data

        Args:
            data (tuple): variable value, type

        Returns:
            any: Python data
        """        """convert LOLCODE data to Python data"""
        match data[TYPE]:
            case 'NUMBR Literal'    : return int(data[VALUE])
            case 'NUMBAR Literal'   : return float(data[VALUE])
            case 'YARN Literal'     : return str(data[VALUE])
            case 'TROOF Literal'    : return {'WIN': True, 'FAIL' : False}[data[VALUE]]
            

    def to_LOL_data(self, data):
        """convert Python data to LOLCODE data

        Args:
            data (any): Python data

        Returns:
            tuple: variable value, LOLCODE type
        """        
        match str(type(data)):
            case "<class 'int'>"    : return (str(data), 'NUMBR Literal')
            case "<class 'float'>"  : return (str(data), 'NUMBAR Literal')
            case "<class 'str'>"    : return (data, 'YARN Literal')
            case "<class 'bool'>"   : return ({True : 'WIN', False : 'FAIL'}[data], 'TROOF Literal')
            

    def eval(self):
        try: 
            return self.eval_()
        except Exception as e:
            self.print_error(e)

    def eval_(self):

        # BINARY OPERATIONS
        math = {
            'SUM OF'        : lambda a, b   : a + b,
            'DIFF OF'       : lambda a, b   : a - b,
            'PRODUKT OF'    : lambda a, b   : a * b,
            'QUOSHUNT OF'   : lambda a, b   : a / b,
            'MOD OF'        : lambda a, b   : a % b,
            'BIGGR OF'      : lambda a, b   : max(a, b),
            'SMALLR OF'     : lambda a, b   : min(a, b),
        }

        compare = {
            'DIFFRINT'      : lambda a, b : a != b,
            'BOTH SAEM'     : lambda a, b : a == b
        }

        boolean = {
            'EITHER OF'     : lambda a, b : a or b,
            'WON OF'        : lambda a, b : (a or b) and (a != b),
            'BOTH OF'       : lambda a, b : a and b
        }

        # INFINITE OPERATIONS
        # * for packing arguments
        # function accepts any number of arguments
        inf_op = {
            'ALL OF': lambda *a: all(map(bool, a)),
            'ANY OF': lambda *a: any(map(bool, a)),
            'SMOOSH': lambda *a: ''.join(map(str, a))
        }

        unary = lambda a: not a
        
        if self.curr_token[VALUE] not in OPERATORS:
            if self.curr_token[TYPE] == 'Identifier':
                if self.curr_token[VALUE] in self.symbol_table.keys():
                    var_name = self.advance()[VALUE]
                    return self.get_var_details(var_name)
                else:
                    self.print_error(f"'{self.curr_token[VALUE]}' is not defined.")
                    self.app.exec_btn.configure(state='normal')
            else:
                return self.advance()

        else:
            stack = []
            while not (len(stack) == 1 and stack[0][TYPE] in LITERALS):
    
                tok = self.advance()
                
                if tok[TYPE] == 'Identifier':
                    stack.append(self.get_var_details(tok[VALUE]))
                else:
                    stack.append(tok)

                def is_binary_operation():
                    """determines if the operation is a binary operation.
                    returns: operator, operand1, and operand2
                    """
                    if len(stack) >= 4 and stack[-4][VALUE] in BINARY_OP and stack[-3][TYPE] in LITERALS and stack[-2][TYPE] == 'Conjunction' and stack[-1][TYPE] in LITERALS:
                        return stack[-4][VALUE], (self.to_python_data(stack[-3]), self.to_python_data(stack[-1]))
                
                def perform_binary_operation(operator, operands):
                    """performs binary operations: arithmetic, comparison, and boolean

                    Args:
                        operator (str)
                        operands (tuple): two operands
                    """                    
                    operands = list(operands)
                    if operator in math:
                        for i in range(2):
                            if type(operands[i]) not in [int, float]:
                                try:
                                    operands[i] = int(operands[i])
                                except ValueError:
                                    operands[i] = float(operands[i])

                        if type(operands[0]) == float or type(operands[1]) == float:
                            operands = [float(elem) for elem in operands]

                        result = math[operator](operands[0], operands[1])
                    elif operator in compare:
                        result = compare[operator](operands[0], operands[1])
                    elif operator in boolean:
                        for i in range(0,2):
                            if type(operands[i]) != bool:
                                operands[i] = bool(operands[i])
                        result = boolean[operator](operands[0], operands[1])
                    result = self.to_LOL_data(result)
                    for i in range(4): stack.pop()
                    stack.append(result)

                def perform_infinite_operation(mkay_required: bool):
                    """performs operations with infinite arity: ALL OF, ANY OF, and SMOOSH.
                    
                    Args: 
                        mkay_required (bool): determine if an MKAY keyword is required
                    """
                    if mkay_required: idx = -2
                    else: idx = -1

                    operands = []
                    while stack[idx][VALUE] not in INF_OP:
                        if stack[idx][TYPE] in LITERALS:
                            operands.append(self.to_python_data(stack[idx]))
                        stack.pop(idx) 
                    
                    operator = stack.pop(idx)[VALUE]    
                    operands.reverse()
                    result = self.to_LOL_data(inf_op[operator](*operands))
                    if mkay_required: stack.pop()
                    stack.append(result)
                    
                def maek(idx):
                    """perform typecasting

                    Args:
                        idx (int): number of tokens to read
                    """
                    expr = self.to_python_data(stack[-idx+1])
                    result = self.LOLtypecast_(stack[-1][VALUE], var_value=expr)
                    for i in range(idx): stack.pop()
                    stack.append(result)

                # OPERATION EVALUATION
                while True:
                    if len(stack) >= 2 and stack[-2][VALUE] in UNARY_OP and stack[-1][TYPE] in LITERALS:
                        result = unary(self.to_python_data(stack[-1]))
                        for i in range(2): stack.pop()
                        stack.append(self.to_LOL_data(result))
                    elif is_binary_operation() != None:
                        operator, operands = is_binary_operation()
                        perform_binary_operation(operator, operands)
                    elif len(stack) >= 5 and stack[-1][VALUE] == 'MKAY':
                        perform_infinite_operation(mkay_required=True)
                    elif len(stack) >= 4 and stack[-4][VALUE] == 'MAEK' and stack[-1][VALUE] in TYPES:
                        maek(idx=4)
                    elif len(stack) >= 3 and stack[-3][VALUE] == 'MAEK' and stack[-1][VALUE] in TYPES:
                        maek(idx=3)
                    elif len(stack) >= 2 and stack[-1][TYPE] in LITERALS and self.curr_token[VALUE] == '\n':
                        perform_infinite_operation(mkay_required=False)
                    else: break

            return stack[0]

    # dispatch  = a dictionary with callable keys
    dispatch = {
            'GIMMEH'    : get_user_input,
            'VISIBLE'   : print_output,
            'I HAS A'   : declare_var,
            'O RLY?'    : if_then,
            'WTF?'      : switch_case,
            'IM IN YR'  : loop
        }            
  
    def statement(self, data):
        """driver for the code execution.
        calls functions according to each keyword encountered

        Args:
            data (tuple): data value and type
        """        """
        """
        try:
            if data[VALUE] == '\n':
                None
            elif data[VALUE] == 'GTFO':
                self.break_loop = True
            elif data[VALUE] in self.dispatch.keys():
                cmd = self.advance()[VALUE]
                self.dispatch[cmd](self)
            elif data[VALUE] in self.symbol_table.keys() and self.lexemes[1][VALUE] == 'R':
                var = self.advance()[VALUE]
                self.advance()
                self.assignment(var)
            elif data[VALUE] in self.symbol_table.keys() and self.lexemes[1][VALUE] == 'IS NOW A':
                var =self.advance()[VALUE]
                self.advance()
                type = self.advance()[VALUE]
                self.LOLtypecast_(type, var_name=var)
            else:
                result = self.eval()
                if result:
                    self.add_symbol('IT', result[VALUE], result[TYPE])
            self.advance()       
        except Exception as e:

            if self.stop == False: self.print_error(e)     

    def print_error(self, err):
        """prints error to console

        Args:
            err (Exception): error to be printed
        
        """

        rep = {
            '()'        : '',
            'NoneType'  : 'NOOB',
            'int'       : 'NUMBR',
            'float'     : 'NUMBAR',
            'string'    : 'YARN',
            'bool'      : 'TROOF'
        }
        rep = dict((re.escape(k), v) for k, v in rep.items())
        pattern = re.compile("|".join(rep.keys()))
        err = pattern.sub(lambda m: rep[re.escape(m.group(0))], str(err))
        err = err + '\n'
        self.app.printToConsole(err)
        self.stop = True


    def add_symbol(self, name, value, type):
        """used to add variables to symbol table

        Args:
            name (str): variable name
            value (str): variable value
            type (str): variable type
        """        """used to add variables to symbol table"""
        self.symbol_table[name] = (value, type)
        self.app.updateSymbolTable()

    def advance(self):
        """used to move to the next lexeme"""
        if len(self.lexemes) != 0: 
            popped = self.lexemes.popleft()
            self.curr_token = self.lexemes[0]
            return popped
    
    def get_var_details(self, var_name):
        """returns the variable value and type

        Args:
            var_name (str): variable name

        Returns:
            tuple: variable value, variable type
        """        
        return (self.symbol_table[var_name][VALUE], self.symbol_table[var_name][TYPE])


# references:
# https://flexiple.com/python/python-switch-case/
# https://www.w3schools.com/python/ref_list_reverse.asp
# https://stackoverflow.com/questions/6116978/how-to-replace-multiple-substrings-of-a-string