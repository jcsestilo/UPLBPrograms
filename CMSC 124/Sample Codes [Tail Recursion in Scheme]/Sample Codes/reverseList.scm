;Recursive Function for Reversing a List
(define (reversed list1)
	(if (null? list1) 
		'() ;Base Case
		(append (reversed (cdr list1)) (list (car list1))) ;Recursive Case
	)
)

;Tail-Recursive Helper-Function for Reversing a List
(define (reversed1 list1 acc) ;Accumulator
	(if (null? list1) 
		acc ;Base Case
		(reversed1 (cdr list1) (cons (car list1) acc)) ;Recursive Case
	)
)

;Tail-Recursive Main-Function for Reversing a List
(define (rev list1)
	(reversed1 list1 '()) ;Initialization
)