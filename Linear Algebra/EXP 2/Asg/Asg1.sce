A=input("Enter a Matrix: ")
length=size(A)
len=length(1,1)

printf("\n-----------------------------------------\n")
asymmetric =0
for i=1:len
    for j=1:len
        if A(i,j)~=A(j,i) then 
            asymmetric=1
        end
    end 
end

if asymmetric==1 then
    printf("\nGiven Matrix is not symmetric\n")
else
    printf("\nGiven Matrix is symmetric\n")
    
    for i=1:len
        for j=1:len
            if i~=j
                A(i,j) = -A(j,i)
            else
                A(i,j)=0
            end
        end
    end
    printf("\n****************************************\n")
    printf("The skew symmetric matrix is:\n")
    disp(A)
end

printf("\n-----------------------------------------\n")

skew =0
for i=1:len
    for j=1:len
        if A(i,j)~=-A(j,i) then 
            skew=1
        end
    end 
end

if skew==1 then
    printf("Given Matrix is not skew symmetric\n")
else
    printf("Given Matrix is skew symmetric\n")
end

printf("\n-----------------------------------------\n")

determinant = det(A)

if determinant==0 then
    printf("Matrix is singular\n")
else
    printf("Matrix is non-singular\n")
    printf("\n********************************************\n")
    disp("The inverse of the given matrix is:")
    disp(inv(A))

end
printf("\n-----------------------------------------\n")
