(define (computeDist pair1 list1 acc)
  (if(null? list1)
  	acc
  	(computeDist (car list1) (cdr list1) (+ acc sqrt(+ expt((- (cdr (car list1)) (car pair1)) 2) expt((- (cdr (cdr list1)) (car pair1)) 2))))
  )
)

(define (computeDistance list1)
  (computeDist (car list1) (cdr list1) 0) ;Initialization
)