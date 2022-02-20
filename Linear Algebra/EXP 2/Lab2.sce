x = input("Enter a number: ")
if x>0 then
    disp("The number is positive")
else
   disp("The number is negative")
 
end


size=input("Enter the matrix size :")

size =3

for i = 1:size
    for j = 1:size
        if i==j then 
            a(i,j)=1
        else
            a(i,j)=0;
        
        end
    end
end
disp(a)
