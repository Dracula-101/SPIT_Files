     sol=[]

     i=n
     while i>=1
         sol(i)=C(i,n+1)
         j=n
         while j>i 
             sol(i)=double(sol(i))-double(C(i,j)*sol(j))
             j=j-1
         end
         i=i-1
     end
