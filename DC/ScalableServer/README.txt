


Package Descriptions:

1) cs304.scaling.client
This package contains the implementations of the Client and its various directly linked helper classes.


	Class Descriptions in package cs304.scaling.client:

	1a) Client
	
	It has all functionalities of the Client and coordinates message sending at a fixed rate to the server. The Client also listens for responses from the server. Track of messages is recorded. 
	1b) ClientProfile
	
	This class is a Timer Task . This class utilises  AppConstants.STATS_LOGGER_INTERVAL_MILLIS(20 secs as per problem)  to 	 print Client Statistics at regular 20 second intervals.

	1c) AsyncMessage

	This class makes the client to  send randomly generated payloads(asynchronously) of 8KB each at regular intervals ( (1000/message-rate) is the configured delay ) to the server.


2) cs304.scaling.utils
This package chas helper classes which is in association with the implementation. 


	Class Descriptions in package cs304.scaling.helpers:
        2a) SHA1Hasher

	This is a class has a singular static method that returns the SHA-1 hash for a given byte array.


	2b) AppConstants
	
	In this class implementation-level constants such as debug mode and time intervals for statistics etc have been put together.

	

3) cs304.scaling.server
This package contains the implementations of the Server and its  directly linked helper classes.

	
	Class Descriptions in package 304.scaling.server:

	3a) Server

	This class has all functionalities of the Server and coordinates all tasks by designating them to the thread pool. The Server listens for messages from the various clients that have 		opened connection with the server. The Server accepts client connections, listens for client messages, calculates hashcode of message sent by 		client and reports the hash back to client.

	3b) ServerProfile

	This class is a Timer Task . This class utilises  AppConstants.STATS_LOGGER_INTERVAL_MILLIS(20 secs as per problem)  to 	 print Client Statistics at regular 20 second intervals.

	3c) WorkerThread

	This class is the implementation of a worker thread that waits when the queue is empty and as soon as it gets a notify signal, it polls a task from the blocking queue, executes it and goes back to 		waiting.

	3d) ThreadPool
	
	 This class is responsible for storing the task list, for notifying executor threads about new tasks that are ready to be executed. This class is responsible for the 		batching of tasks and for initializing the executor(worker) threads.


4) cs304.scaling.functions
This package contains abstractions for the various tasks that are queued by the server and executed by the executor threads (worker threads).


	Class Descriptions in package cs304.scaling.tasks

	4a) BatchExecutorTask

	This is a class implementing the TaskInterface interface in order to do a task, which in this case is to execute all tasks in a batch one by one. This is one of the tasks executed by any one of 		the threads in the thread pool.
	
	4b) ConnectionAcceptor

	This is a class implementing the TaskInterface interface in order to do a task, which in this case is to invoke the acceptConnections method in the Server which enables the server to accept 		incoming client connections. This is one of the tasks executed by any one of the threads in the thread pool.

	4c) DoReadWriteTask

	This is a class implementing the TaskInterface interface in order to do a task, which in this case is to read a message from the Client and to compute the corresponding hashcode and relay the 	hashcode back to the Client. This is one of the tasks executed by any one of the threads in the thread pool.

	4d) TaskInterface

	An abstraction of the task that needs to be performed when each of the actual implementations are invoked.



