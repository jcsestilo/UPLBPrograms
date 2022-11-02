loves(kei, bulacs).
loves(ippo, bulacs).
jealous(X, Y) :- loves(X, Z), loves(Y, Z).
jealous(_X, _Y, _Z).