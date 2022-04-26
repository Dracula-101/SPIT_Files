#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>
#include <time.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <stdbool.h>
int main()
{
    key_t key = 1000;
    int sh_id = shmget(key, 50 * sizeof(int),
    IPC_CREAT | 0777);
    int *sh = (int *)shmat(sh_id, NULL, 0);
    sem_t *p = sem_open("r", 0);
    sem_wait(p);
    int n, c = 0;
    printf("Enter the number of elements:");
    scanf("%d", &n);
    printf("\n");
    while (1)
    {
        if (sh[c] == -1)
        {
            break;
        }
        else
        c++;
    }
    for (int i = 0; i < n; i++)
    {
        scanf("%d", &sh[c + i]);
    }
    sh[c + n] = -1;
    sem_post(p);
}
