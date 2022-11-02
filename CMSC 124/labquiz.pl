muggle(lily).
wizard(james).
wizard(molly).
wizard(arthur).
wizard_relative(_WIZARD1, _WIZARD2).
wizard(MUGGLE, WIZARD) :- wizard(WIZARD);
			muggle(MUGGLE), wizard(WIZARD).
