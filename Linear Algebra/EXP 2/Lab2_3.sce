//Functions 

function s=distance(u,a,t)
    s=u*t+0.5*a*t^2;
endfunction

u=1
a=2
t=1


//printf("The distance is %d",distance(u,a,t))

//Code for distance at t=1:5

for t=1:5
    printf("\nThe distance at time :%d ts %d",t,distance(u,a,t))
    t=t+1
end


//Function Defination for single lines


deff('s=distance(t)','s=u*t+0.5*a*t^2')