A=[2,-2,1; 6,-6,3;  12,-12,6]

b=[1;3;6]

[x,kerA]=linsolve(A,b)

if isempty(x) then
    printf("System of linear equation has no solution ")
elseif isempty(kerA)
        printf("System of linear equation has only one solution.It is given by:  ")
        disp(x);
        printf("The vector n is :")
        disp(kerA)
else 
        printf("The system of linear equation has infinite solutions")
        disp(x);
        printf("The vector n is :")
        disp(kerA)   
end

