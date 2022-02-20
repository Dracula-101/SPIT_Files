A=[1,1,1; 1,2,3;  1,4,9]
B=[3; 4; 6]

C=[A B]

if rank(C)==rank(A) then
    printf("The system is consistent\n")
    C(2,:)=C(2,:)-C(1,:)
    disp(C)

    C(3,:)=C(3,:)-C(1,:)
    disp(C)

    C(3,:)=C(3,:)-3*C(2,:)
    disp(C)
    printf("The rank of matrix A is %d and C is %d",rank(A),rank(C))
    
    z=C(3,4)/C(3,3)
    
    y=(C(2,4)-z*C(2,3))/C(2,2)
    
    x=(C(1,4)-y*C(1,2)-z*C(1,3))/C(1,1)
    
    printf("\nThe value of x,y,z is %d,%d,%d",x,y,z)

else
    disp("THe system is inconsitent")

end
