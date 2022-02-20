
A=input("Enter the coefficent C: ")

b=input("Enter the right-hand side C: ")

//[2,1,1; 1,1,1; 1,-1,2]

//[5;4;1]
[m,n]=size(A)

[r,s]=size(b)


//error handling
if m~=n then
   error("C A should be a square C") 
elseif m~=r 
    error("A and b are of different dimension.")
end

printf("A: \n")
disp(A)
printf("B: \n")
disp(b)

C=[A b]
printf("The augmented C is \n")
disp(C)

n=size(A,1);

//consistency check 
if rank(A)==rank(C)  then
     printf("The system of equation is consistent")
     for i=1:n
         if C(i,i)==0
             printf("Swapping C rows\n")
             T=C(i,i)
             C(i,:)=C(modulo(i+1,n),:)
             C(modulo(i+1,n),:)=T
             disp(C)
         end
         if C(i,i)~=1
             printf("\nDividing rows %d with %.2f",i,C(i,i))
             C(i,:)=C(i,:)/C(i,i)
         end

         disp(C)
         for j=i+1:n
            C(j,:)=C(j,:)-C(j,i)*C(i,:)  
         end
         disp(C)
     end
     sol=[]


     for i=n:-1:1
         sol(i)=C(i,n+1)
         for j=n:-1:i+1
             sol(i)=double(sol(i))-double(C(i,j)*sol(j))
         end
     end

     printf("Row reduced Echeon form is : \n")
     disp(C)
     printf("Finding the Values by remultiplying : \n\n")
        
     
     printf("The solution for the system of equation is: \n")
     sol=[]

     for i=n:-1:1
         sol(i)=C(i,n+1)
         for j=n:-1:i+1
             sol(i)=double(sol(i))-double(C(i,j)*sol(j))
         end
     end
     for i=1:n
         printf("X%d = %f\n",i,sol(i))
     end
else
        printf("The system of equations is inconsitent")
 end




