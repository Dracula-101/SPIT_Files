#include <stdio.h>
#include <semaphore.h>
#include <sys/wait.h>
#include <sys/shm.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <error.h>
#include <unistd.h>
#define SHMSZ 27
void swap(int *a, int *b)
{
    int temp = *a;
    *a = *b;
    *b = temp;
}
void sort(int arr[], int n)
{
    int i, j;
    for (i = 0; i < n - 1; i++)
    {
        for (j = 0; j < n - i - 1; j++)
        {
            if (arr[j] > arr[j + 1])
                swap(&arr[j], &arr[j + 1]);
        }
    }
}
int main()
{
    key_t key = 1000;
    sem_unlink("r");
    sem_t *r = sem_open("r", O_CREAT | O_EXCL, 0660, 0);
    if (r == SEM_FAILED)
    {
        perror("count error");
        exit(EXIT_FAILURE);
    }
    int sh_id = shmget(key, 50 * sizeof(int), IPC_CREAT | 0777);
    int *sh = (int *)shmat(sh_id, NULL, 0);
    sem_wait(r);
    sem_post(r);
    sleep(2);
    sem_wait(r);
    int c = 0;
    while (1)
    {
        if (sh[c] == -1)
        {
            break;
        }
        else
        c++;
    }
    sort(sh, c);
    printf("After sorting\n");
    for (int i = 0; i < c; i++)
    {
        printf("%d ", sh[i]);
    }
    printf("\n");
    sem_close(r);
}
