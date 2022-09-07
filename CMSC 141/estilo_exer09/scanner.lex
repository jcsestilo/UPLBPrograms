%{
    #include "calc.tab.h"
    #include<stdlib.h>
    // #define YYSTYPE double
%}

NUMBER [0-9]+(\.[0-9]+)?
SPACE [[:space:]]

HEXA H[A-F0-9]+
OCTA O[0-7]+

%%
"+"			{return ADD;}
"-"			{return SUB;}
"/"			{return DIV;}
"*"			{return MUL;}
"^"         {return POWER;}
"("         {return LEFT;}
")"         {return RIGHT;}
"="         {return ASSIGN;}
{HEXA}      {yylval.number = strtol(&yytext[1], NULL, 16); return NUMBER;}
{OCTA}      {yylval.number = strtol(&yytext[1], NULL, 8); return NUMBER;}
\n          {return EOL;}
[a-z]       {yylval.id = yytext[0]; return IDENTIFIER;}
{NUMBER}	{yylval.number = atof(yytext); return NUMBER;}
{SPACE}     {/*ignore white space*/}
"exit"		{return QUIT;}
.           {printf("\nUnrecognized string %c\n", *yytext);}
%%

int yywrap (void) {return 1;}