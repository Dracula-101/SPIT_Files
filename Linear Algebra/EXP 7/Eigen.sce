printf("\n")

A=input("Enter the matrix: ")

[x,R]=spec(A)
[x1,y1]=size(A)
b=zeros(x1,1)
printf("The eigen values are\n")
for i=1:x1
    printf(" %d, ",R(i,i))
end
[m,n]=size(R)


function x=calAM(R,x1)
    occ=zeros(x1,1)
    for i=1:x1
        for j=1:x1
            if R(i,i) == R(j,j)
                occ(i,1)=occ(i,1)+1
            end  
        end
    end
    for i=1:x1
        for j=1:x1
            if occ(i,1)>occ(j,1)
                x =occ(i,1)
             end 
         end
    end  
endfunction

for i=1:m
 eigenVal=R(i,i);
 [X, n1] = linsolve(A - R(i,i)*eye(m,m),b)
 printf("\n\nEigen vectors for eigen value %d",eigenVal)
 disp(n1)
 [row, col] = size(n1)
    printf("\nGeometric multiplicity is: %d\n", col)
    c=calAM(R,x1)
    printf("Algebraic multiplicity is: %d\n",c )
    if col == c
        printf("Matrix is Diagonisable")
    else
        printf("Matrix is Non Diagonisable")
    end     
end






