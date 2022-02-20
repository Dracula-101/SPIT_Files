A=[1,2,7;  4,5,12;  6,0,8]

//printf(" %d ",size('r',A))
flag=0

for i=1:3
    for j=1:3
        if A(i,j)==0 then
            flag=1
            break;
        else
            disp(A(i,j))
            
        end
    end
    if flag==1 then
        break;
        
    end
end    
