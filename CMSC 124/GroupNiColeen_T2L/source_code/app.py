from tkinter import *
from tkinter import ttk
from tkinter.filedialog import askopenfile
from lexer import *
from syntax import *
from semantic import *
import tkinter.font as tkfont

class App(Tk):
    def __init__(self):
        super().__init__()

        self.lexemes = []   # container for lexemes

        # setting the title 
        self.title("""Ganda ni Ma'am Joh""")
        
        # dimensions of the main window
        self.geometry("1520x800")

        # Editor widget
        self.text = Text(self, height=30, width=60)
        textscroll = Scrollbar(self)
        self.text.configure(yscrollcommand=textscroll.set)
        self.text.place(x=10,y=30)

        # console
        self.console = Text(self, height=13, width=187, bg='grey1', fg='white', state=DISABLED)
        console_scroll = Scrollbar(self)
        self.console.place(x=10, y=550)
        font = tkfont.Font(font=self.console['font'])
        tab = font.measure('    ')
        self.console.configure(yscrollcommand=console_scroll, tabs=tab)

        # console input
        self.has_input = StringVar()
        self.console_input = StringVar()
        self.input_entry = Entry(self, textvariable=self.console_input, width=187, background='grey1', fg='white', insertbackground='white')
        self.input_entry.bind('<Return>', self.inputDetected)
        self.input_entry.place(x=10,y=765)
        self.input_entry.pack(fill='x', side='bottom', padx=10, pady=10)

        # Open file button
        file_btn = Button(self, text ='Open', command = lambda:self.openFile(), height= 1, width=68) 
        file_btn.place(x=10,y=0)

        # lexeme table
        columns = ('lexeme', 'classification')
        self.tree = ttk.Treeview(self, columns=columns, show='headings', height= 23)
        self.tree.column("#0", stretch=True)
        self.tree.heading('lexeme', text='Lexeme')
        self.tree.heading('classification', text='Classification')
        self.tree.place(x=500, y= 30)
        
        # symbol table
        columns_symbol = ('identifier', 'value', 'type')
        self.tree_symbol = ttk.Treeview(self, columns=columns_symbol, show='headings', height= 23)
        self.tree_symbol.heading('identifier', text='Identifier')
        self.tree_symbol.heading('value', text='Value')
        self.tree_symbol.heading('type', text='Type')
        self.tree_symbol.place(x=910, y= 30)

        # Execute button
        self.exec_btn = Button(self, text='Execute', command = lambda:self.execute(), height=1, width=213)
        self.exec_btn.place(x=10, y=520)

    def execute(self):
        """essentially executes the code by running the lexer, syntax analyzer, and semantic analyzer."""
        self.clearConsole()
        self.clearLexemeTable()
        self.clearSymbolTable()
        input_text = self.text.get('1.0', 'end-1c') # get the text from the textarea
        if len(input_text) == 0: return # if there is no code
        lexer_out = run_lexer(input_text, self)

        # proceed only when the lexer does not return any error
        if not isinstance(lexer_out, LexerError):
            # analyze syntax
            valid_syntax = analyze_syntax(lexer_out, self)
            # run code if syntax is valid
            if valid_syntax == True:
                self.createLexemeTable(lexer_out)
                self.exec_btn.configure(state=DISABLED)
                self.run_code(lexer_out)
                self.exec_btn.configure(state=NORMAL)

                     
    def run_code(self, lexemes):
        """runs the code through the semantic analyzer"""
        filtered_lexemes = []
        for lexeme_pair in lexemes:

            if lexeme_pair[1] in ['Single-line Comment', 'Multi-line Comment', 'Line Delimiter']:
                filtered_lexemes.append(('\n', 'Line Delimiter'))
            elif lexeme_pair[1] == 'YARN Literal':
                string_lit = lexeme_pair[0][1:-1]
                filtered_lexemes.append((string_lit, 'YARN Literal'))
            else:
                filtered_lexemes.append(lexeme_pair)

        self.semantic_analyzer = SemanticAnalyzer(filtered_lexemes, self)
        self.semantic_analyzer.start_program()
    
    def updateSymbolTable(self):
        """displays all the variable details in the symbol table.
        variable details: name, value, type
        """
        self.clearSymbolTable()
        for key, value in self.semantic_analyzer.symbol_table.items():
            item = (key, value[0], value[1])
            self.tree_symbol.insert('', END, values=item)
    
    def createLexemeTable(self, lexemes):
        """displays all the lexemes generated by the lexer into a lexeme table"""
        for lexeme_pair in lexemes:
            if lexeme_pair[1] != 'Line Delimiter' and lexeme_pair[1] != 'YARN Literal':
                self.tree.insert('', END, values=lexeme_pair)
            if lexeme_pair[1] == 'YARN Literal':
                self.tree.insert('', END, values=('\"', 'Starting String delimiter'))
                string_lit = lexeme_pair[0][1:-1]
                self.tree.insert('', END, values=(string_lit, 'YARN Literal'))
                self.tree.insert('', END, values=('\"', 'Ending String delimiter'))

    def openFile(self):
        """opens a file and reads and stores the content"""
        file = askopenfile(mode = 'r', filetypes =[('LOLCODE Files', '*.lol')]) 
        if file is not None:
            self.text.delete('1.0', END)
            self.clearLexemeTable()
            self.clearSymbolTable()
            content = file.read() 
            self.text.insert(INSERT, content)
    
    def clearLexemeTable(self):
        """clears all the value previously inserted in the lexeme table"""
        for item in self.tree.get_children():
            self.tree.delete(item)
    
    def clearSymbolTable(self):
        """clears all the value previously inserted in the symbol table"""
        for item in self.tree_symbol.get_children():
            self.tree_symbol.delete(item)
    
    def printToConsole(self, text):
        """prints text to console"""
        self.console.configure(state=NORMAL)
        self.console.insert(INSERT, text)
        self.console.configure(state=DISABLED)
        self.console.see(END)

    def clearConsole(self):
        self.console.configure(state='normal')
        self.console.delete('1.0', END)
        self.console.configure(state='disabled')

    def inputDetected(self, event=None):
        """stores the input detected from the console input widget once the return key is pressed"""
        self.has_input.set(self.console_input.get())
        if self.console_input.get() == 'cls': 
            self.clearConsole()
        self.input_entry.delete(0, END)
        if self.has_input.get() != 'cls': self.printToConsole(f'{self.has_input.get()}\n')
        

    def getConsoleInput(self):
        """waits for an input from the user and returns the value of the input detected"""
        self.printToConsole('>> ')
        self.wait_variable(self.has_input)
        input_ = self.has_input.get()
        if input_ != '': return input_
        
if __name__ == '__main__':
    app = App()
    app.printToConsole('Welcome to LOLCODE Interpreter!')
    app.mainloop()
 

# Reference(s):
# https://github.com/huzaifamaw/Lexical_Analyzer-Parser_Implemented-in-Python/tree/407fdbb636e326237139827f512444329b1f8cfc
# https://flexiple.com/python/python-switch-case/
# https://www.geeksforgeeks.org/use-yield-keyword-instead-return-keyword-python/
# https://github.com/cyronquillo/lolcode-interpreter
# https://www.w3schools.com/python/ref_list_reverse.asp
# https://stackoverflow.com/questions/6116978/how-to-replace-multiple-substrings-of-a-string
# https://docs.python.org/3/library/tkinter.html
# https://www.youtube.com/watch?v=LCslqgM48D4
# https://stackoverflow.com/questions/54394324/how-do-you-adjust-the-tab-size-when-using-python-tkinter-library
# https://www.tutorialspoint.com/how-to-use-unicode-and-special-characters-in-tkinter
