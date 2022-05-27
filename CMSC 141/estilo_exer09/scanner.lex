%{
    #include "calc.tab.h"
    #include<stdlib.h>
    // #define YYSTYPE double
%}

NUMBER [0-9]+(\.[0-9]+)?
SPACE [[:space:]]
IDENTIFIER [a-zA-Z]

%%
"+"			{return ADD;}
"-"			{return SUB;}
"/"			{return DIV;}
"*"			{return MUL;}
"^"         {return POWER;}
"("         {return LEFT;}
")"         {return RIGHT;}
"="         {return ASSIGN;}
\n          {return EOL;}
{NUMBER}	{yylval.number = atof(yytext); return NUMBER;}
{IDENTIFIER} {yylval.id=yytext[0]; return IDENTIFIER;}
{SPACE}     {/*ignore white space*/}
"exit"		{return QUIT;}
.           {printf("\nUnrecognized string %c\n", *yytext);}
%%
/* "O"         {return OCTA;}
"H"         {return HEXA;} */