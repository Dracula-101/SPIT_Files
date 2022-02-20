function matrix=sumMatrix(A)
    
    //finding the length of the matrix a
    length = size(A)
    
    //Getting the 1row and 1column
    len = length(1,1)
    
    //Constructing a identity matrix of len x len
    B=eye(len,len)
    B = 2*%i*B
    
    //returning the matrix with the sum
    matrix = A+B;

endfunction;
   
printf("\n--------------------------------\n") 
A=input("Enter a Matrix: ")
printf("\n--------------------------------\n")
disp(sumMatrix(A))
printf("\n--------------------------------\n")
