#include <stdio.h>


int waitingtime(int proc[], int n,int burst_time[], int wait_time[]) {

   wait_time[0] = 0;

   for (int i = 1; i < n ; i++ )
   wait_time[i] = burst_time[i-1] + wait_time[i-1] ;
   return 0;
}

int turnaroundtime( int proc[], int n,int burst_time[], int wait_time[], int tat[]) {

   int i;
   for ( i = 0; i < n ; i++)
   tat[i] = burst_time[i] + wait_time[i];
   return 0;
}


int avgtime( int proc[], int n, int burst_time[]) {
   int wait_time[n], tat[n], total_wt = 0, total_tat = 0;
   int i;

   waitingtime(proc, n, burst_time, wait_time);

   turnaroundtime(proc, n, burst_time, wait_time, tat);

   printf("Processes\tBurst\tWaiting\tTurn around \n");

   for ( i=0; i<n; i++) {
      total_wt = total_wt + wait_time[i];
      total_tat = total_tat + tat[i];
      printf(" %d\t\t %d\t %d\t %d\n", i+1, burst_time[i], wait_time[i], tat[i]);
   }
   printf("\nAverage waiting time = %.2f\n", (float)total_wt / (float)n);
   printf("\nAverage turn around time = %.2f\n", (float)total_tat / (float)n);
   return 0;
}

int main() {

   printf("Enter the number of the process to be executed: ");
   int size;
   scanf("%d",&size);
   int proc[size] ;

   for(int i=0;i<size;i++){
	proc[i]=i+1;

   }

   int n = sizeof proc / sizeof proc[0];
   printf("\n-----------------------------------");
   printf("\t\t\nBurst Time\n");
   int burst_time[size];
   for(int i=0;i<size;i++){
      	printf("Enter the burst time (%d): ",(i+1));
     	scanf("%d",&burst_time[i]);
   }
   avgtime(proc, n, burst_time);
   return 0;
}
