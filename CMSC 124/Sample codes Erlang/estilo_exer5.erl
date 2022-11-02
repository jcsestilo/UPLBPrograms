% Jan Coleen S. Estilo
% CMSC 124 - T2L

-module(estilo_exer5).
-export([checking/2, compositeOrPrime/1, binomialCoeffForLoop/4, binomialCoeff/2, numberIterator/3, lineIterator/2,pascalTriangle/1, main/0]). 

checking(1) -> io:fwrite("1 - prime~n", []); % if 1, it is prime
checking(H) -> checking(H,2). % when we call function, it will call the function with 2 arities
checking(H,H) -> io:fwrite("~B - prime~n", [H]); % if we reach the max
checking(H,E) ->
   case (H rem E) =:= 0 of % if element remainder number == 0, means it is composite
      true -> io:fwrite("~B - composite~n", [H]); % composite
      _ -> checking(H,E+1) % increment num that will mod
   end.

compositeOrPrime([]) -> ok;
compositeOrPrime([H|T]) -> % iterate through the list
   checking(H),
   compositeOrPrime(T).

% ------------------------------------------------
% https://www.geeksforgeeks.org/pascal-triangle/ 

binomialCoeffForLoop(K, K, Res, _) -> io:fwrite("~B ", [Res]);
binomialCoeffForLoop(I, K, Res, N) ->
   NewRes = Res * (N - I),
   NewRes2 = NewRes div (I + 1),
   binomialCoeffForLoop(I+1, K, NewRes2, N).

binomialCoeff(N, K) ->
   Res = 1, % the default number is 1
   NMinusK = N - K,
   if
      K > NMinusK -> 
         NewK = NMinusK,
         binomialCoeffForLoop(0, NewK, Res, N);
      true -> binomialCoeffForLoop(0, K, Res, N)
   end.


numberIterator(Number, Number, _) -> io:fwrite("~n"); % print the space 
numberIterator(Number, M, Line) -> % this is the inner for loop
   binomialCoeff(Line, Number),
   numberIterator(Number+1, M, Line).


lineIterator(N, N) -> ok; 
lineIterator(Line, N) -> % this is the bigger for loop
   numberIterator(0, Line+1, Line),
   lineIterator(Line+1, N).

pascalTriangle(0) -> io:fwrite("1~n");
pascalTriangle(N) when N > 0 -> 
   lineIterator(0, N). % we will loop per line
   
main() -> 
   io:fwrite("~nPASCAL'S TRIANGLE~n"),
   pascalTriangle(7),
   io:fwrite("~nCOMPOSITE OR PRIME~n"),
   X = [3, 6, 9, 10, 2],
   compositeOrPrime(X).