#include<stdio.h>
int get_min(int a, int b)
{
     if(a<b)
        return a;
     return b;
}
int minimum_time_required(int reach[][3],int spent[][4], int *entry, int *exit, int n)
{
     int t1[n], t2[n], i;
     t1[0] = entry[0] + spent[0][0];
     t2[0] = entry[1] + spent[1][0];
     for(i = 1; i < n; i++)
     {
         t1[i] = get_min(t1[i-1]+spent[0][i], t2[i-1]+reach[1][i-1]+spent[0][i]);
         t2[i] = get_min(t2[i-1]+spent[1][i], t1[i-1]+reach[0][i-1]+spent[1][i]);
     }
     return get_min(t1[n-1]+exit[0], t2[n-1]+exit[1]);
}
int main()
{
    int time_to_reach[2][3] = {{17, 2, 7}, {19, 4, 9}};
    int time_spent[2][4] = {{6, 5, 15, 7}, {5, 10, 11, 4}};
    int entry_time[2] = {8, 10};
    int exit_time[2] = {10, 7};
    int num_of_stations = 4;
    int ans = minimum_time_required(time_to_reach, time_spent, 
              entry_time, exit_time, num_of_stations);
    printf("%d",ans);
    return 0;
}