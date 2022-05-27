%{
	int numLines=0, numWords=0;
%}

word 	[^[:space:]]+

%%
\n 			{++numLines;}
{word}		{++numWords;}
[[:space:]]	{}
%%

void main(){
	yylex();
	printf("Lines: %d\n", numLines);
	printf("Words: %d\n", numWords);
}