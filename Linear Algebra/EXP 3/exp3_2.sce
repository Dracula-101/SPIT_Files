function Matrix = inputM(M,N)
    
    for i= 1:M
        printf("For Equation %d\n", i);
        for j= 1:N
            if j==N
                Matrix(i,j)=input("Enter value of constant: ")
            else
                printf("Enter coefficient of x%d",j)
                Matrix(i,j)= input("Coefficient: ")
            end
        end
    end
endfunction

function Matrix = swaprows(M,i)
    B=M(i,:)
    M(i,:)=M(i+1,:)
    M(i+1,:)=B
    
    Matrix=M
endfunction

M=input("Enter Number of equations: ")
N=input("Number of variables: ")
printf("Let the %d variables be:  ",N)
for i=1:N
    printf("X%d  ",i)
end
printf("\n\n")
N=N+1
LHS=[]
A=[]
Matrix=inputM(M,N)

for i = 1 : M
   for j= 1 :N-1
      A(i,j)= Matrix(i,j)
   end 
end

for i=1:M
    LHS(i)=Matrix(i,N)
end

printf("\nMatrix A\n")
disp(A)
printf("\nMatrix C\n")
disp(LHS)
printf("\nAugmented Matrix: \n")
disp(Matrix)

if rank(A)==rank(Matrix) then
    printf("Given System of equation is consistent/nHence a unique solution exists\n")
    
        for i=1:M
          if Matrix(i,i) == 0
            
                Matrix = swaprows(Matrix,i)
                disp(Matrix)   
          end
                
          if Matrix(i,i) ~= 1
                printf("\nDivide row %f by %f\n", i , Matrix(i,i))
                Matrix(i,:)=Matrix(i,:)/Matrix(i,i)
          end
          for j=i+1:M
              Matrix(j,:)=Matrix(j,:)-Matrix(j,i)*Matrix(i,:) 
          end  
          disp(Matrix)
        end
        
        printf("\nRow reduced Echelon form: \n")
        disp(Matrix)
        ans=[]
        
        i=M
        while i>=1
            ans(i)=Matrix(i,N)
            j=N-1
            while j>i 
                ans(i)=double(ans(i))-double(Matrix(i,j)*ans(j))
                j=j-1
            end
            i=i-1
        end
        
        printf("\nSolution is:\n")
        for x=1:M
            printf("X%d = %lf\n",x,ans(x))
        end
else
    printf("No solution for the given system of equations")
end

[2,1,1; 1,1,1; 1,-1,2]
