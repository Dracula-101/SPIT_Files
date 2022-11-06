# Question

# Given a list [a,a,a,a,b,b,b,c,c]. Write a function that does the following
# rle([a,a,a,a,b,b,c,c],X)
# X: [a,b,c]


% comments on the question
rle([],[]).
% base case: empty list
rle([P],[P]).
% base case: list with one element
rle([P,P|Px],Rx) :- rle([P|Px],Rx).
% if the first two elements are the same, remove the first one
rle([P,Q|Qx],[P|Rx]) :- P \= Q, rle([Q|Qx],Rx).
% if the first two elements are different, remove the first one and call the function recursively on the rest of the list


