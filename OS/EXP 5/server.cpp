#include<stdio.h>
#include<stdlib.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<netinet/in.h>
 
int main()
{
    /*FILE * fp;char ch[5000];
    fp=fopen("lesson1.txt","r");
    fgets(ch,5000,fp);*/
 
    char ch[1024];
 
    int server_socket;
    server_socket=socket(AF_INET, SOCK_STREAM, 0);
    //socket for server created
 
    struct sockaddr_in server_address;
    server_address.sin_family=AF_INET;
    server_address.sin_port=htons(9002);
    server_address.sin_addr.s_addr=INADDR_ANY;
     
    bind(server_socket, (struct sockaddr*)&server_address, sizeof(server_address));
    //binding the server socket to the ports
 
    listen(server_socket,5);
    //listen for client sockets
 
    int client_socket;
    client_socket=accept(server_socket,NULL, NULL);
    //accepting a connection from client socket
     
    while(1){
        printf("Message: ");
        gets(ch);
        send(client_socket,ch,strlen(ch),0);
        recv(client_socket,&ch,sizeof(ch),0);
        printf("Message Received: %s",ch);
        }
 
             
     
         
    //sending data to the connected client socket
 
    close(server_socket);
    return 0;
}