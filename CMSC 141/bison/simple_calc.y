%{
	#include<stdio.h>
	int yylex();
	void yyerror(char *s);	
%}

%token NUMBER
%token ADD SUB MUL DIV
%token EOL QUIT

%%
calclist: /*nothing*/
	| calclist exp EOL			{printf("=%d\n", $2); }
	| calclist QUIT				{printf("Exitting...\n"); return 0;}
	;

exp: factor
	| exp ADD factor			{$$ = $1 + $3;}
	| exp SUB factor			{$$ = $1 - $3;}
	;

factor: term
	| factor MUL term			{$$ = $1 * $3;}
	| factor DIV term			{$$ = $1 / $3;}
	;

term: NUMBER
	;
%%

void yyerror(char *s){
	fprintf(stderr, "error: %s\n", s);
}

int main(int argc, char **argv){
	yyparse();
}
