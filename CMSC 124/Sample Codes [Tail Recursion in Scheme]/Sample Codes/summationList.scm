;Recursive Function for Summation of a List
(define (list-sum list1)
	(if (null? list1)
		0 ;Base Case
		(+ (car list1) (list-sum (cdr list1))) ;Recursive Case
	)
)

;Tail-Recursive Helper-Function for Summation of a List
(define (summation list1 acc) ;Accumulator
	(if (null? list1)
		acc ;Base Case
		(summation (cdr list1) (+ acc (car list1))) ;Recursive Case
	)
)

;Tail-Recursive Main-Function for Summation of a List
(define (summ list1)
	(summation list1 0) ;Initialization
)