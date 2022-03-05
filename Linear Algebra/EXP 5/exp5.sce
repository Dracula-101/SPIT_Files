printf("\n")

A=input("Enter the coefficents: ")

b=input("Enter the right-hand side C: ")
C=[A b]

function display(x,y,z,tempx,tempy,tempz)

    printf("  Var\t|  Initial\t|  After\t|")
    printf("\n-----------------------------------------")
    printf("\n   X\t|  %f\t|  %f\t|",x,tempx)
    printf("\n   Y\t|  %f\t|  %f\t|",y,tempy)
    printf("\n   Z\t|  %f\t|  %f\t|",z,tempz)
endfunction

function [x,y,z]=solve(matrix)
    x=0
    y=0
    z=0
    limit=0.000001
    steps=100
    for i=1:steps


        tempx=(C(1,4)-C(1,2)*y-C(1,3)*z)/C(1,1)
        tempy=(C(2,4)-C(2,1)*x-C(2,3)*z)/C(2,2)
        tempz=(C(3,4)-C(3,1)*x-C(3,2)*y)/C(3,3)

        diffX=tempx-x;
        diffY=tempy-y;
        diffZ=tempz-z;



        if (abs(diffX)<=limit && abs(diffY)<=limit && abs(diffZ)<=limit)
            break
        end
        printf("\n\n-->Step %d\n",i)
        display(x,y,z,tempx,tempy,tempz)
        x=tempx;
        y=tempy;
        z=tempz;
    end


endfunction

[x,y,z]=solve(C)
printf("\n\nFinal result:\n\n  X=> %f Y=> %f Z=> %f\n",x,y,z)   
