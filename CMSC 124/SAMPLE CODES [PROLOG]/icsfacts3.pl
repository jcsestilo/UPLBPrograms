happy(dja).
playsDota(clinton).
playsDota(dja).
winsDotaGame(dja) :- happy(dja),
					 playsDota(dja).
winsDotaGame(clinton) :- happy(clinton);
						playsDota(clinton).

