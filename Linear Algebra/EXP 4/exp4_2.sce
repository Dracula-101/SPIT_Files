A=input("Enter the coefficents: ")

b=input("Enter the right-hand side C: ")

[m,n]=size(A)

[r,s]=size(b)

C=[A b]

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


for i=1:n
    for j=i+1:n
        C(i,:)=C(i,:)-C(i,j)*C(j,:)
    end
end
disp(C)
     
for i=1:n
    for j=1:n
        if i==j
            printf("X%d = %d\n",i,C(i,n+1))
        end
    end
end



