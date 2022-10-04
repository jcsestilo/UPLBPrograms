;Recursive Function for Multiplication by Repeated Addition
(define (repeatedAddition x y)
	(if (= y 1)
		x ;Base Case
		(+ x (repeatedAddition x (- y 1))) ;Recursive Case
	)
)

;Tail-Recursive Helper-Function for Multiplication by Repeated Addition
(define (repeatedAddition1 x y acc) ;;Accumulator
	(if (= y 0)
		acc ;Base Case
		(repeatedAddition1 x (- y 1) (+ x acc)) ;Recursive Case
	)
)

;Tail-Recursive Main-Function for Multiplication by Repeated Addition
(define (repAdd x y)
	(repeatedAddition1 x y 0) ;Initialization!
)