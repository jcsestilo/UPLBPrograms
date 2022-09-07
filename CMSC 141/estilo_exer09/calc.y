/* References:
https://stackoverflow.com/questions/36641995/cant-make-work-multiple-assignments-in-bison-flex 
http://www-h.eng.cam.ac.uk/help/tpl/languages/flexbison/ 
https://github.com/jengelsma/yacc-tutorial */

%{
	int yylex();
	void yyerror(char *s);
	#include<math.h>
	#include<stdio.h>
	#include<stdlib.h>
	#include <ctype.h>
	double symbols[52];
	double symbolVal(char symbol);
	void updateSymbolVal(char symbol, double val);
	double val;
%}

%union {
	double number;
	char id;
}

/* %token HEXA OCTA */
%token <number> NUMBER

%left ADD SUB
%left MUL DIV
%right POWER
%left NEGATIVE
%token LEFT RIGHT
%token ASSIGN
%token EOL QUIT
%token <id> IDENTIFIER

%type <number> exp term
%type <id> assignment
%type <number> identifiers


%%

calclist:/*  nothing */
	| assegn EOL 
	| calclist assignment EOL
	| calclist exp EOL			{printf("=%f\n", $2); }
	| calclist QUIT				{printf("Exitting...\n"); return 0;}
	;

assegn : assignment
    | identifiers
    ;

identifiers : assignment { val = $1;}
    | IDENTIFIER ASSIGN identifiers {updateSymbolVal($1,val);}
    ;

assignment : IDENTIFIER ASSIGN exp  {$$=$3; updateSymbolVal($1,$3);}
    ;
exp:
	term
	| exp ADD exp				{$$ = $1 + $3;}
	| exp SUB exp				{$$ = $1 - $3;}
	| exp MUL exp				{$$ = $1 * $3;}
	| exp DIV exp				{$$ = $1 / $3;}
	| exp POWER exp				{$$ = pow($1, $3);}
	| LEFT exp RIGHT 			{$$ = ($2);}
	| SUB exp %prec NEGATIVE	{$$ = -$2;}
	;

term: NUMBER 					{$$ = $1;}
	| IDENTIFIER            	{$$ = symbolVal($1);}
	;

%%

int computeSymbolIndex(char token)
{
    int idx = -1;
    if(islower(token)) {
        idx = token - 'a' + 26;
    } else if(isupper(token)) {
        idx = token - 'A';
    }
    return idx;
}

/* returns the value of a given symbol */
double symbolVal(char symbol)
{
    int bucket = computeSymbolIndex(symbol);
    return symbols[bucket];
}

/* updates the value of a given symbol */
void updateSymbolVal(char symbol, double val)
{
    int bucket = computeSymbolIndex(symbol);
    symbols[bucket] = val;
}

int main (void) {
    /* init symbol table */
    int i;
    for(i=0; i<52; i++) {
        symbols[i] = 0;
    }

    return yyparse();
}

void yyerror (char *s) {fprintf (stderr, "%s\n", s);}