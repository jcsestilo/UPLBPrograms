; Jan Coleen S. Estilo
; CMSC 124 - T2L

(define (computeDist pair1 list1 acc)
  (if(null? list1)
  	acc ; base case
    ; for every recursion, pair1 is the first element. accumulator is the summation of the distance formula of pair1 and first element in list1
  	(computeDist (car list1) (cdr list1) (+ acc (sqrt (+ (expt (- (car (car list1)) (car pair1)) 2) (expt (- (cdr (car list1)) (cdr pair1)) 2)))))
  )
)

(define (computeDistance list1)
  (computeDist (car list1) (cdr list1) 0) ;Initialization
)
