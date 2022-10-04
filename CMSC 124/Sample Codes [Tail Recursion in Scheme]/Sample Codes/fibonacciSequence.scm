;Recursive Function for Fibonacci Sequence
(define (fibonacciSequence n)
	(if (< n 2)
		n ;Base Case
		(+ (fibonacciSequence(- n 1)) (fibonacciSequence(- n 2))) ;Recursive Case
	)
)

;Tail-Recursive Helper-Function for Fibonacci Sequence
(define (fibonacciSequence1 n prev curr) ;Accumulators
	(if (= n 1)
		curr ;Base Case
		(fibonacciSequence1 (- n 1) curr (+ prev curr)) ;Recursive Case
	)
)

;Tail-Recursive Main-Function for Fibonacci Sequence
(define (fibo n)
	(fibonacciSequence1 n 0 1) ;Initialization
)