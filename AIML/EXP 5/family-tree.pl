/*Facts*/

/* Males */
male(mahesh).
male(sunil).
male(anil).
male(pranay).
male(bhavesh).

/* Females */
female(kareena).
female(karisma).
female(ravina).
/* Parents */
parent(mahesh,sunil).
parent(mahesh,anil).
parent(mahesh,pranay).
parent(kareena,sunil).
parent(kareena,anil).
parent(kareena,pranay).
parent(sunil,bhavesh).
parent(sunil,ravina).
parent(karisma,bhavesh).
parent(karisma,ravina).

/*Rules*/
father(X,Y) :- male(X),parent(X,Y).
mother(X,Y) :-female(X),parent(X,Y).
son(X,Y) :- male(X),parent(Y,X).
daughter(X,Y) :- female(X),parent(Y,X).
brother(X,Y) :- son(X,Z),son(Y,Z).
sister(X,Y) :- daughter(X,Z),daughter(Y,Z).

grandfather(X,Y) :- father(X,Z), parent(Z,Y).
grandmother(X,Y) :- mother(X,Z),parent(Z,Y).
grandson(X,Y) :- son(X,Z),(son(Z,Y) ; daughter(Z,Y)).
granddaughter(X,Y) :- daughter(X,Z),(son(Z,Y) ; daughter(Z,Y)).

uncle(X,Y) :- brother(X,Z),parent(Z,Y).
aunt(X,Y)  :- sister(X,Z),parent(Z,Y).

nephew(X,Y) :- son(X,Z),(sister(Z,Y) ; brother(Z,Y)).
niece(X,Y) :- daughter(X,Z), (sister(Z,Y) ; brother(Z,Y)).
cousin(X,Y) :- parent(Z,X),(uncle(Z,Y);aunt(Z,Y)).

predecessor(X,Y) :- parent(X,Y).
predecessor(X,Y) :- parent(Z,Y),predecessor(X,Z).
successor(X,Y) :- son(X,Y).
successor(X,Y) :- daughter(X,Y).
successor(X,Y) :- (son(Z,Y);daughter(Z,Y)),successor(X,Z).

fatherSuccessorGrandfather(X) :- father(Y,X),father(Z,Y),successor(Y,Z).
motherFemale(X):-parent(Z,X),female(Z).







