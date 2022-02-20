//Declaring the matrix
A=[1,2,3,0;  2,4,3,2;  3,2,1,3;  6,8,7,5]

//Displaying the matrix
disp('The matrix A is :')
disp(A)

//First Operation
A(2,:)=A(2,:)-2*A(1,:)

disp('R2-> R2-2*R1')
disp(A)

//Second Operation
A(3,:)=A(3,:)-3*A(1,:)

disp('R3-> R3-3*R1')
disp(A)

//Third Operation
A(4,:)=A(4,:)-6*A(1,:)

disp('R4-> R4-4*R1')
disp(A)

//Fourth Operation
C=A(2,:)
A(2,:)=A(3,:)
A(3,:)=C

disp('R2->R3')
disp(A)

//Fifth operation
A(2,:)=A(2,:)/(-4)

disp('R2-> R2/(-4)')
disp(A)

//Sixth Operation
A(4,:)=A(4,:)+4*A(2,:)

disp('R4-> R4+4*R2')
disp(A)

//Seventh Operation
A(3,:)=A(3,:)/(-3)

disp('R3-> R3/(-3)')
disp(A)

//Eighth Operation
A(4,:)=A(4,:)+3*A(3,:)

disp('R4-> R4+3*R3')
disp(A)
