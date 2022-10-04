;Recursive Function for Computing Factorial
(define (factorial n)
	(if (< n 1)
		1 ;Base Case
		(* n (factorial (- n 1))) ;Recursive Case
	)
)

;Tail-Recursive Helper-Function for Computing Factorial
(define (factorial1 n acc) ;Accumulator
	(if (< n 1) 
		acc ;Base Case
		(factorial1 (- n 1) (* n acc)) ;Recursive Case
	)
)

;Tail-Recursive Main-Function for Computing Factorial
(define (fact n) 
	(factorial1 n 1) ;Initialization!
)