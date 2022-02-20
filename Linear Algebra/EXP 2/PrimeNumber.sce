x=input("Enter any number: ")
flag=0
for i = 2:sqrt(x) 
    if(modulo(x,i)==0) then 
        flag=1
        break 
    end
end 
if(flag==1) then 
    disp("Not prime number")
    
else disp("Prime number")
    
end
